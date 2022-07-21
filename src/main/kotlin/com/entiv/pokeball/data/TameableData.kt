package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.AnimalTamer
import org.bukkit.entity.Tameable

object TameableData : DataWrapper<Tameable>() {
    override fun entityWriteToNbt(entity: Tameable, compound: NBTCompound) {
        compound.setBoolean("isTamed", entity.isTamed)
        compound.setString("owner", entity.owner?.name ?: "无")
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Tameable) {
        entity.isTamed = compound.getBoolean("isTamed")
        entity.owner = Bukkit.getOfflinePlayer(compound.getString("owner"))
    }

    override fun entityWriteToComponent(entity: Tameable, components: MutableList<Component>) {
        addComponent(components, "已驯服", if (entity.isTamed) "是" else "否")
        if (entity.isTamed) {
            addComponent(components, "主人", entity.owner?.name ?: "无")
        }
    }

}