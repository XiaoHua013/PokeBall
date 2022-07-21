package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.MushroomCow

//class MushroomCowData(
//    private val variant: MushroomCow.Variant
//): EntityData<MushroomCow>() {
//    override fun applyCompound(compound: NBTCompound) {
//        compound.setString("Variant", variant.name)
//    }
//
//    override fun applyComponent(components: MutableList<Component>) {
//        loreComponent("品种", translate(variant)).also { components.add(it) }
//    }
//

//
//    override fun applyEntity(entity: MushroomCow) {
//        entity.variant = variant
//    }
//
//}
object MushroomCowData : DataWrapper<MushroomCow>() {
    override fun entityWriteToNbt(entity: MushroomCow, compound: NBTCompound) {
        compound.setString("Variant", entity.variant.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: MushroomCow) {
        entity.variant = MushroomCow.Variant.valueOf(compound.getString("Variant"))
    }

    override fun entityWriteToComponent(entity: MushroomCow, components: MutableList<Component>) {
        addComponent(components, "品种", translateVariant(entity.variant))
    }

    private fun translateVariant(variant: MushroomCow.Variant): String {
        return when (variant) {
            MushroomCow.Variant.RED -> "红色哞菇"
            MushroomCow.Variant.BROWN -> "棕色哞菇"
        }
    }
}