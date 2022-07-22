package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import kotlin.math.roundToInt

object LivingEntityData : DataWrapper<LivingEntity>(LivingEntity::class) {

    override val priority = 0

    override fun entityWriteToNbt(entity: LivingEntity, compound: NBTCompound) {
        val maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue ?: error("读取实体最大生命值错误")

        compound.setString("EntityType", entity.type.name)
        compound.setDouble("MaxHealth", maxHealth)
        compound.setDouble("Health", entity.health)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: LivingEntity) {
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = compound.getDouble("MaxHealth")
        entity.health = compound.getDouble("Health")
    }

    override fun entityWriteToComponent(entity: LivingEntity, components: MutableList<Component>) {
        val maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue ?: error("读取实体最大生命值错误")

        addComponent(components, "类型", Component.translatable(entity.type.translationKey()))
        addComponent(components, "血量", "${entity.health.roundToInt()} / ${maxHealth.roundToInt()}")
    }
}