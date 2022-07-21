package com.entiv.pokeball.data

import com.entiv.pokeball.utils.translateDyeColor
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.material.Colorable

class ColorableData(
    val color: DyeColor
) : EntityData<Colorable>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setString("Color", color.name)
    }

    override fun applyComponent(components: MutableList<Component>) {
        loreComponent("颜色", translateDyeColor(color)).apply {
            components.add(this)
        }
    }

    override fun applyEntity(entity: Colorable) {
        entity.color = color
    }

    companion object : DataCreator<Colorable>() {
        override val dataClass = Colorable::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            val color = DyeColor.valueOf(nbtCompound.getString("Color"))
            return ColorableData(color)
        }

        override fun getEntityData(entity: Colorable): EntityData<*> {
            val color = entity.color ?: DyeColor.WHITE
            return ColorableData(color)
        }
    }
}