package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Llama
import org.bukkit.entity.Llama.Color

//class LlamaData(
//    private val color: Llama.Color,
//    private val strength: Int
//) : EntityData<Llama>() {
//    override fun applyCompound(compound: NBTCompound) {
//        compound.setString("Color", color.name)
//        compound.setInteger("Strength", strength)
//    }
//
//    override fun applyComponent(components: MutableList<Component>) {
//        loreComponent("颜色", translateColor(color))
//        loreComponent("力量", strength)
//    }
//
//    private fun translateColor(color: Color): String {
//        return when (color) {
//            Color.BROWN -> "棕色"
//            Color.WHITE -> "亮银色"
//            Color.CREAMY -> "白色"
//            Color.GRAY -> "沙褐色"
//        }
//    }
//
//    override fun applyEntity(entity: Llama) {
//        entity.color = color
//        entity.strength = strength
//    }
//
//
//}
object LlamaData