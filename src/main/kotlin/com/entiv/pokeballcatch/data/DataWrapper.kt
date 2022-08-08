package com.entiv.pokeballcatch.data

import com.entiv.pokeballcatch.utils.Lang
import com.entiv.pokeballcatch.utils.config
import de.tr7zw.nbtapi.NBTCompound
import de.tr7zw.nbtapi.NBTItem
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

sealed class DataWrapper<T : Any>(private val clazz: KClass<T>) {

    // 优先级, 越小越前面
    open val priority: Int = 100

    /*
    * 将实体应用到 nbt 上
    * */
    protected abstract fun entityWriteToNbt(entity: T, compound: NBTCompound)

    /**
     * 将 nbt 应用到实体上
     */
    protected abstract fun nbtWriteToEntity(compound: NBTCompound, entity: T)

    protected abstract fun entityWriteToComponent(entity: T, components: MutableList<Component>)

    private fun canCastEntity(entity: Entity) = entity::class.isSubclassOf(clazz)

    fun processEntity(entity: Entity, compound: NBTCompound) {

        castEntity(entity)?.let {
            try {
                nbtWriteToEntity(compound, it)
            } catch (_: NoSuchMethodException) {
            }
        }
    }

    fun processItemStack(itemStack: ItemStack, entity: Entity) {

        val castEntity = castEntity(entity) ?: return
        val nbtItem = NBTItem(itemStack)

        nbtItem.addCompound("PokeBall").apply {
            try {
                entityWriteToNbt(castEntity, this)
            } catch (_: NoSuchMethodException) {

            }
        }

        nbtItem.applyNBT(itemStack)

        val lore = itemStack.lore() ?: mutableListOf()
        try {
            entityWriteToComponent(castEntity, lore)
        } catch (_: NoSuchMethodException) {
        }
        itemStack.lore(lore)
    }

    protected fun addComponent(components: MutableList<Component>, type: String, variable: Any) {
        val text = Component.text()
            .decoration(TextDecoration.ITALIC, false)
            .append(Component.text(type))

        val variableComponent = Component.text()

        if (variable is Component) {
            variableComponent.append(variable)
        } else {
            variableComponent.append(Component.text(variable.toString()))
        }

        Lang.getComponent("基础设置.生物信息描述",
            Placeholder.component("type", text),
            Placeholder.component("variable", variableComponent)
        )?.let {
            components.add(it)
        }
    }

    private fun castEntity(entity: Entity): T? {
        @Suppress("UNCHECKED_CAST")
        return if (canCastEntity(entity)) {
            entity as T
        } else {
            null
        }
    }



    protected fun noNeedComponent() {}
}