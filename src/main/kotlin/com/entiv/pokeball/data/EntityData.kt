package com.entiv.pokeball.data

import net.kyori.adventure.text.Component
import org.bukkit.entity.Entity

abstract class EntityData<T : Entity> {

    abstract fun applyEntity(entity: T)

    abstract fun applyComponent(component: Component)

}