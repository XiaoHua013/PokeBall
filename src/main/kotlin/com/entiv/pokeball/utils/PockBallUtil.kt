package com.entiv.pokeball.utils

import com.entiv.pokeball.data.*
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.createInstance

fun ItemStack.isPokeBall(): Boolean {
    return getEntityType() != null
}

fun Entity.toPokeBallItem(): ItemStack {
    val itemStack = ItemStack(Material.STONE)

    EntityData::class.sealedSubclasses.forEach {
        val companionObjectInstance = it.companionObjectInstance as? DataCreator<*> ?: error("类 ${it.simpleName} 的伴生对象没有实现 DataCreator 接口")
        companionObjectInstance.fromEntity(this)?.processItemStack(itemStack)
    }

    return itemStack
}

fun ItemStack.getEntityType(): EntityType? {
    val name = NBTItem(this).getCompound("PokeBall")?.getString("entityType") ?: return null
    return EntityType.valueOf(name)
}