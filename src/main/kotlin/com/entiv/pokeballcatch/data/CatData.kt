package com.entiv.pokeballcatch.data

import com.entiv.pokeballcatch.utils.translate
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.entity.Cat
import org.bukkit.entity.Cat.Type

object CatData : DataWrapper<Cat>(Cat::class) {
    override fun entityWriteToNbt(entity: Cat, compound: NBTCompound) {
        compound.setString("catType", entity.catType.name)
        compound.setString("catColor", entity.collarColor.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Cat) {
        entity.catType = Type.valueOf(compound.getString("catType"))
        entity.collarColor = DyeColor.valueOf(compound.getString("catColor"))
    }

    override fun entityWriteToComponent(entity: Cat, components: MutableList<Component>) {
        addComponent(components, "品种", entity.catType.translate())
        addComponent(components, "项圈颜色", entity.collarColor.translate())
    }

    fun Type.translate(): String {
        return when (this) {
            Type.TABBY -> "虎斑猫"
            Type.BLACK -> "西服猫"
            Type.RED -> "红虎斑猫"
            Type.SIAMESE -> "暹罗猫"
            Type.BRITISH_SHORTHAIR -> "英国短毛猫"
            Type.CALICO -> "花猫"
            Type.PERSIAN -> "波斯猫"
            Type.RAGDOLL -> "布偶猫"
            Type.WHITE -> "白猫"
            Type.JELLIE -> "起司猫"
            Type.ALL_BLACK -> "黑猫"
        }
    }

}