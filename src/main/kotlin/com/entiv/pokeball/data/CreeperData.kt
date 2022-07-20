package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.entity.Cat
import org.bukkit.entity.Creeper

class CreeperData(
    private val powered: Boolean
) : EntityData<Creeper>() {

    override fun applyItemNBT(nbtItem: NBTItem) {
        nbtItem.setBoolean("powered", true)
    }

    override fun applyComponent(components: MutableList<Component>) {

    }

    override fun applyEntity(entity: Creeper) {
        TODO("Not yet implemented")
    }

}