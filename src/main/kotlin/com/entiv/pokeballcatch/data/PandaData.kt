package com.entiv.pokeballcatch.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Panda

object PandaData : DataWrapper<Panda>(Panda::class) {
    override fun entityWriteToNbt(entity: Panda, compound: NBTCompound) {
        compound.setString("mainGene", entity.mainGene.name)
        compound.setString("hiddenGene", entity.hiddenGene.name)
    }

    override fun nbtWriteToEntity(compound: NBTCompound, entity: Panda) {
        entity.mainGene = Panda.Gene.valueOf(compound.getString("mainGene"))
        entity.hiddenGene = Panda.Gene.valueOf(compound.getString("hiddenGene"))
    }

    override fun entityWriteToComponent(entity: Panda, components: MutableList<Component>) {
        addComponent(components, "显性基因", translateGene(entity.mainGene))
        addComponent(components, "隐性基因", translateGene(entity.hiddenGene))
    }

    private fun translateGene(gene: Panda.Gene): String {
        return when (gene) {
            Panda.Gene.LAZY -> "懒惰"
            Panda.Gene.WORRIED -> "忧郁"
            Panda.Gene.PLAYFUL -> "顽皮"
            Panda.Gene.AGGRESSIVE -> "好斗"
            Panda.Gene.WEAK -> "体弱"
            Panda.Gene.BROWN -> "棕色"
            Panda.Gene.NORMAL -> "普通"
        }
    }
}