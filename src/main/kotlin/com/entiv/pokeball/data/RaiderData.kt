package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Raider

object RaiderData : DataWrapper<Raider>() {
    override fun entityWriteToNbt(entity: Raider, compound: NBTCompound) {
        compound.setBoolean("isPatrolLeader", entity.isPatrolLeader)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Raider) {
        entity.isPatrolLeader = compound.getBoolean("isPatrolLeader")
    }

    override fun entityWriteToComponent(entity: Raider, components: MutableList<Component>) {
        addComponent(components, "队长", if (entity.isPatrolLeader) "是" else "否")
    }
}