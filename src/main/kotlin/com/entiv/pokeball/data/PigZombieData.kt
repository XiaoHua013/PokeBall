package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.PigZombie

object PigZombieData : DataWrapper<PigZombie>(PigZombie::class) {
    override fun entityWriteToNbt(entity: PigZombie, compound: NBTCompound) {
        compound.setBoolean("Angry", entity.isAngry)
        compound.setInteger("AngerLevel", entity.anger)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: PigZombie) {
        entity.isAngry = compound.getBoolean("Angry")
        entity.anger = compound.getInteger("AngerLevel")
    }

    override fun entityWriteToComponent(entity: PigZombie, components: MutableList<Component>) {
        addComponent(components, "愤怒", if (entity.isAngry) "是" else "否")
        addComponent(components, "愤怒等级", entity.anger)
    }

}