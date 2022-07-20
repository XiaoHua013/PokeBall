package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Enderman
import org.bukkit.material.MaterialData

class EndermanData(
    private val carriedBlock: BlockData,
) : EntityData<Enderman>() {

    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setObject("carriedBlock", carriedBlock)
    }

    override fun applyComponent(components: MutableList<Component>) {
        components.add(loreComponent("持有方块", Component.translatable(carriedBlock.material.translationKey())))
    }

    override fun applyEntity(entity: Enderman) {
        TODO("Not yet implemented")
    }
}