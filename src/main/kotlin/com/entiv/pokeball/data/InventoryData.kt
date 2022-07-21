package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

object InventoryData : DataWrapper<InventoryHolder>() {
    override fun entityWriteToNbt(entity: InventoryHolder, compound: NBTCompound) {
        val inventoryCompound = compound.getCompoundList("inventory")

        for (i in 0 until entity.inventory.size) {
            val item = entity.inventory.getItem(i)
            if (item != null) {
                inventoryCompound.addCompound().setItemStack("item", item)
            }
        }
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: InventoryHolder) {
        val inventoryCompound = compound.getCompoundList("inventory")
        val contents = inventoryCompound.map { NBTItem.convertNBTtoItem(it.getCompound("item")) }.toTypedArray()

        entity.inventory.contents = contents
    }

    override fun entityWriteToComponent(entity: InventoryHolder, components: MutableList<Component>) {
        noNeedComponent()
    }
}