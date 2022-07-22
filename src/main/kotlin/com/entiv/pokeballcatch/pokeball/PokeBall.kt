package com.entiv.pokeballcatch.pokeball

import com.entiv.core.debug.LagCatcher
import com.entiv.pokeballcatch.utils.dataWrappers
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

        LagCatcher.performanceCheck("item to entity", 0) {
            dataWrappers.forEach {
                it.processEntity(entity, compound)
            }
        }
    }
}