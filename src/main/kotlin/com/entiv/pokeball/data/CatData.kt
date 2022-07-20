package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.entity.Cat

class CatData(
    private val catType: Cat.Type,
    private val collarColor: DyeColor
) : EntityData<Cat>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setString("CatType", catType.name)
        nbtCompound.setString("CollarColor", collarColor.name)
    }

    override fun applyComponent(components: MutableList<Component>) {
        //TODO 汉化
        components.add(loreComponent("品种", catType.name))
        components.add(loreComponent("颜色", collarColor.name))
    }

    override fun applyEntity(entity: Cat) {
        entity.catType = catType
        entity.collarColor = collarColor
    }

    companion object : DataCreator<Cat>() {
        override val dataEntityClass = Cat::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            val catType = Cat.Type.valueOf(nbtCompound.getString("CatType"))
            val collarColor = DyeColor.valueOf(nbtCompound.getString("CollarColor"))
            return CatData(catType, collarColor)
        }

        override fun getEntityData(entity: Cat): EntityData<*> {
            return CatData(entity.catType, entity.collarColor)
        }

    }
}