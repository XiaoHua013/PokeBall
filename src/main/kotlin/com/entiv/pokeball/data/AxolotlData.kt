package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Axolotl
import org.bukkit.entity.Axolotl.Variant

class AxolotlData(
    private val variant: Variant
) : EntityData<Axolotl>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setString("variant", variant.name)
    }

    override fun applyComponent(components: MutableList<Component>) {
        components.add(loreComponent("颜色", translateVariant(variant)))
    }

    private fun translateVariant(variant: Variant): String {
        return when (variant) {
            Variant.LUCY -> "粉红色"
            Variant.WILD -> "棕色"
            Variant.GOLD -> "金色"
            Variant.CYAN -> "青色"
            Variant.BLUE -> "蓝色"
        }
    }
    override fun applyEntity(entity: Axolotl) {
        entity.variant = variant
    }

    companion object : DataCreator<Axolotl>() {
        override val dataClass = Axolotl::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            val variant = Variant.valueOf(nbtCompound.getString("variant"))

            return AxolotlData(variant)
        }

        override fun getEntityData(entity: Axolotl): EntityData<*> {
            return AxolotlData(entity.variant)
        }

    }
}