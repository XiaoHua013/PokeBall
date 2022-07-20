package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import org.bukkit.entity.Axolotl
import org.bukkit.inventory.ItemStack

class AxolotlData(
    private val variant: Axolotl.Variant
) : EntityData<Axolotl>() {
    override fun applyItemNBT(nbtItem: NBTItem) {
        nbtItem.setString("variant", variant.name)
    }

    override fun applyComponent(components: MutableList<Component>) {
        components.add(loreComponent("品种", variant.name))
    }

    override fun applyEntity(entity: Axolotl) {
        entity.variant = variant
    }

    companion object : DataCreator<Axolotl>() {
        override val dataEntityClass = Axolotl::class.java

        override fun getEntityData(nbtItem: NBTItem): EntityData<*> {
            val variant = Axolotl.Variant.valueOf(nbtItem.getString("variant"))

            return AxolotlData(variant)
        }

        override fun getEntityData(entity: Axolotl): EntityData<*> {
            return AxolotlData(entity.variant)
        }

    }
}