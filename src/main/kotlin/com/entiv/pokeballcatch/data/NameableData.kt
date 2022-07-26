package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import org.bukkit.Nameable

object NameableData : DataWrapper<Nameable>(Nameable::class) {

    override val priority = 120

    override fun entityWriteToNbt(entity: Nameable, compound: NBTCompound) {
        entity.customName()?.let {
            compound.setString("CustomName", GsonComponentSerializer.gson().serialize(it))
        }
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Nameable) {
        compound.getString("CustomName")?.let {
            entity.customName(GsonComponentSerializer.gson().deserialize(it))
        }
    }

    override fun entityWriteToComponent(entity: Nameable, components: MutableList<Component>) {
        entity.customName()?.let {
            addComponent(components, "名称", it)
        }
    }


}