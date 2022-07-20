package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Horse

class HorseData(
    private val horseColor: Horse.Color,
    private val horseStyle: Horse.Style,
): EntityData<Horse>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setString("Color", horseColor.name)
        nbtCompound.setString("Style", horseStyle.name)
    }

    override fun applyComponent(components: MutableList<Component>) {
        loreComponent("颜色", horseColor.name).also { components.add(it) }
        loreComponent("品种", horseStyle.name).also { components.add(it) }
    }

    override fun applyEntity(entity: Horse) {
        entity.color = horseColor
        entity.style = horseStyle
    }

    companion object : DataCreator<Horse>() {
        override val dataEntityClass = Horse::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            val horseColor = Horse.Color.valueOf(nbtCompound.getString("Color"))
            val horseStyle = Horse.Style.valueOf(nbtCompound.getString("Style"))

            return HorseData(horseColor, horseStyle)
        }

        override fun getEntityData(entity: Horse): EntityData<*> {
            return HorseData(entity.color, entity.style)
        }

    }
}