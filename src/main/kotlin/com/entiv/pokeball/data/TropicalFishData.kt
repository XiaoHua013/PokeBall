package com.entiv.pokeball.data

import com.entiv.pokeball.utils.translateDyeColor
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.entity.TropicalFish
import org.bukkit.entity.TropicalFish.Pattern

object TropicalFishData : DataWrapper<TropicalFish>() {
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
        addComponent(components, "身体颜色", translateDyeColor(entity.bodyColor))
        addComponent(components, "花纹", translatePattern(entity.pattern))
        addComponent(components, "花纹颜色", translateDyeColor(entity.patternColor))
    }

    private fun translatePattern(pattern: Pattern): String {
        return when (pattern) {
            Pattern.FLOPPER -> "飞翼类"
            Pattern.GLITTER -> "闪鳞类"
            Pattern.BETTY -> "背蒂类"
            Pattern.STRIPEY -> "条纹类"
            Pattern.BLOCKFISH -> "方身类"
            Pattern.CLAYFISH -> "陶鱼类"
            Pattern.KOB -> "石首类"
            Pattern.SNOOPER -> "窥伺类"
            Pattern.BRINELY -> "咸水类"
            Pattern.SUNSTREAK -> "日纹类"
            Pattern.DASHER -> "速跃类"
            Pattern.SPOTTY -> "多斑类"
        }
    }
}