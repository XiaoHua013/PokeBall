package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import org.bukkit.attribute.Attribute
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class LivingEntityData(
    private val entityType: EntityType,
    private val health: Double,
    private val maxHealth: Double,

    private val customName: Component? = null,
) : EntityData<LivingEntity>() {

    override fun applyEntity(entity: LivingEntity) {
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = maxHealth
        entity.health = health

        customName?.let {
            entity.customName(it)
            entity.isCustomNameVisible = true
        }
    }

    override fun applyComponent(components: MutableList<Component>) {
        components.add(loreComponent("类型", Component.translatable(entityType.translationKey())))
        components.add(loreComponent("血量", "$health / $maxHealth"))

        customName?.let {
            components.add(loreComponent("名称", it))
        }
    }

    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setString("entityType", entityType.toString())
        nbtCompound.setDouble("health", health)
        nbtCompound.setDouble("maxHealth", maxHealth)

        customName?.let {
            nbtCompound.setObject("customName", it)
        }
    }

    companion object : DataCreator<LivingEntity>() {

        override val dataEntityClass = LivingEntity::class.java

        override fun getEntityData(entity: LivingEntity): EntityData<*> {
            return LivingEntityData(
                entity.type,
                entity.health,
                entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue ?: error("最大血量读取异常!"),
                entity.customName()
            )
        }

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {

            val type = EntityType.valueOf(nbtCompound.getString("entityType"))
            val health = nbtCompound.getDouble("health")
            val maxHealth = nbtCompound.getDouble("maxHealth")
            val name = nbtCompound.getObject("customName", Component::class.java)

            return LivingEntityData(type, health, maxHealth, name)
        }
    }
}



