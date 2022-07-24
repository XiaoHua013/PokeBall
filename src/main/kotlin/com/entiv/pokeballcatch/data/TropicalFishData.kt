package com.entiv.pokeballcatch.data

import com.entiv.core.utils.translate
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.entity.TropicalFish
import org.bukkit.entity.TropicalFish.Pattern

object TropicalFishData : DataWrapper<TropicalFish>(TropicalFish::class) {
    override fun entityWriteToNbt(entity: TropicalFish, compound: NBTCompound) {
        compound.setString("BodyColor", entity.bodyColor.name)
        compound.setString("Pattern", entity.pattern.name)
        compound.setString("PatternColor", entity.patternColor.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: TropicalFish) {
        entity.bodyColor = DyeColor.valueOf(compound.getString("BodyColor"))
        entity.pattern = Pattern.valueOf(compound.getString("Pattern"))
        entity.patternColor = DyeColor.valueOf(compound.getString("PatternColor"))
    }

    override fun entityWriteToComponent(entity: TropicalFish, components: MutableList<Component>) {
        addComponent(components, "身体颜色", entity.bodyColor.translate())
        addComponent(components, "花纹", entity.pattern.translate())
        addComponent(components, "花纹颜色", entity.patternColor.translate())
    }

}