package com.entiv.pokeballcatch.data

import com.entiv.core.utils.translate
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Horse

object HorseData : DataWrapper<Horse>(Horse::class) {
    override fun entityWriteToNbt(entity: Horse, compound: NBTCompound) {
        compound.setString("Color", entity.color.name)
        compound.setString("Style", entity.style.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Horse) {
        entity.color = Horse.Color.valueOf(compound.getString("Color"))
        entity.style = Horse.Style.valueOf(compound.getString("Style"))
    }

    override fun entityWriteToComponent(entity: Horse, components: MutableList<Component>) {
        addComponent(components, "颜色", entity.color.translate())
        addComponent(components, "花纹", entity.style.translate())
    }
}