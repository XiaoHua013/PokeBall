package com.entiv.pokeballcatch.data

import com.entiv.core.utils.translate
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.material.Colorable

object ColorableData : DataWrapper<Colorable>(Colorable::class) {
    override fun entityWriteToNbt(entity: Colorable, compound: NBTCompound) {
        compound.setString("Color", entity.color?.name ?: DyeColor.WHITE.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Colorable) {
        entity.color = DyeColor.valueOf(compound.getString("Color"))
    }

    override fun entityWriteToComponent(entity: Colorable, components: MutableList<Component>) {
        addComponent(components, "颜色", (entity.color ?: DyeColor.WHITE).translate())
    }
}