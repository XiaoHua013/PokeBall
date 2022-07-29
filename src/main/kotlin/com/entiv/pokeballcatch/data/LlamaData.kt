package com.entiv.pokeballcatch.data

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
        addComponent(components, "颜色", entity.color.translate())
        addComponent(components, "力量", entity.strength.toString())
    }

    fun Llama.Color.translate(): String {
        return when (this) {
            Llama.Color.BROWN -> "棕色"
            Llama.Color.WHITE -> "亮银色"
            Llama.Color.CREAMY -> "白色"
            Llama.Color.GRAY -> "沙褐色"
        }
    }

}