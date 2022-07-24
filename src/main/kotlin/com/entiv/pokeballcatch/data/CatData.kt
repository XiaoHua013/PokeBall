package com.entiv.pokeballcatch.data

import com.entiv.core.utils.translate
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.entity.Cat
import org.bukkit.entity.Cat.Type

object CatData : DataWrapper<Cat>(Cat::class) {
    override fun entityWriteToNbt(entity: Cat, compound: NBTCompound) {
        compound.setString("catType", entity.catType.name)
        compound.setString("catColor", entity.collarColor.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Cat) {
        entity.catType = Type.valueOf(compound.getString("catType"))
        entity.collarColor = DyeColor.valueOf(compound.getString("catColor"))
    }

    override fun entityWriteToComponent(entity: Cat, components: MutableList<Component>) {
        addComponent(components, "品种", entity.catType.translate())
        addComponent(components, "项圈颜色", entity.collarColor.translate())
    }


}