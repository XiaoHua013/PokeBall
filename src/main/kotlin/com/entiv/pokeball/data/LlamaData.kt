package com.entiv.pokeball.data

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
        //TODO 汉化
        loreComponent("颜色", color.name)
        loreComponent("力量", strength)
    }

    override fun applyEntity(entity: Llama) {
        entity.color = color
        entity.strength = strength
    }

    companion object : DataCreator<Llama>() {
        override val dataEntityClass = Llama::class.java

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