package com.entiv.pokeball.data

import com.entiv.pokeball.utils.translateDyeColor
import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.entity.Cat
import org.bukkit.entity.Cat.Type

//class CatData(
//    private val catType: Type,
//    private val collarColor: DyeColor
//) : EntityData<Cat>() {
//    override fun applyCompound(compound: NBTCompound) {
//        compound.setString("CatType", catType.name)
//        compound.setString("CollarColor", collarColor.name)
//    }
//
//    override fun applyComponent(components: MutableList<Component>) {
//        components.add(loreComponent("品种", translateType(catType)))
//        components.add(loreComponent("项圈颜色", translateDyeColor(collarColor)))
//    }
//
//    override fun applyEntity(entity: Cat) {
//        entity.catType = catType
//        entity.collarColor = collarColor
//    }
//

//    companion object : DataCreator<Cat>() {
//        override val dataClass = Cat::class.java
//
//        override fun getEntityData(compound: NBTCompound): EntityData<*> {
//            val catType = Type.valueOf(compound.getString("CatType"))
//            val collarColor = DyeColor.valueOf(compound.getString("CollarColor"))
//            return CatData(catType, collarColor)
//        }
//
//        override fun getEntityData(entity: Cat): EntityData<*> {
//            return CatData(entity.catType, entity.collarColor)
//        }
//
//    }
//}

object CatData : DataWrapper<Cat>() {
    override fun entityWriteToNbt(entity: Cat, compound: NBTCompound) {
        compound.setString("catType", entity.type.name)
        compound.setString("catColor", entity.collarColor.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Cat) {
        entity.catType = Type.valueOf(compound.getString("catType"))
        entity.collarColor = DyeColor.valueOf(compound.getString("catColor"))
    }

    override fun entityWriteToComponent(entity: Cat, components: MutableList<Component>) {
        addComponent(components, "品种", translateType(entity.catType))
        addComponent(components, "项圈颜色", translateDyeColor(entity.collarColor))
    }

    private fun translateType(type: Type): String {
        return when (type) {
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