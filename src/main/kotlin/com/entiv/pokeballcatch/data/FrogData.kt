package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Frog
import org.bukkit.entity.Frog.Variant
object FrogData : DataWrapper<Frog>(Frog::class) {
    override fun entityWriteToNbt(entity: Frog, compound: NBTCompound) {
        compound.setString("Variant", entity.variant.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Frog) {
        entity.variant = Variant.valueOf(compound.getString("Variant"))
    }

    override fun entityWriteToComponent(entity: Frog, components: MutableList<Component>) {
        addComponent(components, "肤色", entity.variant.translate())
    }

    fun Variant.translate(): String {
        return when (this) {
            Variant.TEMPERATE -> "绿色"
            Variant.WARM -> "橙色"
            Variant.COLD -> "白色"
        }
    }
}