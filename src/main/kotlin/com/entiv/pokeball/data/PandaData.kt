package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Panda

class PandaData(
    private val mainGene: Panda.Gene,
    private val hiddenGene: Panda.Gene,
): EntityData<Panda>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setString("mainGene", mainGene.name)
        nbtCompound.setString("hiddenGene", hiddenGene.name)
    }

    override fun applyComponent(components: MutableList<Component>) {
        //TODO 汉化
        loreComponent("显性基因", mainGene.name).let { components.add(it) }
        loreComponent("隐性基因", hiddenGene.name).let { components.add(it) }
    }

    override fun applyEntity(entity: Panda) {
        entity.mainGene = mainGene
        entity.hiddenGene = hiddenGene
    }

    companion object : DataCreator<Panda>() {
        override val dataEntityClass = Panda::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            val mainGene = Panda.Gene.valueOf(nbtCompound.getString("mainGene"))
            val hiddenGene = Panda.Gene.valueOf(nbtCompound.getString("hiddenGene"))

            return PandaData(mainGene, hiddenGene)
        }

        override fun getEntityData(entity: Panda): EntityData<*> {
            return PandaData(entity.mainGene, entity.hiddenGene)
        }

    }
}