package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.PufferFish

//class PufferFishData(private val puffState: Int) : EntityData<PufferFish>() {
//
//    override fun applyCompound(compound: NBTCompound) {
//        compound.setInteger("PuffState", puffState)
//    }
//
//    override fun applyComponent(components: MutableList<Component>) {
//        loreComponent("膨胀大小", puffState).also { components.add(it) }
//    }
//
//    override fun applyEntity(entity: PufferFish) {
//        entity.puffState = puffState
//    }
//
//
//}

object PufferFishData