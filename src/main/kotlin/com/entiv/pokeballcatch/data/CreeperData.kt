package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Creeper

object CreeperData : DataWrapper<Creeper>(Creeper::class) {
    override fun entityWriteToNbt(entity: Creeper, compound: NBTCompound) {
        compound.setBoolean("powered", entity.isPowered)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Creeper) {
        entity.isPowered = compound.getBoolean("powered")
    }

    override fun entityWriteToComponent(entity: Creeper, components: MutableList<Component>) {
        addComponent(components, "闪电爬行者", if (entity.isPowered) "是" else "否")
    }

}