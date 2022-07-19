package com.entiv.pokeball

import com.entiv.pokeball.data.AbstractHorseData
import com.entiv.pokeball.data.LivingEntityData
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity

class EntityProcessor {

    fun process(entity: Entity) {

        if (entity is LivingEntity) {
            LivingEntityData.fromEntity(entity).applyEntity(entity)
        }

        if (entity is AbstractHorse) {
            AbstractHorseData.fromEntity(entity).applyEntity(entity)
        }
    }

}