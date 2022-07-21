package com.entiv.pokeball.data

import com.entiv.pokeball.utils.translateDyeColor
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.entity.TropicalFish
import org.bukkit.entity.TropicalFish.Pattern

//class TropicalFishData(
//    val bodyColor: DyeColor,
//    val pattern: Pattern,
//    val patternColor: DyeColor,
//
//    ) : EntityData<TropicalFish>() {
//    override fun applyCompound(compound: NBTCompound) {
//        compound.setString("BodyColor", bodyColor.name)
//        compound.setString("Pattern", pattern.name)
//        compound.setString("PatternColor", patternColor.name)
//    }
//
//    override fun applyComponent(components: MutableList<Component>) {
//        components.add(
//            loreComponent("身体颜色", translateDyeColor(bodyColor))
//        )
//        components.add(
//            loreComponent("花纹", tranPattern(pattern))
//        )
//        components.add(
//            loreComponent("花纹颜色", translateDyeColor(patternColor))
//        )
//    }
//
//    private fun tranPattern(pattern: Pattern):String {
//        return when (pattern) {
//            Pattern.FLOPPER -> "飞翼类"
//            Pattern.GLITTER -> "闪鳞类"
//            Pattern.BETTY -> "背蒂类"
//            Pattern.STRIPEY -> "条纹类"
//            Pattern.BLOCKFISH -> "方身类"
//            Pattern.CLAYFISH -> "陶鱼类"
//            Pattern.KOB -> "石首类"
//            Pattern.SNOOPER -> "窥伺类"
//            Pattern.BRINELY -> "咸水类"
//            Pattern.SUNSTREAK -> "日纹类"
//            Pattern.DASHER -> "速跃类"
//            Pattern.SPOTTY  -> "多斑类"
//        }
//    }
//
//    override fun applyEntity(entity: TropicalFish) {
//        entity.bodyColor = bodyColor
//        entity.pattern = pattern
//        entity.patternColor = patternColor
//    }
//
//}
object TropicalFishData