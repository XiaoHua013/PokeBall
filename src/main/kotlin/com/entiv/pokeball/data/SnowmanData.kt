package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Sheep
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
object SnowmanData : DataWrapper<Snowman>() {
    override fun entityWriteToNbt(entity: Snowman, compound: NBTCompound) {
        compound.setBoolean("isDerp", entity.isDerp)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Snowman) {
        entity.isDerp = compound.getBoolean("isDerp")
    }

    override fun entityWriteToComponent(entity: Snowman, components: MutableList<Component>) {
        addComponent(components, "南瓜头", if (entity.isDerp) "有" else "无")
    }
}