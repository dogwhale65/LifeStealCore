package lifesteal.lifestealcore.itemmanagers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManagerBeaconOfLife {
    public static ItemStack LifeBeacon;

    public ItemManagerBeaconOfLife() {
    }

    public static void init() {
        createBeaconLife();
    }

    private static void createBeaconLife() {
        ItemStack lifeBeacon = new ItemStack(Material.BEACON, 1);
        ItemMeta meta = lifeBeacon.getItemMeta();
        meta.setDisplayName("§dBeacon of Life");
        List<String> lore = new ArrayList<>();
        lore.add("§dRight-Click this to bring someone back to life!"); // Add the lore here
        for (int i = 0; i < lore.size(); i++) {
            String line = lore.get(i);
            line = line.replace("§o", "");
            lore.set(i, line);
        }
        meta.setLore(lore);
        lifeBeacon.setItemMeta(meta);
        LifeBeacon = lifeBeacon;
        ShapedRecipe lbRecipe = new ShapedRecipe(NamespacedKey.minecraft("lifebeacon"), lifeBeacon);
        lbRecipe.shape(new String[]{"NHN", "HRH", "NHN"});
        lbRecipe.setIngredient('N', Material.NETHERITE_BLOCK);
        lbRecipe.setIngredient('R', Material.RECOVERY_COMPASS);
        lbRecipe.setIngredient('H', ItemManagerHeart.Heart);
        Bukkit.addRecipe(lbRecipe);
    }
}
