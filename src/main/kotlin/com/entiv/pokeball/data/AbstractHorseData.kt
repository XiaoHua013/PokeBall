package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Entity
import org.bukkit.inventory.AbstractHorseInventory
import org.bukkit.inventory.ItemStack
import kotlin.math.roundToInt

class AbstractHorseData(
    private val jumpStrength: Double,
    private val saddle: Boolean,
) : EntityData<AbstractHorse>() {

    override fun applyEntity(entity: AbstractHorse) {
        entity.jumpStrength = jumpStrength
    }

    override fun applyComponent(components: MutableList<Component>) {
        components.add(loreComponent("跳跃力", (jumpStrength * 100).roundToInt()))
        components.add(loreComponent("马鞍", if (saddle) "有" else "无"))
    }

    override fun applyItemNBT(nbtItem: NBTItem) {
        nbtItem.setDouble("jumpStrength", jumpStrength)
        nbtItem.setBoolean("saddle", saddle)
    }

    companion object : DataCreator<AbstractHorse>() {

        override val dataEntityClass = AbstractHorse::class.java

        override fun getEntityData(nbtItem: NBTItem): EntityData<*> {
            val jumpStrength = nbtItem.getDouble("jumpStrength")
            val saddle = nbtItem.getBoolean("saddle")

            return AbstractHorseData(jumpStrength, saddle)
        }

        override fun getEntityData(entity: AbstractHorse): EntityData<*> {
            return AbstractHorseData(
                jumpStrength = entity.jumpStrength,
                saddle = entity.inventory.saddle != null
            )
        }
    }
}