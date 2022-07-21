package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Enderman

class EndermanData(
    private val carriedBlock: BlockData?,
) : EntityData<Enderman>() {

    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setObject("carriedBlock", carriedBlock)
    }

    override fun applyComponent(components: MutableList<Component>) {
        if (carriedBlock != null) {
            components.add(loreComponent("持有方块", Component.translatable(carriedBlock.material.translationKey())))
        } else {
            components.add(loreComponent("持有方块", "无"))
        }
    }

    override fun applyEntity(entity: Enderman) {
        entity.carriedBlock = carriedBlock
    }

    companion object : DataCreator<Enderman>() {
        override val dataClass = Enderman::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            val carriedBlock = nbtCompound.getObject("carriedBlock", BlockData::class.java)
            return EndermanData(carriedBlock)
        }

        override fun getEntityData(entity: Enderman): EntityData<*> {
            val carriedBlock = entity.carriedBlock
            return EndermanData(carriedBlock)
        }

    }
}