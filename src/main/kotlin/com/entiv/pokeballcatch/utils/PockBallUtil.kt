package com.entiv.pokeballcatch.utils

import com.entiv.core.debug.debug
import com.entiv.core.plugin.InsekiPlugin
import com.entiv.pokeballcatch.data.DataWrapper
import com.google.common.base.Enums
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.DyeColor
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

val dataWrappers = DataWrapper::class.sealedSubclasses
    .mapNotNull { it.objectInstance }
    .sortedBy { it.priority }
    .toList()

fun ItemStack.isPokeBall() = NBTItem(this).getCompound("PokeBall") != null

fun translateDyeColor(dyeColor: DyeColor): String {
    return when (dyeColor) {
        DyeColor.BLACK -> "黑色"
        DyeColor.RED -> "红色"
        DyeColor.GREEN -> "绿色"
        DyeColor.BROWN -> "棕色"
        DyeColor.BLUE -> "蓝色"
        DyeColor.PURPLE -> "紫色"
        DyeColor.CYAN -> "青色"
        DyeColor.LIGHT_GRAY -> "淡灰色"
        DyeColor.GRAY -> "灰色"
        DyeColor.PINK -> "粉红色"
        DyeColor.LIME -> "黄绿色"
        DyeColor.YELLOW -> "黄色"
        DyeColor.LIGHT_BLUE -> "淡蓝色"
        DyeColor.MAGENTA -> "品红色"
        DyeColor.ORANGE -> "橙色"
        DyeColor.WHITE -> "白色"
    }
}

fun Player.safePlaySound(location: Location, soundName: String) {
    val sound = Enums.getIfPresent(Sound::class.java, soundName).orNull()

    if (sound != null) {
        playSound(location, sound, 1f, 1f)
    } else {
        debug("声音 $soundName 不存在，请检查配置文件！")
    }
}

val config get() = InsekiPlugin.instance.config
