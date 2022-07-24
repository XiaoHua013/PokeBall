package com.entiv.pokeballcatch

import com.entiv.core.command.CompositeCommand
import com.entiv.core.command.DefaultCommand
import com.entiv.core.plugin.InsekiPlugin
import com.entiv.pokeballcatch.command.GiveCommand
import com.entiv.pokeballcatch.pokeball.PokeBallManager
import org.bukkit.event.Listener

class PokeBallPlugin : InsekiPlugin() {

    override fun onEnabled() {
        val message = arrayOf(
            "§e${name}-${description.version}§a 插件已启用", "§a插件制作作者:§e EnTIv §aQQ群:§e 600731934"
        )
        server.consoleSender.sendMessage(*message)

        reload()
    }

    private fun registerCommands() {
        DefaultCommand.register()
        GiveCommand.register()
    }

    override fun reload() {
        saveDefaultConfig()
        reloadConfig()
        unregisterCommands()
        registerCommands()

        moduleManager.unload(PokeBallManager)
        moduleManager.load(PokeBallManager)
    }
}