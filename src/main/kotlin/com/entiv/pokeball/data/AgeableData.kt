package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Ageable
import org.bukkit.entity.Entity
import org.bukkit.inventory.AbstractHorseInventory
import org.bukkit.inventory.ItemStack
import kotlin.math.roundToInt

class AgeableData(
    val age: Int,
    val adult: Boolean,
) : EntityData<Ageable>() {

    override fun applyItemNBT(nbtItem: NBTItem) {
        nbtItem.setInteger("age", age)
        nbtItem.setBoolean("adult", adult)
    }

    override fun applyComponent(components: MutableList<Component>) {
        components.add(loreComponent("成年", if (adult) "是" else "否"))
    }

    override fun applyEntity(entity: Ageable) {
        entity.age = age
    }

    companion object : DataCreator<Ageable>() {

        override val dataEntityClass = Ageable::class.java

        override fun getEntityData(itemStack: ItemStack): EntityData<*> {
            val nbtItem = NBTItem(itemStack)

            return AgeableData(
                nbtItem.getInteger("age"),
                nbtItem.getBoolean("adult")
            )
        }

        override fun getEntityData(entity: Ageable): EntityData<*> {
            return AgeableData(
                entity.age,
                entity.isAdult
            )
        }
    }
}