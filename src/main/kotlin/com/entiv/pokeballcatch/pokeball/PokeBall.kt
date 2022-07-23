package com.entiv.pokeballcatch.pokeball

import com.entiv.core.common.submit
import com.entiv.core.utils.RandomUtil
import com.entiv.pokeballcatch.utils.config
import com.entiv.pokeballcatch.utils.dataWrappers
import com.entiv.pokeballcatch.utils.safePlaySound
import com.google.common.collect.HashBiMap
import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.*
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.*
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.net.URL
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.isSuperclassOf

class PokeBall(
    val type: String,
    val name: Component,
    val skinUrl: URL,
    val ballLore: List<Component>,
    val entityLore: Component,
    val successChance: Double,
    val brokenChance: Double,
    val healthLimit: Int,
    val canCatchMob: List<EntityType>
) {

    fun throwPokeBall(player: Player, pokeBall: ItemStack) {
        player.safePlaySound(player.location, config.getString("基础设置.投掷音效", "")!!)

        val item = player.world.dropItem(player.eyeLocation, pokeBall.clone().apply { amount = 1 })
        item.velocity = player.location.direction.multiply(config.getDouble("基础设置.投掷速度", 1.0))

        item.setCanMobPickup(false)
        item.owner = player.uniqueId

        pokeBall.amount -= 1

        submit(period = 5) {
            item.location.getNearbyEntities(1.0, 1.0, 1.0)
                .filterIsInstance<LivingEntity>()
                .filter { it.type != EntityType.PLAYER }
                .firstOrNull { it.type != EntityType.ARMOR_STAND }?.let {
                    if (it.type in canCatchMob) {
                        onHitEntity(player, item, it)
                    }
                }

            if (!item.isValid) {
                cancel()
            }

            if (item.isOnGround) {
                item.teleport(player)
                return@submit
            }
        }
    }

    fun onHitEntity(player: Player, pokeBall: Item, entity: LivingEntity) {
        if (!testCatchCondition(player, entity)) {
            pokeBall.teleport(player)
            pokeBall.setCanMobPickup(false)

            return
        }

        if (RandomUtil.checkChance(successChance)) {
            val location = entity.location
            val world = location.world

            pokeBall.remove()
            entity.remove()

            world.createExplosion(location, 0.0f)
            world.playEffect(location, Effect.SMOKE, 0)
            entity.world.dropItem(location, getCaughtBallItem(entity))
            player.sendRichMessage(config.getString("提示消息.捕捉成功", "")!!)
        } else {
            pokeBall.remove()
            player.sendRichMessage(config.getString("提示消息.捕捉失败", "")!!)
        }
    }

    private fun testCatchCondition(player: Player, entity: LivingEntity): Boolean {
        if (!canCatchMob.contains(entity.type)) {
            MiniMessage.miniMessage().deserialize(config.getString("提示消息.无法捕捉", "")!!)
            return false
        }

        //TODO 待添加
//        if (entity.health >= healthLimit) {
//            player.sendRichMessage(config.getString("提示消息.血量限制", "")!!)
//            return false
//        }

        return true
    }

    fun spawnEntity(itemStack: ItemStack, location: Location) {
        val compound = NBTItem(itemStack).getCompound("PokeBall") ?: error("该物品不是一个有效的精灵球")
        val entityType = EntityType.valueOf(compound.getString("EntityType"))

        val world = location.world
        val entity = world.spawnEntity(location, entityType)

        dataWrappers.forEach {
            it.processEntity(entity, compound)
        }

        itemStack.amount -= 1
    }

    fun getCaughtBallItem(entity: LivingEntity): ItemStack {
        val itemStack = getBasePockBall()

        dataWrappers.forEach { it.processItemStack(itemStack, entity) }

        return itemStack
    }

    fun getPokeBallItem(): ItemStack {
        return getBasePockBall().apply {
            lore(ballLore)
        }
    }

    fun isCaughtBall(itemStack: ItemStack): Boolean {
        return NBTItem(itemStack).getCompound("PokeBall").getString("EntityType").isNotEmpty()
    }

    private fun getBasePockBall(): ItemStack {
        val itemStack = ItemStack(Material.PLAYER_HEAD)
        val skullMeta = itemStack.itemMeta as SkullMeta

        skullMeta.displayName(name)

        val profile = Bukkit.getServer().createProfile(UUID.randomUUID())
        val textures = profile.textures

        textures.skin = skinUrl
        profile.setTextures(textures)
        skullMeta.playerProfile = profile
        itemStack.itemMeta = skullMeta

        val nbtItem = NBTItem(itemStack)
        val compound = nbtItem.addCompound("PokeBall")
        compound.setString("BallType", type)
        nbtItem.applyNBT(itemStack)

        return itemStack
    }

    companion object {
        fun fromConfig(section: ConfigurationSection): PokeBall {

            val type = section.name

            val name = MiniMessage.miniMessage().deserialize(section.getString("名称") ?: "精灵球")
            val url = URL(
                section.getString("头颅")
                    ?: "https://textures.minecraft.net/texture/93e68768f4fab81c94df735e205c3b45ec45a67b558f3884479a62dd3f4bdbf8"
            )

            val ballLore = section.getStringList("精灵球描述").map { MiniMessage.miniMessage().deserialize(it) }

            val entityLore = MiniMessage.miniMessage().deserialize(section.getString("生物信息描述") ?: "")

            val successChance = section.getDouble("成功率", 100.0)
            val brokenChance = section.getDouble("损坏率", 100.0)
            val healthLimit = section.getInt("血量限制", -1)
            val catchManager = getAllowCatchList(section.getStringList("生物捕捉管理"))

            return PokeBall(
                type,
                name,
                url,
                ballLore,
                entityLore,
                successChance,
                brokenChance,
                healthLimit,
                catchManager
            )
        }

        private fun getAllowCatchList(content: List<String>): List<EntityType> {
            val states = content.map { PokeBallCatchState.create(it) }

            states.sortedWith(Comparator { o1, o2 ->
                if (o1.clazz.isSubclassOf(o2.clazz)) {
                    return@Comparator 1
                } else if (o1.clazz.isSuperclassOf(o2.clazz)) {
                    return@Comparator -1
                } else {
                    return@Comparator 0
                }
            })

            val biMap = HashBiMap.create<EntityType, KClass<out Entity>>()

            val entityTypes = EntityType.values()

            entityTypes.mapNotNull { it.entityClass }.map { it.kotlin }.forEachIndexed { index, kClass ->
                biMap[entityTypes[index]] = kClass
            }

            return entityTypes.mapNotNull { it.entityClass }
                .map { it.kotlin }
                .filter { LivingEntity::class.isSuperclassOf(it) }
                .filter { it != Player::class }
                .filter { it != ArmorStand::class }
                .filter {
                    var canCatch = true
                    for (state in states) {
                        if (it.isSubclassOf(state.clazz)) {
                            canCatch = state.flag
                        }
                    }
                    canCatch
                }
                .mapNotNull { biMap.inverse()[it] }
                .toList()
        }
    }
}