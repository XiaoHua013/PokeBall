package com.entiv.pokeballcatch.utils

import com.entiv.core.debug.debug
import com.entiv.core.debug.warn
import com.entiv.core.plugin.InsekiPlugin
import com.entiv.pokeballcatch.data.DataWrapper
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Bukkit
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

        // 不知道为什么有概率编译会失败，需要删除一个密封类然后撤销重新编译即可
        if (it.isEmpty()) {
            warn("没有加载任何实体处理器，插件已卸载")
            Bukkit.getPluginManager().disablePlugin(InsekiPlugin.instance)
        }
    }

fun ItemStack?.isPokeBall(): Boolean {
    if (this == null) return false

    if (type != Material.PLAYER_HEAD) {
        return false
    }
    return NBTItem(this).getCompound("PokeBall") != null
}

val config get() = InsekiPlugin.instance.config
