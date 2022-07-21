package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Rabbit

//class RabbitData(
//    private val type: Rabbit.Type
//) : EntityData<Rabbit>() {
//    override fun applyCompound(compound: NBTCompound) {
//        compound.setString("RabbitType", type.name)
//    }
//
//    override fun applyComponent(components: MutableList<Component>) {
//        loreComponent("品种", translateType(type)).also { components.add(it) }
//    }
//
//    override fun applyEntity(entity: Rabbit) {
//        entity.rabbitType = type
//    }
//
//    private fun translateType(type: Rabbit.Type): String {
//        return when (type) {
//            Rabbit.Type.BROWN -> "棕色"
//            Rabbit.Type.WHITE -> "白色"
//            Rabbit.Type.BLACK -> "黑色"
//            Rabbit.Type.BLACK_AND_WHITE -> "黑白斑点"
//            Rabbit.Type.GOLD -> "金黄色"
//            Rabbit.Type.SALT_AND_PEPPER -> "棕白色"
//            Rabbit.Type.THE_KILLER_BUNNY -> "杀手兔"
//        }
//    }
//
//}

object RabbitData