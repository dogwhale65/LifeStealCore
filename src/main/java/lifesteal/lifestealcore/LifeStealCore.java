package lifesteal.lifestealcore;

import lifesteal.lifestealcore.Commands.Withdraw;
import lifesteal.lifestealcore.Menus.BOLM;
import lifesteal.lifestealcore.itemmanagers.ItemManagerBeaconOfLife;
import lifesteal.lifestealcore.itemmanagers.ItemManagerHeart;
import lifesteal.lifestealcore.itemmanagers.ItemManagerHeartFrag;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public final class LifeStealCore extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("§6Enabling LifeSteal");
        new PlayerDeath(this);
        new EquipHeart(this);
        new BOLM(this);
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
