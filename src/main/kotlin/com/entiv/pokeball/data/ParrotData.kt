package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Parrot
import org.bukkit.entity.Parrot.Variant

//class ParrotData(
//    val variant: Variant
//) : EntityData<Parrot>() {
//    override fun applyCompound(compound: NBTCompound) {
//        compound.setString("Variant", variant.name)
//    }
//
//    override fun applyComponent(components: MutableList<Component>) {
//        loreComponent("颜色", translate(variant)).also { components.add(it) }
//    }
//
//    private fun translate(variant: Variant): String {
//        return when (variant) {
//            Variant.RED -> "红色"
//            Variant.GREEN -> "绿色"
//            Variant.BLUE -> "蓝色"
//            Variant.CYAN -> "青色"
//            Variant.GRAY -> "灰色"
//        }
//    }
//    override fun applyEntity(entity: Parrot) {
//        entity.variant = variant
//    }
//
//
//
//}
object ParrotData