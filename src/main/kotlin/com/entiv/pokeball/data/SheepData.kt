package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Sheep

class SheepData(
    val isSheared: Boolean,
    ) : EntityData<Sheep>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setBoolean("Sheared", isSheared)
    }

    override fun applyComponent(components: MutableList<Component>) {
        loreComponent("被剪毛", if (isSheared) "是" else "否").apply {
            components.add(this)
        }
    }

    override fun applyEntity(entity: Sheep) {
        entity.isSheared = isSheared
    }

    companion object : DataCreator<Sheep>() {
        override val dataEntityClass = Sheep::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            return SheepData(nbtCompound.getBoolean("Sheared"))
        }

        override fun getEntityData(entity: Sheep): EntityData<*> {
            return SheepData(entity.isSheared)
        }

    }
}