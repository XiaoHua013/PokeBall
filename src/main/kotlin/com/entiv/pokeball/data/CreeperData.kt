package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Creeper

object CreeperData : DataWrapper<Creeper>(Creeper::class) {
    override fun entityWriteToNbt(entity: Creeper, compound: NBTCompound) {
        compound.setBoolean("powered", true)

    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Creeper) {
        entity.isPowered = compound.getBoolean("powered")
    }

    override fun entityWriteToComponent(entity: Creeper, components: MutableList<Component>) {

    }

}