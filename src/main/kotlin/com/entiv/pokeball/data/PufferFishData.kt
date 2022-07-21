package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.PufferFish

object PufferFishData : DataWrapper<PufferFish>(PufferFish::class) {
    override fun entityWriteToNbt(entity: PufferFish, compound: NBTCompound) {
        compound.setInteger("PuffState", entity.puffState)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: PufferFish) {
        entity.puffState = compound.getInteger("PuffState")
    }

    override fun entityWriteToComponent(entity: PufferFish, components: MutableList<Component>) {
        addComponent(components, "膨胀大小", entity.puffState)
    }

}