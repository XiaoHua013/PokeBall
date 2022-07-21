package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Raider

class RaiderData(
    private val isPatrolLeader: Boolean
) : EntityData<Raider>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setBoolean("isPatrolLeader", isPatrolLeader)
    }

    override fun applyComponent(components: MutableList<Component>) {
        loreComponent("队长", if (isPatrolLeader) "是" else "否").apply {
            components.add(this)
        }
    }

    override fun applyEntity(entity: Raider) {
        entity.isPatrolLeader = isPatrolLeader
    }

    companion object : DataCreator<Raider>() {
        override val dataClass = Raider::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            return RaiderData(nbtCompound.getBoolean("isPatrolLeader"))
        }

        override fun getEntityData(entity: Raider): EntityData<*> {
            return RaiderData(entity.isPatrolLeader)
        }
    }
}