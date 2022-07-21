package com.entiv.pokeball.data

import com.entiv.pokeball.utils.translateDyeColor
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.entity.Wolf

object WolfData : DataWrapper<Wolf>(Wolf::class) {

    override fun entityWriteToNbt(entity: Wolf, compound: NBTCompound) {
        compound.setString("collarColor", entity.collarColor.name)
        compound.setBoolean("angry", entity.isAngry)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Wolf) {
        entity.collarColor = DyeColor.valueOf(compound.getString("collarColor"))
        entity.isAngry = compound.getBoolean("angry")
    }

    override fun entityWriteToComponent(entity: Wolf, components: MutableList<Component>) {
        addComponent(components, "项圈颜色", translateDyeColor(entity.collarColor))
        addComponent(components, "生气状态", if (entity.isAngry) "是" else "否")
    }

}