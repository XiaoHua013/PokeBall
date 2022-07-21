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

//    override fun applyEntity(entity: Parrot) {
//        entity.variant = variant
//    }
//
//
//
//}
object ParrotData : DataWrapper<Parrot>() {
    override fun entityWriteToNbt(entity: Parrot, compound: NBTCompound) {
        compound.setString("Variant", entity.variant.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Parrot) {
        entity.variant = Variant.valueOf(compound.getString("Variant"))
    }

    override fun entityWriteToComponent(entity: Parrot, components: MutableList<Component>) {
        addComponent(components, "颜色", translateVariant(entity.variant))
    }

    private fun translateVariant(variant: Variant): String {
        return when (variant) {
            Variant.RED -> "红色"
            Variant.GREEN -> "绿色"
            Variant.BLUE -> "蓝色"
            Variant.CYAN -> "青色"
            Variant.GRAY -> "灰色"
        }
    }

}