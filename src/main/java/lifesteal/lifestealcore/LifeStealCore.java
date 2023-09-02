package lifesteal.lifestealcore;

import lifesteal.lifestealcore.itemmanagers.ItemManagerBeaconOfLife;
import lifesteal.lifestealcore.itemmanagers.ItemManagerHeart;
import lifesteal.lifestealcore.itemmanagers.ItemManagerHeartFrag;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LifeStealCore extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Enabling LifeSteal");
        new PlayerDeath(this);
        new EquipHeart(this);
        ItemManagerHeartFrag.init();
        ItemManagerHeart.init();
        ItemManagerBeaconOfLife.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
