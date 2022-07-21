package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Rabbit

class RabbitData(
    private val type: Rabbit.Type
) : EntityData<Rabbit>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setString("RabbitType", type.name)
    }

    override fun applyComponent(components: MutableList<Component>) {
        loreComponent("品种", type.name).also { components.add(it) }
    }

    override fun applyEntity(entity: Rabbit) {
        entity.rabbitType = type
    }

    companion object : DataCreator<Rabbit>() {
        override val dataClass = Rabbit::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            val type = Rabbit.Type.valueOf(nbtCompound.getString("RabbitType"))
            return RabbitData(type)
        }

        override fun getEntityData(entity: Rabbit): EntityData<*> {
            val type = entity.rabbitType
            return RabbitData(type)
        }
    }
}