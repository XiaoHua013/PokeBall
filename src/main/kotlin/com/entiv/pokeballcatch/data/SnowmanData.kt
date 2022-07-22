package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Snowman
object SnowmanData : DataWrapper<Snowman>(Snowman::class) {
    override fun entityWriteToNbt(entity: Snowman, compound: NBTCompound) {
        compound.setBoolean("isDerp", entity.isDerp)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Snowman) {
        entity.isDerp = compound.getBoolean("isDerp")
    }

    override fun entityWriteToComponent(entity: Snowman, components: MutableList<Component>) {
        addComponent(components, "南瓜头", if (entity.isDerp) "有" else "无")
    }
}