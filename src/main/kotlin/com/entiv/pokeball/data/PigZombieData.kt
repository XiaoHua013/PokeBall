package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.PigZombie

//class PigZombieData(
//    private val isAngry : Boolean,
//    private val angerLevel : Int,
//): EntityData<PigZombie>() {
//    override fun applyCompound(compound: NBTCompound) {
//        compound.setBoolean("Angry", isAngry)
//        compound.setInteger("AngerLevel", angerLevel)
//    }
//
//    override fun applyComponent(components: MutableList<Component>) {
//        loreComponent("愤怒", if (isAngry) "是" else "否").also { components.add(it) }
//        loreComponent("愤怒等级", angerLevel).also { components.add(it) }
//    }
//
//    override fun applyEntity(entity: PigZombie) {
//        entity.isAngry = isAngry
//        entity.anger = angerLevel
//    }
//
//
//}
object PigZombieData : DataWrapper<PigZombie>() {
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