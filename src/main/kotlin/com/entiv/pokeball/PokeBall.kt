package com.entiv.pokeball

import com.entiv.pokeball.data.AbstractHorseData
import com.entiv.pokeball.data.AgeableData
import com.entiv.pokeball.data.LivingEntityData
import com.entiv.pokeball.utils.getEntityType
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

class PokeBall(private val itemStack: ItemStack) {

    private val entityType = itemStack.getEntityType() ?: error("itemStack is not a valid pokeball")

    fun spawnEntity(location: Location) {
        val world = location.world
        val entity = world.spawnEntity(location, entityType)

        LivingEntityData.fromItemStack(itemStack)?.processEntity(entity)
        AbstractHorseData.fromItemStack(itemStack)?.processEntity(entity)
        AgeableData.fromItemStack(itemStack)?.processEntity(entity)
    }
}