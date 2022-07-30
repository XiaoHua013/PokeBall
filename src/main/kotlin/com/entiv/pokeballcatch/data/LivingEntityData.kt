package com.entiv.pokeballcatch.data

import com.entiv.core.debug.debug
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack
import kotlin.math.roundToInt

object LivingEntityData : DataWrapper<LivingEntity>(LivingEntity::class) {

    override val priority = 0

    override fun entityWriteToNbt(entity: LivingEntity, compound: NBTCompound) {
        val maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue ?: error("读取实体最大生命值错误")

        compound.setString("EntityType", entity.type.name)
        compound.setDouble("MaxHealth", maxHealth)
        compound.setDouble("Health", entity.health)

        val equipment = compound.getCompoundList("Armor")

        entity.equipment?.armorContents?.forEach {
            if (it != null) {
                equipment.addCompound().setItemStack("item", it)
            } else {
                equipment.addCompound().setItemStack("item", ItemStack(Material.AIR))
            }
        }

        entity.equipment?.itemInMainHand.let {
            compound.setItemStack("MainHand", it)
        }

        entity.equipment?.itemInOffHand.let {
            compound.setItemStack("OffHand", it)
        }
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: LivingEntity) {
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = compound.getDouble("MaxHealth")
        entity.health = compound.getDouble("Health")

        val equipmentCompound = compound.getCompoundList("Armor")
        val equipment = equipmentCompound.map { it.getItemStack("item") }.toTypedArray()

        entity.equipment?.armorContents = equipment

        println(compound.getCompound("MainHand"))
        entity.equipment?.setItemInMainHand(compound.getItemStack("MainHand"))
        entity.equipment?.setItemInOffHand(compound.getItemStack("OffHand"))
    }

    override fun entityWriteToComponent(entity: LivingEntity, components: MutableList<Component>) {
        val maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue ?: error("读取实体最大生命值错误")

        addComponent(components, "类型", Component.translatable(entity.type.translationKey()))
        addComponent(components, "血量", "${entity.health.roundToInt()} / ${maxHealth.roundToInt()}")

        entity.equipment?.armorContents?.forEachIndexed { index, item ->
            val name = when (index) {
                0 -> "头盔"
                1 -> "胸甲"
                2 -> "护腿"
                3 -> "靴子"
                else -> "错误"
            }

            if (item != null) {
                addComponent(components, name, Component.translatable(item.type.translationKey()))
            }
        }

        entity.equipment?.itemInMainHand?.let {
            if (it.type != Material.AIR) {
                addComponent(components, "主手", Component.translatable(it.type.translationKey()))
            }
        }

        entity.equipment?.itemInOffHand?.let {
            if (it.type != Material.AIR) {
                addComponent(components, "副手", Component.translatable(it.type.translationKey()))
            }
        }
    }
}