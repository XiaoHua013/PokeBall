package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Axolotl
import org.bukkit.entity.Axolotl.Variant

object AxolotlData : DataWrapper<Axolotl>() {
    override fun entityWriteToNbt(entity: Axolotl, compound: NBTCompound) {
        compound.setString("variant", entity.variant.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Axolotl) {
        entity.variant = Variant.valueOf(compound.getString("variant"))
    }

    override fun entityWriteToComponent(entity: Axolotl, components: MutableList<Component>) {
        addComponent(components, "颜色", translateVariant(entity.variant))
    }

    private fun translateVariant(variant: Variant): String {
        return when (variant) {
            Variant.LUCY -> "粉红色"
            Variant.WILD -> "棕色"
            Variant.GOLD -> "金色"
            Variant.CYAN -> "青色"
            Variant.BLUE -> "蓝色"
        }
    }

}