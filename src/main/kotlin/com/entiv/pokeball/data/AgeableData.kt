package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Ageable

class AgeableData(
    private val age: Int,
    private val adult: Boolean,
) : EntityData<Ageable>() {

    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setInteger("age", age)
        nbtCompound.setBoolean("adult", adult)
    }

    override fun applyComponent(components: MutableList<Component>) {
        components.add(loreComponent("成年", if (adult) "是" else "否"))
    }

    override fun applyEntity(entity: Ageable) {
        entity.age = age
    }

    companion object : DataCreator<Ageable>() {

        override val dataClass = Ageable::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {

            return AgeableData(
                nbtCompound.getInteger("age"),
                nbtCompound.getBoolean("adult")
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