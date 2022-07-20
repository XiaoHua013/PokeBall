package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.AbstractHorse
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

    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setDouble("jumpStrength", jumpStrength)
        nbtCompound.setBoolean("saddle", saddle)
    }

    companion object : DataCreator<AbstractHorse>() {

        override val dataEntityClass = AbstractHorse::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            val jumpStrength = nbtCompound.getDouble("jumpStrength")
            val saddle = nbtCompound.getBoolean("saddle")

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