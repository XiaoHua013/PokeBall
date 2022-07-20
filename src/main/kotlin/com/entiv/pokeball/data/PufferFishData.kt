package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.PufferFish

class PufferFishData(private val puffState: Int) : EntityData<PufferFish>() {

    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setInteger("PuffState", puffState)
    }

    override fun applyComponent(components: MutableList<Component>) {
        loreComponent("膨胀大小", puffState).also { components.add(it) }
    }

    override fun applyEntity(entity: PufferFish) {
        entity.puffState = puffState
    }

    companion object : DataCreator<PufferFish>() {
        override val dataEntityClass = PufferFish::class.java

        override fun getEntityData(entity: PufferFish): EntityData<*> {
            return PufferFishData(entity.puffState)
        }

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            return PufferFishData(nbtCompound.getInteger("PuffState"))
        }
    }
}
