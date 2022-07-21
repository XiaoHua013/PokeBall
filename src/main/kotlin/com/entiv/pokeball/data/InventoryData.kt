package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class InventoryData(
    private val contents: Array<ItemStack?>? = null
) : EntityData<InventoryHolder>() {

    override fun applyCompound(nbtCompound: NBTCompound) {
        contents?.let {
            val inventoryCompound = nbtCompound.getCompoundList("inventory")

            for (content in contents) {
                if (content != null) {
                    inventoryCompound.addCompound().setItemStack("item", content)
                }
            }
        }
    }

    override fun applyComponent(components: MutableList<Component>) {
    }

    override fun applyEntity(entity: InventoryHolder) {
        entity.inventory.contents = contents ?: emptyArray()
    }

    companion object : DataCreator<InventoryHolder>() {
        override val dataEntityClass = InventoryHolder::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            val inventoryCompound = nbtCompound.getCompoundList("inventory")
            val inventory = inventoryCompound
                .map {
                    NBTItem.convertNBTtoItem(it.getCompound("item"))
                }
                .toTypedArray()

            return InventoryData(inventory)
        }

        override fun getEntityData(entity: InventoryHolder): EntityData<*> {
            return InventoryData(entity.inventory.contents)
        }
    }
}