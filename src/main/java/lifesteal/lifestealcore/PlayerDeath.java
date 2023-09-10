package lifesteal.lifestealcore;


import lifesteal.lifestealcore.itemmanagers.ItemManagerHeart;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public class PlayerDeath implements Listener {
    public PlayerDeath(LifeStealCore plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        Player victim = event.getPlayer();
        Player killer = victim.getKiller();
        int valp = (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        if (killer != null) {
            int valk = (int) killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            int valv = (int) victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue((double) (valv - 2));
            if (victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() <= 1.0) {
                victim.getInventory().clear();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue((double) (6));
                        victim.banPlayer("§cYou have run out of hearts!");
                    }
                }.runTaskLater((Plugin) this, 20L);
            }
            if (valk >= 40 && killer != victim) {
                killer.getInventory().addItem(new ItemStack[]{ItemManagerHeart.Heart});
            } else if (killer != victim){
                killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue((double)(valk + 2));
            }
        } else {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue((double)(valp - 2));
            if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() <= 1.0) {
                player.getInventory().clear();
                BukkitScheduler scheduler = Bukkit.getScheduler();
                scheduler.runTask((Plugin) this, () -> {
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue((double) (6));
                    player.kickPlayer("§cYou have run out of hearts!");
                    player.banPlayer("§cYou have run out of hearts!");
                });
            }
        }
    }
}
