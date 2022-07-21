package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.AbstractHorse
import kotlin.math.roundToInt

object AbstractHorseData : DataWrapper<AbstractHorse>(AbstractHorse::class) {
    override fun entityWriteToNbt(entity: AbstractHorse, compound: NBTCompound) {
        compound.setDouble("JumpStrength", entity.jumpStrength)
        compound.setBoolean("Saddle", entity.inventory.saddle != null)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: AbstractHorse) {
        entity.jumpStrength = compound.getDouble("JumpStrength")
        entity.inventory.saddle = if (compound.getBoolean("Saddle")) entity.inventory.saddle else null
    }

    override fun entityWriteToComponent(entity: AbstractHorse, components: MutableList<Component>) {
        addComponent(components, "跳跃力", (entity.jumpStrength * 100).roundToInt())
        addComponent(components, "马鞍", if (entity.inventory.saddle != null) "有" else "无")
    }

}