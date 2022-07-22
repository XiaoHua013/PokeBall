package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.Nameable

class NameableData : DataWrapper<Nameable>(Nameable::class) {

    override val priority = 120

    override fun entityWriteToNbt(entity: Nameable, compound: NBTCompound) {
        entity.customName()?.let {
            compound.setObject("CustomName", it)
        }
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Nameable) {
        compound.getObject("CustomName", Component::class.java)?.let {
            entity.customName(it)
        }
    }

    override fun entityWriteToComponent(entity: Nameable, components: MutableList<Component>) {
        entity.customName()?.let {
            addComponent(components, "名称", it)
        }
    }


}