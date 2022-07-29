package com.entiv.pokeballcatch.command

import com.entiv.core.command.CompositeCommand
import com.entiv.core.command.DefaultCommand
import com.entiv.core.utils.InventoryUtil
import com.entiv.core.utils.addOrDrop
import com.entiv.core.utils.errorPrefix
import com.entiv.pokeballcatch.pokeball.PokeBallManager
import com.entiv.pokeballcatch.utils.config
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

object GiveCommand : CompositeCommand(
    name = "give",
    parent = DefaultCommand,
    description = "给予玩家一个精灵球",
    usage = "<玩家> <精灵球> [数量]",
    minArgs = 2,
) {
    override fun onCommand() {
        val player = findPlayer(0)
        val ballType = findString(1)

        val pokeBall = PokeBallManager.getPokeBall(ballType) ?: commandException("${errorPrefix}精灵球类型 $ballType 不存在")
        val itemStack = pokeBall.getPokeBallItem()
        val amount = findIntOrNull(2) ?: 1

        itemStack.amount = amount
        player.inventory.addOrDrop(player, itemStack)
    }

    override fun onTabComplete(sender: CommandSender, args: Array<String>): List<String> {
        if (args.size == 1) {
            return Bukkit.getOnlinePlayers().map { it.name }
        }

        if (args.size == 2) {
            return config.getConfigurationSection("精灵球")?.getKeys(false)?.toList() ?: emptyList()
        }

        if (args.size == 3) {
            return listOf("1", "2", "3", "4", "5")
        }

        return emptyList()
    }
}