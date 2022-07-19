package com.entiv.pokeball.data

import net.kyori.adventure.text.Component
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack

class LivingEntityData(
    private val type: EntityType,
    private val health: Double,
    private val maxHealth: Double,

    private val name: Component? = null,
) : EntityData<LivingEntity>() {

    override fun applyEntity(entity: LivingEntity) {
        entity.health = health
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = maxHealth

        name?.let {
            entity.customName(it)
            entity.isCustomNameVisible = true
        }
    }

    override fun applyComponent(component: Component) {
        component.append(Component.text("Type: $type"))
        component.append(Component.text("Health: $health/$maxHealth"))

        name?.let {
            component.append(Component.text("Name: $it"))
        }
    }

    companion object {

        fun fromEntity(entity: LivingEntity): LivingEntityData {
            return LivingEntityData(
                    entity.type,
                    entity.health,
                    entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue
                        ?: error("无法获取到实体 ${entity.uniqueId} 的最大血量"),
                    entity.customName(),
                )
        }

        fun fromItemStack(itemStack: ItemStack): LivingEntityData {
            TODO()
        }
    }
}



