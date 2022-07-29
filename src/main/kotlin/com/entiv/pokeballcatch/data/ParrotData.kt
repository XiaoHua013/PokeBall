package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Parrot
import org.bukkit.entity.Parrot.Variant

object ParrotData : DataWrapper<Parrot>(Parrot::class) {
    override fun entityWriteToNbt(entity: Parrot, compound: NBTCompound) {
        compound.setString("Variant", entity.variant.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Parrot) {
        entity.variant = Variant.valueOf(compound.getString("Variant"))
    }

    override fun entityWriteToComponent(entity: Parrot, components: MutableList<Component>) {
        addComponent(components, "颜色", entity.variant.translate())
    }

    fun Parrot.Variant.translate(): String {
        return when (this) {
            Parrot.Variant.RED -> "红色"
            Parrot.Variant.GREEN -> "绿色"
            Parrot.Variant.BLUE -> "蓝色"
            Parrot.Variant.CYAN -> "青色"
            Parrot.Variant.GRAY -> "灰色"
        }
    }


}