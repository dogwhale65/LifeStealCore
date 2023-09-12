package lifesteal.lifestealcore;

import lifesteal.lifestealcore.itemmanagers.ItemManagerHeart;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.inventory.EquipmentSlot.HAND;
import static org.bukkit.inventory.EquipmentSlot.OFF_HAND;

public class EquipHeart implements Listener {
    private LifeStealCore plugin;
    public EquipHeart(LifeStealCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();;
        int maxHealth = (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        if(event.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }
            if (!(heldItem.isSimilar(ItemManagerHeart.Heart)) || !(event.getAction().name().contains("RIGHT_CLICK")))
                return;

            if (maxHealth >= 40) {
                player.sendMessage("§8[§4✕§8] You have reached the maximum amount of hearts!");
                return;
            }

            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth + 2);
            player.sendMessage("§8[§a✓§8] You have gained 1 heart!");
            heldItem.setAmount(heldItem.getAmount() - 1);
    }
}