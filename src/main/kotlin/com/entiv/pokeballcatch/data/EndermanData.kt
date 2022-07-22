package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Enderman


object EndermanData : DataWrapper<Enderman>(Enderman::class) {
    override fun entityWriteToNbt(entity: Enderman, compound: NBTCompound) {
        compound.setObject("carriedBlock", entity.carriedBlock)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Enderman) {
        entity.carriedBlock = compound.getObject("carriedBlock", BlockData::class.java)
    }

    override fun entityWriteToComponent(entity: Enderman, components: MutableList<Component>) {
        addComponent(components, "持有方块", entity.carriedBlock?.material?.translationKey() ?: "无")
    }

}