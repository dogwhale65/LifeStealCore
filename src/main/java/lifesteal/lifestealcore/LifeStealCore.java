package lifesteal.lifestealcore;

import lifesteal.lifestealcore.Commands.Withdraw;
import lifesteal.lifestealcore.Menus.Revive;
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
        new Revive(this);
        ItemManagerHeartFrag.init();
        ItemManagerHeart.init();
        ItemManagerBeaconOfLife.init();
        Bukkit.getPluginCommand("withdraw").setExecutor(new Withdraw());
    }


    @Override
    public void onDisable() {
        Bukkit.getLogger().info(" ");
        Bukkit.getLogger().info(" ");
        Bukkit.getLogger().info(" ");
        Bukkit.getLogger().info(" ");
        Bukkit.getLogger().info(" ");
        Bukkit.getLogger().info(" ");
        Bukkit.getLogger().info(" ");
        Bukkit.getLogger().info(" ");
        Bukkit.getLogger().info(" ");
        Bukkit.getLogger().info("§6 Disabling LifeSteal");
    }
}
