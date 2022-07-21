package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Panda

//class PandaData(
//    private val mainGene: Panda.Gene,
//    private val hiddenGene: Panda.Gene,
//): EntityData<Panda>() {
//    override fun applyCompound(compound: NBTCompound) {
//        compound.setString("mainGene", mainGene.name)
//        compound.setString("hiddenGene", hiddenGene.name)
//    }
//
//    override fun applyComponent(components: MutableList<Component>) {
//        loreComponent("显性基因", translateGene(mainGene)).let { components.add(it) }
//        loreComponent("隐性基因", translateGene(hiddenGene)).let { components.add(it) }
//    }
//
//    override fun applyEntity(entity: Panda) {
//        entity.mainGene = mainGene
//        entity.hiddenGene = hiddenGene
//    }
//
//    private fun translateGene(gene: Panda.Gene): String {
//        return when (gene) {
//            Panda.Gene.LAZY -> "懒惰"
//            Panda.Gene.WORRIED -> "忧郁"
//            Panda.Gene.PLAYFUL -> "顽皮"
//            Panda.Gene.AGGRESSIVE -> "好斗"
//            Panda.Gene.WEAK -> "体弱"
//            Panda.Gene.BROWN -> "棕色"
//            Panda.Gene.NORMAL -> "普通"
//        }
//    }
//
//}
object PandaData