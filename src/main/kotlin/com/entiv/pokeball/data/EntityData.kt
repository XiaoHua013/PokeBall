package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

sealed class EntityData<T> {

    abstract fun applyCompound(nbtCompound: NBTCompound)

    fun processEntity(entity: Entity) {
        castEntity(entity)?.let {
            applyEntity(it)
        }
    }

    fun processItemStack(itemStack: ItemStack) {

        val nbtItem = NBTItem(itemStack)
        nbtItem.addCompound("PokeBall").apply {
            applyCompound(this)
        }
        nbtItem.applyNBT(itemStack)

        val lore = itemStack.lore() ?: mutableListOf()
        applyComponent(lore)
        itemStack.lore(lore)
    }

    protected fun loreComponent(type: String, variable: Any): Component {
        val text = Component.text("$type: ", NamedTextColor.AQUA)
            .decoration(TextDecoration.ITALIC, false)

        return if (variable is Component) {
            text.append(variable.color(NamedTextColor.YELLOW))
        } else {
            text.append(Component.text(variable.toString(), NamedTextColor.YELLOW))
        }
    }

    protected abstract fun applyComponent(components: MutableList<Component>)

    protected abstract fun applyEntity(entity: T)

    private fun castEntity(entity: Entity): T? {
        @Suppress("UNCHECKED_CAST")
        return entity as? T
    }
}