package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Phantom

object PhantomData : DataWrapper<Phantom>() {
    override fun entityWriteToNbt(entity: Phantom, compound: NBTCompound) {
        compound.setInteger("Size", entity.size)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Phantom) {
        entity.size = compound.getInteger("Size")
    }

    override fun entityWriteToComponent(entity: Phantom, components: MutableList<Component>) {
        addComponent(components, "大小", entity.size)
    }

}