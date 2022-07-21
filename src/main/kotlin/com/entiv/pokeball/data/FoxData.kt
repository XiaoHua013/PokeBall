package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.AnimalTamer
import org.bukkit.entity.Fox

object FoxData : DataWrapper<Fox>() {
    override fun entityWriteToNbt(entity: Fox, compound: NBTCompound) {
        compound.setObject("firstTrustedPlayer", entity.firstTrustedPlayer)
        compound.setObject("secondTrustedPlayer", entity.secondTrustedPlayer)
        compound.setString("foxType", entity.foxType.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Fox) {
        entity.firstTrustedPlayer = compound.getObject("firstTrustedPlayer", AnimalTamer::class.java)
        entity.secondTrustedPlayer = compound.getObject("secondTrustedPlayer", AnimalTamer::class.java)
        entity.foxType = Fox.Type.valueOf(compound.getString("foxType"))
    }

    override fun entityWriteToComponent(entity: Fox, components: MutableList<Component>) {

        addComponent(components, "品种", translateFoxType(entity.foxType))

        val firstTrustedPlayer = entity.firstTrustedPlayer
        val secondTrustedPlayer = entity.secondTrustedPlayer

        if (firstTrustedPlayer != null && secondTrustedPlayer != null) {
            addComponent(components, "信任玩家", "${firstTrustedPlayer.name}, ${secondTrustedPlayer.name}")
        } else if (firstTrustedPlayer == null && secondTrustedPlayer == null) {
            addComponent(components, "信任玩家", "无")
        } else {
            addComponent(components, "信任玩家", "${firstTrustedPlayer?.name ?: secondTrustedPlayer!!.name}")
        }
    }
    private fun translateFoxType(type: Fox.Type):String  {
        return when (type) {
            Fox.Type.RED -> "红狐"
            Fox.Type.SNOW -> "北极狐"
        }
    }
}