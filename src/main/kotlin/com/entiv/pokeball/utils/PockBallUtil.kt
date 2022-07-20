package com.entiv.pokeball.utils

import com.entiv.pokeball.data.AbstractHorseData
import com.entiv.pokeball.data.AgeableData
import com.entiv.pokeball.data.LivingEntityData
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

fun ItemStack.isPokeBall(): Boolean {
    return getEntityType() != null
}

fun Entity.toPokeBallItem(): ItemStack {
    val itemStack = ItemStack(Material.STONE)

    LivingEntityData.fromEntity(this)?.processItemStack(itemStack)
    AbstractHorseData.fromEntity(this)?.processItemStack(itemStack)
    AgeableData.fromEntity(this)?.processItemStack(itemStack)

    return itemStack
}

fun ItemStack.getEntityType(): EntityType? {
    return NBTItem(this).getObject("entityType", EntityType::class.java)
}