package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Tadpole

object TadpoleData : DataWrapper<Tadpole>(Tadpole::class) {
    override fun entityWriteToNbt(entity: Tadpole, compound: NBTCompound) {
        compound.setInteger("Age", entity.age)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Tadpole) {
        entity.age = compound.getInteger("Age")
    }

    override fun entityWriteToComponent(entity: Tadpole, components: MutableList<Component>) {
        addComponent(components, "年龄", entity.age)
    }
}