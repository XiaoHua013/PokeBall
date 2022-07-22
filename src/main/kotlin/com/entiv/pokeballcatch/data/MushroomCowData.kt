package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.MushroomCow


object MushroomCowData : DataWrapper<MushroomCow>(MushroomCow::class) {
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