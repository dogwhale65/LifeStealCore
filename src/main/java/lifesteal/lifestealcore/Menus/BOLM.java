package lifesteal.lifestealcore.Menus;

import lifesteal.lifestealcore.LifeStealCore;
import lifesteal.lifestealcore.itemmanagers.ItemManagerBeaconOfLife;
import lifesteal.lifestealcore.itemmanagers.ItemManagerHeart;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Date;

public class BOLM implements Listener {
    private String invName = "§cChoose a player to revive!";
    public BOLM(LifeStealCore plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        if (heldItem.isSimilar(ItemManagerBeaconOfLife.LifeBeacon) && event.getAction().name().contains("RIGHT_CLICK")) {
            event.setCancelled(true);
            player.getInventory().removeItem(ItemManagerBeaconOfLife.LifeBeacon);
            Inventory inv = Bukkit.createInventory(player, 9 * 6, invName);

            for (BanEntry bannedPlayer : Bukkit.getServer().getBanList(BanList.Type.NAME).getBanEntries()) {
                ItemStack playerhead = new ItemStack(Material.PLAYER_HEAD, 1);
                ItemMeta meta = playerhead.getItemMeta();
                meta.setDisplayName(bannedPlayer.getDisplayName());
                playerhead.setItemMeta(meta);
                inv.addItem(playerhead);
            }




            player.openInventory(inv);
        }
    }
}
