package com.entiv.pokeballcatch.data

import com.entiv.core.utils.translate
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



}