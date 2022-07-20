package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import de.tr7zw.nbtapi.NBTCompoundList
import de.tr7zw.nbtapi.NBTContainer
import de.tr7zw.nbtapi.NBTItem
import de.tr7zw.nbtapi.NBTList
import net.kyori.adventure.text.Component
import org.bukkit.attribute.Attribute
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import java.util.Arrays

class LivingEntityData(
    private val entityType: EntityType,
    private val health: Double,
    private val maxHealth: Double,

    private val customName: Component? = null,
    private val contents: Array<ItemStack?>? = null
) : EntityData<LivingEntity>() {

    override fun applyEntity(entity: LivingEntity) {
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = maxHealth
        entity.health = health

        customName?.let {
            entity.customName(it)
            entity.isCustomNameVisible = true
        }

        if (entity is InventoryHolder) {
            println(123)
            entity.inventory.contents = contents ?: emptyArray()
        }
    }

    override fun applyComponent(components: MutableList<Component>) {
        components.add(loreComponent("类型", Component.translatable(entityType.translationKey())))
        components.add(loreComponent("血量", "$health / $maxHealth"))

        customName?.let {
            components.add(loreComponent("名称", it))
        }
    }

    override fun applyItemNBT(nbtItem: NBTItem) {
        nbtItem.setObject("entityType", entityType)
        nbtItem.setDouble("health", health)
        nbtItem.setDouble("maxHealth", maxHealth)

        customName?.let {
            nbtItem.setObject("customName", it)
        }

        contents?.let {
            val inventoryCompound = nbtItem.getCompoundList("inventory")

            for (content in contents) {
                if (content != null) {
                    println("set item: ${content.type}")
                    inventoryCompound.addCompound().setItemStack("item", content)
                }
            }
        }
    }

    companion object : DataCreator<LivingEntity>() {

        override val dataEntityClass = LivingEntity::class.java

        override fun getEntityData(entity: LivingEntity): EntityData<*> {
            return LivingEntityData(
                entity.type,
                entity.health,
                entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue ?: error("最大血量读取异常!"),
                entity.customName(),
                (entity as? InventoryHolder)?.inventory?.contents
            )
        }

        override fun getEntityData(itemStack: ItemStack): EntityData<*> {
            val nbtItem = NBTItem(itemStack)

            val type = nbtItem.getObject("entityType", EntityType::class.java)
            val health = nbtItem.getDouble("health")
            val maxHealth = nbtItem.getDouble("maxHealth")
            val name = nbtItem.getObject("customName", Component::class.java)

            val inventoryCompound = nbtItem.getCompoundList("inventory")
            val inventory = inventoryCompound
                .map {
                    NBTItem.convertNBTtoItem(it.getCompound("item"))
                }
                .toTypedArray()

            return LivingEntityData(type, health, maxHealth, name, inventory)
        }
    }
}



