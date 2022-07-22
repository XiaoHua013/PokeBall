package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Villager
import org.bukkit.entity.ZombieVillager

object ZombieVillageData : DataWrapper<ZombieVillager>(ZombieVillager::class) {
    override fun entityWriteToNbt(entity: ZombieVillager, compound: NBTCompound) {
        compound.setString("Profession", entity.villagerProfession?.name)
        compound.setString("VillagerType", entity.villagerType.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: ZombieVillager) {
        entity.villagerProfession = Villager.Profession.valueOf(compound.getString("Profession"))
        entity.villagerType = Villager.Type.valueOf(compound.getString("VillagerType"))
    }

    override fun entityWriteToComponent(entity: ZombieVillager, components: MutableList<Component>) {
        addComponent(components, "职业", VillageData.translateProfession(entity.villagerProfession))
        addComponent(components, "群系", VillageData.translateType(entity.villagerType))
    }

}