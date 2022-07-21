package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Llama
import org.bukkit.entity.Llama.Color

object LlamaData : DataWrapper<Llama>(Llama::class) {

    override fun entityWriteToNbt(entity: Llama, compound: NBTCompound) {
        compound.setString("Color", entity.color.name)
        compound.setInteger("Strength", entity.strength)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Llama) {
        entity.color = Color.valueOf(compound.getString("Color"))
        entity.strength = compound.getInteger("Strength")
    }

    override fun entityWriteToComponent(entity: Llama, components: MutableList<Component>) {
        addComponent(components, "颜色", translateColor(entity.color))
        addComponent(components, "力量", entity.strength.toString())
    }

    private fun translateColor(color: Color): String {
        return when (color) {
            Color.BROWN -> "棕色"
            Color.WHITE -> "亮银色"
            Color.CREAMY -> "白色"
            Color.GRAY -> "沙褐色"
        }
    }
}