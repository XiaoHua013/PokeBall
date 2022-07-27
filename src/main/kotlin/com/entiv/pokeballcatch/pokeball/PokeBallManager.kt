package com.entiv.pokeballcatch.pokeball

import com.entiv.core.debug.debug
import com.entiv.core.module.Module
import com.entiv.core.utils.InventoryUtil
import com.entiv.pokeballcatch.utils.config
import com.entiv.pokeballcatch.utils.isPokeBall
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory

object PokeBallManager : Module(), Listener {

    private val pokeBalls = mutableMapOf<String, PokeBall>()

    override fun load() {
        val section = config.getConfigurationSection("精灵球") ?: error("配置文件错误，请检查配置文件！")

        section.getKeys(false)
            .mapNotNull { section.getConfigurationSection(it) }
            .forEach {
                val pokeBall = PokeBall.fromConfig(it)
                pokeBalls[pokeBall.type] = pokeBall
                debug("精灵球 ${pokeBall.type} 加载成功")
            }

    }

    override fun unload() {
        pokeBalls.clear()
    }

    fun getPokeBall(itemStack: ItemStack): PokeBall {
        val compound = NBTItem(itemStack).getCompound("PokeBall") ?: error("物品 ${itemStack.displayName()} 不是一个精灵球")

        val typeName = compound.getString("BallType") ?: error("无法读取到该精灵球的物品类型！")

        return getPokeBall(typeName) ?: error("精灵球类型 $itemStack 不存在！请检查配置文件")
    }

    fun getPokeBall(name: String): PokeBall? {
        return pokeBalls[name]
    }

    @EventHandler
    private fun denyPlace(event: BlockPlaceEvent) {
        val item = event.itemInHand

        if (item.isPokeBall()) {
            event.isCancelled = true
        }
    }

    @EventHandler
    private fun handlerThrow(event: PlayerInteractEvent) {
        val player = event.player

        if (!event.action.isRightClick) return
        val itemStack = event.item ?: return
        if (!itemStack.isPokeBall()) return

        val pokeBall = getPokeBall(itemStack)

        pokeBall.throwPokeBall(player, itemStack)
    }

    @EventHandler
    private fun handlerThrow(event: PlayerInteractEntityEvent) {

        if (event.hand != EquipmentSlot.HAND) {
            return
        }

        val player = event.player
        val itemStack = player.inventory.itemInMainHand
        if (!itemStack.isPokeBall()) return

        val pokeBall = getPokeBall(itemStack)

        pokeBall.throwPokeBall(player, itemStack)
    }

    @EventHandler
    private fun denyHat(event: InventoryClickEvent) {
        val inventory = event.inventory

        if (inventory.type != InventoryType.CRAFTING) return

        val currentItem = event.currentItem

        if (currentItem.isPokeBall() && event.isShiftClick) {
            event.isCancelled = true
            return
        }

        if (event.cursor.isPokeBall() && event.rawSlot == 5) {
            event.isCancelled = true
            return
        }

        val player = event.whoClicked as? Player ?: return

        if (event.click == ClickType.NUMBER_KEY && event.rawSlot == 5 && player.inventory.getItem(event.hotbarButton).isPokeBall()) {
            event.isCancelled = true
        }
    }

    @EventHandler
    private fun denyHat(event: InventoryDragEvent) {
        if (event.inventory.type != InventoryType.CRAFTING) {
            return
        }

        event.rawSlots.forEach {
            if (it == 5) {
                event.isCancelled = true
                return
            }
        }
    }
}