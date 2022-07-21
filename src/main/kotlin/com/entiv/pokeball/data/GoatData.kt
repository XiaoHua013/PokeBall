package com.entiv.pokeball.data

import de.tr7zw.nbtapi.NBTCompound
import net.kyori.adventure.text.Component
import org.bukkit.entity.Goat

class GoatData(
    private val hasLeftHorn: Boolean,
    private val hasRightHorn: Boolean,
    private val isScreaming: Boolean,
): EntityData<Goat>() {
    override fun applyCompound(nbtCompound: NBTCompound) {
        nbtCompound.setBoolean("hasLeftHorn", hasLeftHorn)
        nbtCompound.setBoolean("hasRightHorn", hasRightHorn)
        nbtCompound.setBoolean("isScreaming", isScreaming)
    }

    override fun applyComponent(components: MutableList<Component>) {
        loreComponent("左角", if (hasLeftHorn) "有" else "无").also { components.add(it) }
        loreComponent("右角", if (hasRightHorn) "有" else "无").also { components.add(it) }
        loreComponent("尖叫山羊", if (isScreaming) "是" else "否").also { components.add(it) }
    }

    override fun applyEntity(entity: Goat) {
        entity.setLeftHorn(hasLeftHorn)
        entity.setLeftHorn(hasRightHorn)
        entity.isScreaming = isScreaming
    }

    companion object : DataCreator<Goat>() {
        override val dataClass = Goat::class.java

        override fun getEntityData(nbtCompound: NBTCompound): EntityData<*> {
            return GoatData(
                nbtCompound.getBoolean("hasLeftHorn"),
                nbtCompound.getBoolean("hasRightHorn"),
                nbtCompound.getBoolean("isScreaming")
            )
        }

        override fun getEntityData(entity: Goat): EntityData<*> {
            return GoatData(
                entity.hasLeftHorn(),
                entity.hasRightHorn(),
                entity.isScreaming
            )
        }
    }
}