package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Villager

object VillageData : DataWrapper<Villager>(Villager::class) {

    override fun entityWriteToNbt(entity: Villager, compound: NBTCompound) {
        compound.setString("Profession", entity.profession.name)
        compound.setInteger("VillagerExperience", entity.villagerExperience)
        compound.setInteger("VillagerLevel", entity.villagerLevel)
        compound.setString("VillagerType", entity.villagerType.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Villager) {
        entity.profession = Villager.Profession.valueOf(compound.getString("Profession"))
        entity.villagerExperience = compound.getInteger("VillagerExperience")
        entity.villagerLevel = compound.getInteger("VillagerLevel")
        entity.villagerType = Villager.Type.valueOf(compound.getString("VillagerType"))
    }

    override fun entityWriteToComponent(entity: Villager, components: MutableList<Component>) {
        addComponent(components, "职业", translateProfession(entity.profession))
        addComponent(components, "等级", entity.villagerLevel.toString())
        addComponent(components, "经验", entity.villagerExperience.toString())
        addComponent(components, "群系", translateType(entity.villagerType))
    }

    fun translateProfession(profession: Villager.Profession?): Component {
        return if (profession != null) {
            Component.translatable(profession.translationKey())
        } else {
            Component.text("未知")
        }
    }

    fun translateType(type: Villager.Type): String {
        return when (type) {
            Villager.Type.DESERT -> "沙漠"
            Villager.Type.JUNGLE -> "丛林"
            Villager.Type.PLAINS -> "平原"
            Villager.Type.SAVANNA -> "热带"
            Villager.Type.SNOW -> "雪地"
            Villager.Type.SWAMP -> "沼泽"
            Villager.Type.TAIGA -> "针叶林"
        }
    }
}