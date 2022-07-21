package com.entiv.pokeball

import com.entiv.pokeball.data.*
import com.entiv.pokeball.utils.getEntityType
import com.entiv.pokeball.utils.toPokeBallItem
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.createInstance

class PokeBall(private val itemStack: ItemStack) {

    private val entityType = itemStack.getEntityType() ?: error("itemStack is not a valid pokeball")

    fun spawnEntity(location: Location) {
        val world = location.world
        val entity = world.spawnEntity(location, entityType)

        EntityData::class.sealedSubclasses.forEach {
            val companionObjectInstance = it.companionObjectInstance as? DataCreator<*> ?: error("类 ${it.simpleName} 的伴生对象没有实现 DataCreator 接口")
            companionObjectInstance.fromItemStack(itemStack)?.processEntity(entity)
        }
    }
}