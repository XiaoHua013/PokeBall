package com.entiv.pokeballcatch.data

import com.entiv.core.utils.translate
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Axolotl
import org.bukkit.entity.Axolotl.Variant

object AxolotlData : DataWrapper<Axolotl>(Axolotl::class) {
    override fun entityWriteToNbt(entity: Axolotl, compound: NBTCompound) {
        compound.setString("variant", entity.variant.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Axolotl) {
        entity.variant = Variant.valueOf(compound.getString("variant"))
    }

    override fun entityWriteToComponent(entity: Axolotl, components: MutableList<Component>) {
        addComponent(components, "颜色", entity.variant.translate())
    }

}