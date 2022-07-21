package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.AnimalTamer
import org.bukkit.entity.Tameable

//class TameableData(
//    val isTamed: Boolean,
//    val owner: AnimalTamer?
//) : EntityData<Tameable>() {
//    override fun applyCompound(compound: NBTCompound) {
//        compound.setBoolean("isTamed", isTamed)
//        compound.setString("owner", owner?.name ?: "无")
//    }
//
//    override fun applyComponent(components: MutableList<Component>) {
//        loreComponent("已驯服", if (isTamed) "是" else "否").apply {
//            components.add(this)
//        }
//
//        if (isTamed) {
//            loreComponent("主人", owner?.name ?: "无").apply {
//                components.add(this)
//            }
//        }
//
//    }
//
//    override fun applyEntity(entity: Tameable) {
//        entity.isTamed = isTamed
//        if (isTamed) {
//            entity.owner = owner
//        }
//    }
//
//}
object TameableData