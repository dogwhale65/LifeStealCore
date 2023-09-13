package lifesteal.lifestealcore.Menus;

import com.destroystokyo.paper.profile.PlayerProfile;
import lifesteal.lifestealcore.LifeStealCore;
import lifesteal.lifestealcore.itemmanagers.ItemManagerBeaconOfLife;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import static org.bukkit.Bukkit.getBanList;

public class Revive implements Listener {
    private LifeStealCore plugin;
    public Revive(LifeStealCore plugin) {
        this.plugin = plugin;
    }
    private String invName = "§cChoose a player to revive!";
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        if (heldItem.isSimilar(ItemManagerBeaconOfLife.LifeBeacon) && event.getAction().name().contains("RIGHT_CLICK")) {
            if(event.getHand() == EquipmentSlot.OFF_HAND) {
                event.setCancelled(true);
                return;
            }
            event.setCancelled(true);
            player.getInventory().removeItem(ItemManagerBeaconOfLife.LifeBeacon);
            Inventory inv = Bukkit.createInventory(player, 9 * 6, invName);


            for (BanEntry bannedPlayerEntry : Bukkit.getServer().getBanList(BanList.Type.NAME).getBanEntries()) {
                PlayerProfile bannedPlayer = (PlayerProfile) bannedPlayerEntry.getBanTarget();
                ItemStack playerhead = new ItemStack(Material.PLAYER_HEAD, 1);
                ItemMeta meta = playerhead.getItemMeta();
                SkullMeta skullMeta = (SkullMeta) meta;
                skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(bannedPlayer.getUniqueId()));
                playerhead.setItemMeta(skullMeta);
                meta.setDisplayName(bannedPlayer.getName());
                playerhead.setItemMeta(meta);
                inv.addItem(playerhead);
            }
            player.openInventory(inv);


        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(invName))  {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        event.setCancelled(true);
        if (clickedItem != null && clickedItem.getType() == Material.PLAYER_HEAD && event.getView().getTitle().equals(invName)) {
            String playerName = clickedItem.getItemMeta().getDisplayName();
            Bukkit.getBanList(BanList.Type.NAME).pardon(playerName);
            player.sendMessage("§aSuccessfully revived " + playerName + "§a!");
            player.closeInventory();
        }
    }
}