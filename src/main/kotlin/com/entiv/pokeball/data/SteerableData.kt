package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Steerable

//class SteerableData(
//    val hasSaddle: Boolean,
//) : EntityData<Steerable>() {
//    override fun applyCompound(compound: NBTCompound) {
//        compound.setBoolean("hasSaddle", hasSaddle)
//    }
//
//    override fun applyComponent(components: MutableList<Component>) {
//        loreComponent("鞍", if (hasSaddle) "有" else "无").also {
//            components.add(it)
//        }
//    }
//
//    override fun applyEntity(entity: Steerable) {
//        entity.setSaddle(hasSaddle)
//    }
//
//
//}

object SteerableData