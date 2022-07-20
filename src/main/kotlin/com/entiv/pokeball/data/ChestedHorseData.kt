package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import org.bukkit.entity.ChestedHorse

class ChestedHorseData(
    private val carryingChest: Boolean
): EntityData<ChestedHorse>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setBoolean("carryingChest", carryingChest)
    }

    override fun applyComponent(components: MutableList<Component>) {
    }

    override fun applyEntity(entity: ChestedHorse) {
        entity.isCarryingChest = carryingChest
    }

    companion object : DataCreator<ChestedHorse>() {
        override val dataEntityClass = ChestedHorse::class.java

        override fun getEntityData(nbtItem: NBTItem): EntityData<*> {
            return ChestedHorseData(nbtItem.getBoolean("carryingChest"))
        }

        override fun getEntityData(entity: ChestedHorse): EntityData<*> {
            return ChestedHorseData(entity.isCarryingChest)
        }

    }
}