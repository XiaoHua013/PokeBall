package com.entiv.pokeballcatch.utils

import com.entiv.core.plugin.InsekiPlugin
import com.entiv.pokeballcatch.data.DataWrapper
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.inventory.ItemStack

val dataWrappers = DataWrapper::class.sealedSubclasses
    .mapNotNull { it.objectInstance }
    .sortedBy { it.priority }
    .toList()

fun ItemStack.isPokeBall() = NBTItem(this).getCompound("PokeBall") != null

val config get() = InsekiPlugin.instance.config
