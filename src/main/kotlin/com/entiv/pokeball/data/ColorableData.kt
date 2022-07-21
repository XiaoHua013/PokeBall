package com.entiv.pokeball.data

import com.entiv.pokeball.utils.translateDyeColor
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.material.Colorable

object ColorableData : DataWrapper<Colorable>() {
    override fun entityWriteToNbt(entity: Colorable, compound: NBTCompound) {
        compound.setString("Color", entity.color?.name ?: DyeColor.WHITE.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Colorable) {
        entity.color = DyeColor.valueOf(compound.getString("Color"))
    }

    override fun entityWriteToComponent(entity: Colorable, components: MutableList<Component>) {
        addComponent(components, "颜色", translateDyeColor(entity.color ?: DyeColor.WHITE))
    }
}