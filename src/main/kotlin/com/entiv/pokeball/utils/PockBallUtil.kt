package com.entiv.pokeball.utils

import com.entiv.pokeball.data.DataWrapper
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

fun ItemStack.isPokeBall() = NBTItem(this).getString("PockBall") != null

fun Entity.toPokeBallItem(): ItemStack {
    val itemStack = ItemStack(Material.STONE)

    DataWrapper::class.sealedSubclasses.forEach {
        it.objectInstance?.processItemStack(itemStack, this)
    }

    return itemStack
}

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