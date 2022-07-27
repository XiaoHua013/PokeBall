package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

object InventoryData : DataWrapper<InventoryHolder>(InventoryHolder::class) {
    override fun entityWriteToNbt(entity: InventoryHolder, compound: NBTCompound) {
        val inventoryCompound = compound.getCompoundList("inventory")

        for (i in 0 until entity.inventory.size) {
            val item = entity.inventory.getItem(i)
            if (item != null) {
                inventoryCompound.addCompound().setItemStack("item", item)
            } else {
                inventoryCompound.addCompound().setItemStack("item", ItemStack(Material.AIR))
            }
        }
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: InventoryHolder) {
        val inventoryCompound = compound.getCompoundList("inventory")
        val contents = inventoryCompound.map { it.getItemStack("item") }.toTypedArray()

        entity.inventory.setContents(contents)
    }

    override fun entityWriteToComponent(entity: InventoryHolder, components: MutableList<Component>) {
        noNeedComponent()
    }
}