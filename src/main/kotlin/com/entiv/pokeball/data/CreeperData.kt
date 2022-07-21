package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Creeper

//class CreeperData(
//    private val powered: Boolean
//) : EntityData<Creeper>() {
//
//    override fun applyCompound(compound: NBTCompound) {
//    }
//
//    override fun applyComponent(components: MutableList<Component>) {
//        loreComponent("闪电爬行者", if (powered) "是" else "否")
//    }
//
//    override fun applyEntity(entity: Creeper) {
//        entity.isPowered = powered
//    }
//
//}

object CreeperData : DataWrapper<Creeper>() {
    override fun entityWriteToNbt(entity: Creeper, compound: NBTCompound) {
        compound.setBoolean("powered", true)

    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Creeper) {
        entity.isPowered = compound.getBoolean("powered")
    }

    override fun entityWriteToComponent(entity: Creeper, components: MutableList<Component>) {

    }

}