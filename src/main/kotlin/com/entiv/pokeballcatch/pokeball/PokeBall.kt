package com.entiv.pokeballcatch.pokeball

import com.entiv.core.common.submit
import com.entiv.core.debug.debug
import com.entiv.core.utils.RandomUtil
import com.entiv.core.utils.friendlyName
import com.entiv.pokeballcatch.utils.Lang
import com.entiv.pokeballcatch.utils.config
import com.entiv.pokeballcatch.utils.dataWrappers
import com.google.common.collect.HashBiMap
import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.*
import org.bukkit.attribute.Attribute
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.*
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.persistence.PersistentDataType
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector
import org.jetbrains.annotations.NotNull
import java.net.URL
import java.util.*
import kotlin.math.max
import kotlin.math.roundToInt
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.isSuperclassOf

class PokeBall(
    val type: String,
    val name: Component,
    val skinUrl: URL,
    val ballLore: List<Component>,
    val successChance: Double,
    val brokenChance: Double,
    val healthLimit: Int,
    val canCatchMob: List<EntityType>
) {

    fun throwPokeBall(player: Player, pokeBall: ItemStack) {

        val item = player.world.dropItem(player.eyeLocation, pokeBall.clone().apply { amount = 1 })
        item.velocity = player.location.direction.multiply(config.getDouble("基础设置.投掷速度", 1.0))

        if (isCaughtBall(pokeBall)) {
            catchThrow(player, item)
        } else {
            summonThrow(player, item)
        }

        player.playSound(player, Sound.ENTITY_EGG_THROW, 1f, 1f)
        pokeBall.amount -= 1
    }

    private fun catchThrow(player: Player, item: Item) {

        item.owner = player.uniqueId

        submit(period = 1) {
            if (!item.isValid || item.isOnGround) {
                teleportItemToPlayer(item, player)
                cancel()
                return@submit
            }

            item.location.getNearbyEntities(0.7, 0.7, 0.7)
                .filterIsInstance<LivingEntity>()
                .filter { it.type != EntityType.PLAYER }
                .filter { it.type != EntityType.ARMOR_STAND }
                .firstOrNull { it.entitySpawnReason != CreatureSpawnEvent.SpawnReason.CUSTOM }
                ?.let {
                    if (it.type in canCatchMob) {
                        onHitEntity(player, item, it)
                        cancel()
                    }
                }
        }
    }

    private fun summonThrow(player: Player, item: Item) {
        item.setCanMobPickup(false)
        item.setCanPlayerPickup(false)

        submit(period = 1) {
            if (!item.isValid) {
                cancel()
                return@submit
            }

            if (item.isOnGround) {
                val location = item.location

                spawnEntity(item.itemStack, location)
                item.remove()

                if (!config.getBoolean("基础设置.一次性精灵球")) {
                    val newItem = item.world.dropItem(location, getPokeBallItem())
                    teleportItemToPlayer(newItem, player)
                }
            }
        }
    }

    private fun onHitEntity(player: Player, pokeBall: Item, entity: LivingEntity) {
        if (testCatchCondition(player, pokeBall, entity)) {
            val location = entity.location
            val world = location.world

            val item = entity.world.dropItem(location, getCaughtBallItem(entity))
            item.owner = player.uniqueId

            pokeBall.remove()
            entity.remove()

            world.createExplosion(location, 0.0f, false, false)
            world.playEffect(location, Effect.SMOKE, 0)

            teleportItemToPlayer(item, player)
            Lang.sendMessage("捕捉成功", player, entityPlaceholder(entity))
        }

        teleportItemToPlayer(pokeBall, player)

    }

    private fun testCatchCondition(player: Player, item: Item, entity: LivingEntity): Boolean {
        val damageByEntityEvent = EntityDamageByEntityEvent(
            player,
            entity,
            EntityDamageEvent.DamageCause.ENTITY_ATTACK,
            0.0
        )

        debug("玩家 ${player.name} 尝试伤害实体 ${entity.name}, 结果 ${damageByEntityEvent.isCancelled}")
        if (damageByEntityEvent.isCancelled) {
            Lang.sendMessage("受到保护", player, entityPlaceholder(entity))
            return false
        }

        if (!canCatchMob.contains(entity.type)) {
            Lang.sendMessage("无法捕捉", player, entityPlaceholder(entity), ballPlaceholder())
            return false
        }

        val maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue

        if (maxHealth != null && healthLimit >= 0 && entity.health >
            max((maxHealth * healthLimit / 100).roundToInt(), 1)
        ) {
            Lang.sendMessage("血量限制", player, entityPlaceholder(entity), healthLimitPlaceholder(entity))
            return false
        }

        if (!RandomUtil.checkChance(successChance)) {

            if (RandomUtil.checkChance(brokenChance)) {
                item.remove()
                Lang.sendMessage("精灵球损坏", player, entityPlaceholder(entity))
            } else {
                Lang.sendMessage("捕捉失败", player, entityPlaceholder(entity))
            }

            return false
        }

        return true
    }

    fun spawnEntity(itemStack: ItemStack, location: Location) {
        val compound = NBTItem(itemStack).getCompound("PokeBall") ?: error("该物品不是一个有效的精灵球")
        val entityType = EntityType.valueOf(compound.getString("EntityType"))

        val world = location.world
        val entity = world.spawnEntity(location, entityType, CreatureSpawnEvent.SpawnReason.SPAWNER_EGG)

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
        return NBTItem(itemStack).getCompound("PokeBall").getString("EntityType").isEmpty()
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

    private fun teleportItemToPlayer(item: Item, player: Player) {
        if (!item.isValid) {
            return
        }
        item.velocity = Vector(0.0, 0.0, 0.0)
        player.playSound(item.location, Sound.ENTITY_ARROW_HIT, 1f, 1f)
        item.teleport(player.eyeLocation)
    }

    companion object {
        fun fromConfig(section: ConfigurationSection): PokeBall {

            val type = section.name

            val name = MiniMessage.miniMessage()
                .deserialize(section.getString("名称") ?: "精灵球")
                .decoration(TextDecoration.ITALIC, false)

            val url = URL(
                section.getString("头颅")
                    ?: "https://textures.minecraft.net/texture/93e68768f4fab81c94df735e205c3b45ec45a67b558f3884479a62dd3f4bdbf8"
            )

            val ballLore = section.getStringList("精灵球描述").map {
                MiniMessage.miniMessage().deserialize(it).decoration(TextDecoration.ITALIC, false)
            }

            val successChance = section.getDouble("成功率", 100.0)
            val brokenChance = section.getDouble("损坏率", 100.0)
            val healthLimit = section.getInt("血量限制", -1)
            val catchManager = getAllowCatchList(section.getStringList("捕捉生物管理"))

            debug("精灵球 $type 可捕捉的实体列表: ${catchManager.joinToString { it.name }}")

            return PokeBall(
                type,
                name,
                url,
                ballLore,
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

            debug(states.joinToString { it.clazz.simpleName!! })

            val biMap = HashBiMap.create<EntityType, KClass<out Entity>>()

            val entityTypes = EntityType.values()

            entityTypes.mapNotNull { it.entityClass }
                .map { it.kotlin }
                .forEachIndexed { index, kClass -> biMap[entityTypes[index]] = kClass }

            return entityTypes.mapNotNull { it.entityClass }
                .map { it.kotlin }
                .filter { it.isSubclassOf(LivingEntity::class) }
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

    private fun entityPlaceholder(entity: Entity) = Placeholder.component("entity", entity.friendlyName())

    private fun ballPlaceholder() = Placeholder.component("ball", Component.text(type))

    private fun healthLimitPlaceholder(entity: LivingEntity) = Placeholder.component(
        "health",
        Component.text(
            max(
                (entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue * healthLimit / 100).roundToInt(),
                1
            )
        )
    )
}