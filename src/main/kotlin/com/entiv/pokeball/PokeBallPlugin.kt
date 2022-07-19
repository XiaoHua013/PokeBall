package com.entiv.pokeball

import com.entiv.core.plugin.InsekiPlugin
import de.tr7zw.nbtapi.NBTEntity
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

class PokeBallPlugin : InsekiPlugin(), Listener {

    override fun onEnabled() {
        val message = arrayOf(
            "§e${name}-${description.version}§a 插件已启用",
            "§a插件制作作者:§e EnTIv §aQQ群:§e 600731934"
        )
        server.consoleSender.sendMessage(*message)
        registerListener(this)
    }


}