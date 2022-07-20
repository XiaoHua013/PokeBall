package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.entity.Cat

class CatData(
    private val catType: Cat.Type,
    private val collarColor: DyeColor
) : EntityData<Cat>() {
    override fun applyItemNBT(nbtItem: NBTItem) {
        nbtItem.setString("CatType", catType.name)
        nbtItem.setString("CollarColor", collarColor.name)
    }

    override fun applyComponent(components: MutableList<Component>) {
        components.add(loreComponent("品种", catType.name))
        components.add(loreComponent("颜色", collarColor.name))
    }

    override fun applyEntity(entity: Cat) {
        entity.catType = catType
        entity.collarColor = collarColor
    }

    companion object : DataCreator<Cat>() {
        override val dataEntityClass = Cat::class.java

        override fun getEntityData(nbtItem: NBTItem): EntityData<*> {
            val catType = Cat.Type.valueOf(nbtItem.getString("CatType"))
            val collarColor = DyeColor.valueOf(nbtItem.getString("CollarColor"))
            return CatData(catType, collarColor)
        }

        override fun getEntityData(entity: Cat): EntityData<*> {
            return CatData(entity.catType, entity.collarColor)
        }

    }
}