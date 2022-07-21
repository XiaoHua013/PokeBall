package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Snowman

//class SnowmanData(
//    val isDerp: Boolean,
//) : EntityData<Snowman>() {
//    override fun applyCompound(compound: NBTCompound) {
//        compound.setBoolean("isDerp", isDerp)
//    }
//
//    override fun applyComponent(components: MutableList<Component>) {
//        loreComponent("头盔", if (isDerp) "有" else "无").also {
//            components.add(it)
//        }
//    }
//
//    override fun applyEntity(entity: Snowman) {
//        entity.isDerp = isDerp
//    }
//
//}
object SnowmanData