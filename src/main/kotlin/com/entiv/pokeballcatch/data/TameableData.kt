package com.entiv.pokeballcatch.data

import com.entiv.core.debug.debug
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Tameable

object TameableData : DataWrapper<Tameable>(Tameable::class) {
    override val priority = 30

    override fun entityWriteToNbt(entity: Tameable, compound: NBTCompound) {
        compound.setBoolean("isTamed", entity.isTamed)

        entity.owner?.name.let {
            compound.setString("owner", it)
        }
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Tameable) {

        val owner = compound.getString("owner")

        if (owner != null && owner.isNotEmpty()) {
            entity.owner = Bukkit.getOfflinePlayer(owner)
            debug("已设置主人: $owner")
        }

        entity.isTamed = compound.getBoolean("isTamed")
        debug("生物已驯服：${entity.isTamed}")
    }

    override fun entityWriteToComponent(entity: Tameable, components: MutableList<Component>) {
        addComponent(components, "已驯服", if (entity.isTamed) "是" else "否")
        if (entity.isTamed) {
            addComponent(components, "主人", entity.owner?.name ?: "无")
        }
    }

}