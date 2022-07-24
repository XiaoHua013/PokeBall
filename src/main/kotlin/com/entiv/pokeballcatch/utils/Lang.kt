package com.entiv.pokeballcatch.utils

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.command.CommandSender

object Lang {

    fun sendMessage(path: String, sender: CommandSender, vararg args: TagResolver) {
        val richMessage = config.getString("提示消息.$path") ?: return
        if (richMessage.isEmpty()) return

        val component = MiniMessage.miniMessage().deserialize(richMessage, *args)

        sender.sendMessage(component)
    }
}