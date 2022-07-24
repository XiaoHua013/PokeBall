package com.entiv.pokeballcatch.data

import com.entiv.core.utils.translate
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Rabbit

object RabbitData : DataWrapper<Rabbit>(Rabbit::class) {
    override fun entityWriteToNbt(entity: Rabbit, compound: NBTCompound) {
        compound.setString("RabbitType", entity.rabbitType.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Rabbit) {
        entity.rabbitType = Rabbit.Type.valueOf(compound.getString("RabbitType"))
    }

    override fun entityWriteToComponent(entity: Rabbit, components: MutableList<Component>) {
        addComponent(components, "品种", entity.rabbitType.translate())
    }



}