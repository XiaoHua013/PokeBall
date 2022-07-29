package com.entiv.pokeballcatch.data

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

    fun Rabbit.Type.translate(): String {
        return when (this) {
            Rabbit.Type.BROWN -> "棕色"
            Rabbit.Type.WHITE -> "白色"
            Rabbit.Type.BLACK -> "黑色"
            Rabbit.Type.BLACK_AND_WHITE -> "黑白斑点"
            Rabbit.Type.GOLD -> "金黄色"
            Rabbit.Type.SALT_AND_PEPPER -> "棕白色"
            Rabbit.Type.THE_KILLER_BUNNY -> "杀手兔"
        }
    }


}