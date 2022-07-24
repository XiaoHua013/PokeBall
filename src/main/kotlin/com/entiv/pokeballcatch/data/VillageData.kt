package com.entiv.pokeballcatch.data

import com.entiv.core.utils.translate
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
        addComponent(components, "职业", entity.profession.translate())
        addComponent(components, "等级", entity.villagerLevel.toString())
        addComponent(components, "经验", entity.villagerExperience.toString())
        addComponent(components, "群系", entity.villagerType.translate())
    }
}