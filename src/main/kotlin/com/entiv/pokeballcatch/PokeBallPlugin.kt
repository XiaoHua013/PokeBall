package com.entiv.pokeballcatch

import com.entiv.core.command.CompositeCommand
import com.entiv.core.command.DefaultCommand
import com.entiv.core.plugin.InsekiPlugin
import com.entiv.pokeballcatch.command.GiveCommand
import com.entiv.pokeballcatch.pokeball.PokeBallManager
import org.bukkit.event.Listener

class PokeBallPlugin : InsekiPlugin(), Listener {

    override fun onEnabled() {
        val message = arrayOf(
            "§e${name}-${description.version}§a 插件已启用", "§a插件制作作者:§e EnTIv §aQQ群:§e 600731934"
        )
        server.consoleSender.sendMessage(*message)
        registerListener(this)

        moduleManager.load(PokeBallManager)
        saveDefaultConfig()
        registerCommand()
    }

    private fun registerCommand() {
        DefaultCommand.register()
        GiveCommand.register()
    }
}