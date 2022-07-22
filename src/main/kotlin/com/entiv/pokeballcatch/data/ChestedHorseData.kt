package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.ChestedHorse

object ChestedHorseData : DataWrapper<ChestedHorse>(ChestedHorse::class) {
    override fun entityWriteToNbt(entity: ChestedHorse, compound: NBTCompound) {
        compound.setBoolean("carryingChest", entity.isCarryingChest)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: ChestedHorse) {
        entity.isCarryingChest = compound.getBoolean("carryingChest")
    }

    override fun entityWriteToComponent(entity: ChestedHorse, components: MutableList<Component>) {
        addComponent(components, "拥有箱子", if (entity.isCarryingChest) "是" else "否")
    }

}