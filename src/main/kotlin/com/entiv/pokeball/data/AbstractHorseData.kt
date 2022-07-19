package com.entiv.pokeball.data

import com.entiv.pokeball.data.EntityData
import net.kyori.adventure.text.Component
import org.bukkit.attribute.Attribute
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.AbstractHorseInventory
import org.bukkit.inventory.ItemStack

class AbstractHorseData(
    private val jumpStrength: Double,
    private val horseInventory: AbstractHorseInventory,
): EntityData<AbstractHorse>() {

    override fun applyEntity(entity: AbstractHorse) {
        entity.jumpStrength = jumpStrength

        for (itemStack in horseInventory) {
            entity.inventory.addItem(itemStack)
        }
    }

    override fun applyComponent(component: Component) {
        component.append(Component.text("jump strength: $jumpStrength"))
    }

    companion object {

        fun fromEntity(entity: AbstractHorse): AbstractHorseData {
            return AbstractHorseData(
                entity.jumpStrength,
                entity.inventory
            )
        }

        fun fromItemStack(itemStack: ItemStack): AbstractHorseData {
            TODO()
        }
    }
}