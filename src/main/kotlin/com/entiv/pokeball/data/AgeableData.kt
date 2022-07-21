package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Ageable

object AgeableData : DataWrapper<Ageable>(Ageable::class) {
    override fun entityWriteToNbt(entity: Ageable, compound: NBTCompound) {
        compound.setInteger("Age", entity.age)
        compound.setBoolean("Adult", entity.isAdult)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Ageable) {
        entity.age = compound.getInteger("Age")
    }

    override fun entityWriteToComponent(entity: Ageable, components: MutableList<Component>) {
        addComponent(components, "成年", if (entity.isAdult) "是" else "否")
    }

}