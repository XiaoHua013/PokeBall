package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.AnimalTamer
import org.bukkit.entity.Fox

class FoxData(
    private val firstTrustedPlayer: AnimalTamer?,
    private val secondTrustedPlayer: AnimalTamer?,
    private val foxType: Fox.Type,
) : EntityData<Fox>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setObject("firstTrustedPlayer", firstTrustedPlayer)
        nbtCompound.setObject("secondTrustedPlayer", secondTrustedPlayer)
        nbtCompound.setString("foxType", foxType.name)
    }

    override fun applyComponent(components: MutableList<Component>) {

        loreComponent("品种", translateFoxType(foxType)).also {
            components.add(it)
        }

        if (firstTrustedPlayer != null && secondTrustedPlayer != null) {
            loreComponent("信任玩家", "${firstTrustedPlayer.name}, ${secondTrustedPlayer.name}").also {
                components.add(it)
            }
        } else if (firstTrustedPlayer == null && secondTrustedPlayer == null) {
            loreComponent("信任玩家", "无").also {
                components.add(it)
            }
        } else {
            loreComponent("信任玩家", "${firstTrustedPlayer?.name ?: secondTrustedPlayer!!.name}").also {
                components.add(it)
            }
        }
    }

    override fun applyEntity(entity: Fox) {
        entity.firstTrustedPlayer = firstTrustedPlayer
        entity.secondTrustedPlayer = secondTrustedPlayer
        entity.foxType = foxType
    }

    private fun translateFoxType(type: Fox.Type):String  {
        return when (type) {
            Fox.Type.RED -> "红狐"
            Fox.Type.SNOW -> "北极狐"
        }
    }

    companion object : DataCreator<Fox>() {
        override val dataClass = Fox::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            return FoxData(
                nbtCompound.getObject("firstTrustedPlayer", AnimalTamer::class.java),
                nbtCompound.getObject("secondTrustedPlayer", AnimalTamer::class.java),
                Fox.Type.valueOf(nbtCompound.getString("foxType"))
            )
        }

        override fun getEntityData(entity: Fox): EntityData<*> {
            return FoxData(
                entity.firstTrustedPlayer,
                entity.secondTrustedPlayer,
                entity.foxType
            )
        }
    }
}