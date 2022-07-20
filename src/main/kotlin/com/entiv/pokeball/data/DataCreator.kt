package com.entiv.pokeball.data

import com.entiv.pokeball.utils.getEntityType
import com.entiv.pokeball.utils.isPokeBall
import de.tr7zw.nbtapi.NBTCompound
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

abstract class DataCreator<T: Entity> {

    abstract val dataEntityClass: Class<T>

    protected abstract fun getEntityData(entity: T): EntityData<*>

    protected abstract fun getEntityData(nbtCompound: NBTCompound): EntityData<*>

    fun fromEntity(entity: Entity): EntityData<*>? {

        if (!dataEntityClass.isAssignableFrom(entity.javaClass)) {
            return null
        }

        @Suppress("UNCHECKED_CAST")
        return getEntityData(entity as T)
    }

    fun fromItemStack(itemStack: ItemStack): EntityData<*>? {
        if (!itemStack.isPokeBall()) {
            return null
        }

        val entityType = itemStack.getEntityType() ?: return null
        val entityClass = entityType.entityClass ?: return null

        if (!dataEntityClass.isAssignableFrom(entityClass)) {
            return null
        }

        return getEntityData(NBTItem(itemStack).getCompound("PokeBall"))
    }
}