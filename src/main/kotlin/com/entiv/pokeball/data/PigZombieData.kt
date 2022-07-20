package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.PigZombie

class PigZombieData(
    private val isAngry : Boolean,
    private val angerLevel : Int,
): EntityData<PigZombie>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setBoolean("Angry", isAngry)
        nbtCompound.setInteger("AngerLevel", angerLevel)
    }

    override fun applyComponent(components: MutableList<Component>) {
        loreComponent("愤怒", if (isAngry) "是" else "否").also { components.add(it) }
        loreComponent("愤怒等级", angerLevel).also { components.add(it) }
    }

    override fun applyEntity(entity: PigZombie) {
        entity.isAngry = isAngry
        entity.anger = angerLevel
    }

    companion object : DataCreator<PigZombie>() {
        override val dataEntityClass = PigZombie::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            return PigZombieData(
                nbtCompound.getBoolean("Angry"),
                nbtCompound.getInteger("AngerLevel")
            )
        }

        override fun getEntityData(entity: PigZombie): EntityData<*> {
            return PigZombieData(
                entity.isAngry,
                entity.anger
            )
        }

    }
}