package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Goat

object GoatData : DataWrapper<Goat>(Goat::class) {
    override fun entityWriteToNbt(entity: Goat, compound: NBTCompound) {
        compound.setBoolean("isScreaming", entity.isScreaming)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Goat) {
        entity.isScreaming = compound.getBoolean("isScreaming")
    }

    override fun entityWriteToComponent(entity: Goat, components: MutableList<Component>) {
        addComponent(components, "尖叫山羊", if (entity.isScreaming) "是" else "否")
    }
}