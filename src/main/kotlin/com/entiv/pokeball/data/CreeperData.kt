package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.entity.Cat
import org.bukkit.entity.Creeper

class CreeperData(
    private val powered: Boolean
) : EntityData<Creeper>() {

    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setBoolean("powered", true)
    }

    override fun applyComponent(components: MutableList<Component>) {
        loreComponent("闪电充能", if (powered) "是" else "否")
    }

    override fun applyEntity(entity: Creeper) {
        entity.isPowered = powered
    }

    companion object : DataCreator<Creeper>() {
        override val dataEntityClass = Creeper::class.java

        override fun getEntityData(nbtItem: NBTItem): EntityData<*> {
            return CreeperData(nbtItem.getBoolean("powered"))
        }

        override fun getEntityData(entity: Creeper): EntityData<*> {
            return CreeperData(entity.isPowered)
        }
    }
}