package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.MushroomCow

class MushroomCowData(
    private val variant: MushroomCow.Variant
): EntityData<MushroomCow>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setString("Variant", variant.name)
    }

    override fun applyComponent(components: MutableList<Component>) {
        //TODO 汉化
        loreComponent("品种", variant.name).also { components.add(it) }
    }

    override fun applyEntity(entity: MushroomCow) {
        entity.variant = variant
    }

    companion object : DataCreator<MushroomCow>() {
        override val dataEntityClass = MushroomCow::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            val variant = MushroomCow.Variant.valueOf(nbtCompound.getString("Variant"))
            return MushroomCowData(variant)
        }

        override fun getEntityData(entity: MushroomCow): EntityData<*> {
            return MushroomCowData(entity.variant)
        }
    }
}