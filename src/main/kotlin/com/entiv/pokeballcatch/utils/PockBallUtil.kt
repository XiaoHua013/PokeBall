package com.entiv.pokeballcatch.utils

import com.entiv.core.debug.LagCatcher
import com.entiv.pokeballcatch.data.DataWrapper
import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.net.URL
import java.util.*

val dataWrappers = DataWrapper::class.sealedSubclasses
    .mapNotNull { it.objectInstance }
    .sortedBy { it.priority }
    .toList()

fun Entity.toPokeBallItem(): ItemStack {
    val itemStack = ItemStack(Material.PLAYER_HEAD)
    val skullMeta = itemStack.itemMeta as SkullMeta

    skullMeta.displayName(
        Component.text("精灵球", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false)
    )

    LagCatcher.performanceCheck("get skull", 0) {
        val profile = Bukkit.getServer().createProfile(UUID.randomUUID())
        val textures = profile.textures

        textures.skin = URL("https://textures.minecraft.net/texture/93e68768f4fab81c94df735e205c3b45ec45a67b558f3884479a62dd3f4bdbf8")
        profile.setTextures(textures)
        skullMeta.playerProfile = profile
        itemStack.itemMeta = skullMeta
    }

    LagCatcher.performanceCheck("entity to item", 0) {
        dataWrappers.forEach { it.processItemStack(itemStack, this) }
    }

    return itemStack
}

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