package com.entiv.pokeball

import com.entiv.pokeball.data.DataWrapper
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

class PokeBall(itemStack: ItemStack) {

    private val compound = NBTItem(itemStack).getCompound("PokeBall") ?: error("该物品不是一个有效的精灵球")
    private val entityType = EntityType.valueOf(compound.getString("EntityType"))

    fun spawnEntity(location: Location) {
        val world = location.world
        val entity = world.spawnEntity(location, entityType)

        println(DataWrapper::class.sealedSubclasses.size)

        DataWrapper::class.sealedSubclasses.forEach {
            it.objectInstance?.processEntity(entity, compound)
        }
    }

}