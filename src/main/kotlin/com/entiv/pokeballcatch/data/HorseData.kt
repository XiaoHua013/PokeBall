package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Horse

object HorseData : DataWrapper<Horse>(Horse::class) {
    override fun entityWriteToNbt(entity: Horse, compound: NBTCompound) {
        compound.setString("Color", entity.color.name)
        compound.setString("Style", entity.style.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Horse) {
        entity.color = Horse.Color.valueOf(compound.getString("Color"))
        entity.style = Horse.Style.valueOf(compound.getString("Style"))
    }

    override fun entityWriteToComponent(entity: Horse, components: MutableList<Component>) {
        addComponent(components, "颜色", translationColor(entity.color))
        addComponent(components, "花纹", translationStyle(entity.style))
    }

    private fun translationColor(color: Horse.Color): String {
        return when (color) {
            Horse.Color.WHITE -> "白色"
            Horse.Color.CREAMY -> "淡栗色"
            Horse.Color.CHESTNUT -> "深枣红色"
            Horse.Color.BROWN -> "褐色"
            Horse.Color.BLACK -> "黑色"
            Horse.Color.GRAY -> "灰色"
            Horse.Color.DARK_BROWN -> "深褐色"
        }
    }

    private fun translationStyle(style: Horse.Style): String {
        return when (style) {
            Horse.Style.NONE -> "纯色"
            Horse.Style.WHITE -> "长袜白斑"
            Horse.Style.WHITEFIELD -> "雪片白斑"
            Horse.Style.WHITE_DOTS -> "白色斑点"
            Horse.Style.BLACK_DOTS -> "黑色斑点"
        }
    }
}