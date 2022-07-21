package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Raider

//class RaiderData(
//    private val isPatrolLeader: Boolean
//) : EntityData<Raider>() {
//    override fun applyCompound(compound: NBTCompound) {
//        compound.setBoolean("isPatrolLeader", isPatrolLeader)
//    }
//
//    override fun applyComponent(components: MutableList<Component>) {
//        loreComponent("队长", if (isPatrolLeader) "是" else "否").apply {
//            components.add(this)
//        }
//    }
//
//    override fun applyEntity(entity: Raider) {
//        entity.isPatrolLeader = isPatrolLeader
//    }
//
//}
object RaiderData