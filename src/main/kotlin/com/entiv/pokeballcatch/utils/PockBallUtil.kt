package com.entiv.pokeballcatch.utils

import com.entiv.core.debug.debug
import com.entiv.core.plugin.InsekiPlugin
import com.entiv.pokeballcatch.data.DataWrapper
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

val dataWrappers = DataWrapper::class.sealedSubclasses
    .mapNotNull {
       try { it.objectInstance } catch (_: NoClassDefFoundError) { null }
    }
    .sortedBy { it.priority }
    .toList()
    .also {
        debug("共加载了 ${it.size} 个实体处理器")
    }

fun ItemStack?.isPokeBall(): Boolean {
    if (this == null) return false

    if (type != Material.PLAYER_HEAD) {
        return false
    }
    return NBTItem(this).getCompound("PokeBall") != null
}

val config get() = InsekiPlugin.instance.config
