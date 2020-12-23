package com.github.tonatumi.invchat

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class InvChat : JavaPlugin(), Listener {
    fun openGUI(player: Player) {
        val coal = ItemStack(Material.COAL)
        val coalm = coal.itemMeta
        coalm.setDisplayName("§8おはつです")
        val coallore = Arrays.asList("§8クリックでおはつといいます。")
        coalm.lore = coallore
        coal.itemMeta = coalm

        val ironIngot = ItemStack(Material.IRON_INGOT)
        val ironIngotm = ironIngot.itemMeta
        ironIngotm.setDisplayName("§8こんにちは")
        val ironIngotlore = Arrays.asList("§8クリックでこんにちはといいます。")
        ironIngotm.lore = ironIngotlore
        ironIngot.itemMeta = ironIngotm

        val goldIngot = ItemStack(Material.GOLD_INGOT)
        val goldIngotm = goldIngot.itemMeta
        goldIngotm.setDisplayName("§8よろしくお願いします")
        val goldIngotlore = Arrays.asList("§8クリックでよろしくお願いしますといいます。")
        goldIngotm.lore = goldIngotlore
        goldIngot.itemMeta = goldIngotm

        val lapisLazuli = ItemStack(Material.LAPIS_LAZULI)
        val lapisLazulim = lapisLazuli.itemMeta
        lapisLazulim.setDisplayName("§8おつです")
        val lapisLazulilore = Arrays.asList("§8クリックでおつですといいます。")
        lapisLazulim.lore = lapisLazulilore
        lapisLazuli.itemMeta = lapisLazulim

        val diamond = ItemStack(Material.DIAMOND)
        val diamondm = diamond.itemMeta
        diamondm.setDisplayName("§8ありがとう")
        val diamondlore = Arrays.asList("§8クリックでありがとうといいます。")
        diamondm.lore = diamondlore
        diamond.itemMeta = diamondm

        val emerald = ItemStack(Material.EMERALD)
        val emeraldm = emerald.itemMeta
        emeraldm.setDisplayName("§8ごめんなさい")
        val emeraldlore = Arrays.asList("§8クリックでごめんなさいといいます。")
        emeraldm.lore = emeraldlore
        emerald.itemMeta = emeraldm

        val redstone = ItemStack(Material.REDSTONE)
        val redstonem = redstone.itemMeta
        redstonem.setDisplayName("§8はい")
        val redstonelore = Arrays.asList("§8クリックではいといいます。")
        redstonem.lore = redstonelore
        redstone.itemMeta = redstonem

        val glowstoneDust = ItemStack(Material.GLOWSTONE_DUST)
        val glowstoneDustm = glowstoneDust.itemMeta
        glowstoneDustm.setDisplayName("§8いいえ")
        val glowstoneDustlore = Arrays.asList("§8クリックでいいえといいます。")
        glowstoneDustm.lore = glowstoneDustlore
        glowstoneDust.itemMeta = glowstoneDustm

        val quartz = ItemStack(Material.QUARTZ)
        val quartzm = quartz.itemMeta
        quartzm.setDisplayName("§8おはまん！")
        val quartzlore = Arrays.asList("§8クリックでおはまん！といいます。")
        quartzm.lore = quartzlore
        quartz.itemMeta = quartzm

        val inv: Inventory
        inv = Bukkit.createInventory(null, 9, "定型文")
        inv.setItem(0, coal)
        inv.setItem(1, ironIngot)
        inv.setItem(2, goldIngot)
        inv.setItem(3, lapisLazuli)
        inv.setItem(4, diamond)
        inv.setItem(5, emerald)
        inv.setItem(6, redstone)
        inv.setItem(7, glowstoneDust)
        inv.setItem(8, quartz)
        player.openInventory(inv)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) return false
        openGUI(sender)
        return true
    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val p = event.whoClicked as Player
        event.isCancelled = true
        if (event.clickedInventory === event.whoClicked.inventory) {
            return
        }
        if (event.view.title == "定型文") {
            when (event.currentItem!!.type) {
                Material.COAL -> {
                    p.chat("おはつです")
                    p.closeInventory()
                }
                Material.IRON_INGOT -> {
                    p.chat("こんにちは")
                    p.closeInventory()
                }
                Material.GOLD_INGOT -> {
                    p.chat("よろしくお願いします")
                    p.closeInventory()
                }
                Material.LAPIS_LAZULI -> {
                    p.chat("おつです")
                    p.closeInventory()
                }
                Material.DIAMOND -> {
                    p.chat("ありがとうございます")
                    p.closeInventory()
                }
                Material.EMERALD -> {
                    p.chat("ごめんなさい")
                    p.closeInventory()
                }
                Material.REDSTONE -> {
                    p.chat("はい")
                    p.closeInventory()
                }
                Material.GLOWSTONE_DUST -> {
                    p.chat("いいえ")
                    p.closeInventory()
                }
                Material.QUARTZ -> {
                    p.chat("/give @p egg")
                    p.closeInventory()
                }
            }
        }
    }

    override fun onEnable() {
        getCommand("ic")!!.setExecutor(this)
        server.pluginManager.registerEvents(this, this)
        logger.info("start:Invchat")
        // Plugin startup logic
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}