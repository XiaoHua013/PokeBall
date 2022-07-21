package com.entiv.pokeball.data

import com.entiv.pokeball.utils.colorTranslation
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Llama

class LlamaData(
    private val color: Llama.Color,
    private val strength: Int
): EntityData<Llama>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setString("Color", color.name)
        nbtCompound.setInteger("Strength", strength)
    }

    override fun applyComponent(components: MutableList<Component>) {
        loreComponent("颜色", colorTranslation(color.name))
        loreComponent("力量", strength)
    }

    override fun applyEntity(entity: Llama) {
        entity.color = color
        entity.strength = strength
    }

    companion object : DataCreator<Llama>() {
        override val dataClass = Llama::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            val color = Llama.Color.valueOf(nbtCompound.getString("Color"))
            val strength = nbtCompound.getInteger("Strength")

            return LlamaData(color, strength)
        }

        override fun getEntityData(entity: Llama): EntityData<*> {
            return LlamaData(entity.color, entity.strength)
        }
    }
}