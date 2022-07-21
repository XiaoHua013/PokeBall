package com.entiv.pokeball.utils

import com.entiv.pokeball.data.*
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack
import kotlin.reflect.full.companionObjectInstance

fun ItemStack.isPokeBall(): Boolean {
    return getEntityType() != null
}

fun Entity.toPokeBallItem(): ItemStack {
    val itemStack = ItemStack(Material.STONE)

    EntityData::class.sealedSubclasses.forEach {
        val companionObject =
            it.companionObjectInstance as? DataCreator<*> ?: error("类 ${it.simpleName} 的伴生对象没有实现 DataCreator 接口")
        companionObject.fromEntity(this)?.processItemStack(itemStack)
    }

    return itemStack
}

fun ItemStack.getEntityType(): EntityType? {
    val name = NBTItem(this).getCompound("PokeBall")?.getString("entityType") ?: return null
    return EntityType.valueOf(name)
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