package lifesteal.lifestealcore;

import lifesteal.lifestealcore.itemmanagers.ItemManagerHeart;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EquipHeart implements Listener {
    public EquipHeart(LifeStealCore plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();

        if (heldItem.isSimilar(ItemManagerHeart.Heart) && event.getAction().name().contains("RIGHT_CLICK")) {
            int maxHealth = (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

            if (maxHealth < 40) {
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth + 2);
                player.sendMessage("§8[§a✓§8] You have gained 1 heart!");
                heldItem.setAmount(heldItem.getAmount() - 1);

                // Check for player heart count, if exceeding 20 new hearts will not be applied. This is the message to be sent.
            }
        }
    }
}