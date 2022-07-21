package com.entiv.pokeball

import com.entiv.core.plugin.InsekiPlugin
import com.entiv.pokeball.utils.isPokeBall
import com.entiv.pokeball.utils.toPokeBallItem

import org.bukkit.Material
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class PokeBallPlugin : InsekiPlugin(), Listener {

    override fun onEnabled() {
        val message = arrayOf(
            "§e${name}-${description.version}§a 插件已启用", "§a插件制作作者:§e EnTIv §aQQ群:§e 600731934"
        )
        server.consoleSender.sendMessage(*message)
        registerListener(this)
    }

    @EventHandler
    private fun onPlayerIE(event: PlayerInteractEntityEvent) {
        val player = event.player
        val entity = event.rightClicked as? LivingEntity ?: return

        val item = player.inventory.itemInMainHand

        if (item.type == Material.AIR) {
            val pokeBallItem = entity.toPokeBallItem()
            player.inventory.addItem(pokeBallItem)
        }
    }

    @EventHandler
    private fun onPlayerI(event: PlayerInteractEvent) {
        val player = event.player
        val item = event.item ?: return

        if (item.isPokeBall()) {
            val pokeBall = PokeBall(item)
            pokeBall.spawnEntity(player.location)
        }
    }
}