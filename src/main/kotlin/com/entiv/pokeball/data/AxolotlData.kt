package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Axolotl

class AxolotlData(
    private val variant: Axolotl.Variant
) : EntityData<Axolotl>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setString("variant", variant.name)
    }

    override fun applyComponent(components: MutableList<Component>) {
        // TODO 汉化
        components.add(loreComponent("品种", variant.name))
    }

    override fun applyEntity(entity: Axolotl) {
        entity.variant = variant
    }

    companion object : DataCreator<Axolotl>() {
        override val dataEntityClass = Axolotl::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            val variant = Axolotl.Variant.valueOf(nbtCompound.getString("variant"))

            return AxolotlData(variant)
        }

        override fun getEntityData(entity: Axolotl): EntityData<*> {
            return AxolotlData(entity.variant)
        }

    }
}