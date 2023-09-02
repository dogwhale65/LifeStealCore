package lifesteal.lifestealcore.itemmanagers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManagerHeartFrag {
    public static ItemStack HeartFrag;

    public ItemManagerHeartFrag() {
    }

    public static void init() {
        createHeartFrag();
    }

    private static void createHeartFrag() {
        ItemStack heartFrag = new ItemStack(Material.DIAMOND, 1);
        ItemMeta meta = heartFrag.getItemMeta();
        meta.setDisplayName("§cHeart Fragment");
        List<String> lore = new ArrayList<>();
        lore.add("§dUse 4 of these to craft a heart!"); // Add the lore here
        for (int i = 0; i < lore.size(); i++) {
            String line = lore.get(i);
            line = line.replace("§o", "");
            lore.set(i, line);
        }
        heartFrag.setItemMeta(meta);
        HeartFrag = heartFrag;
        ShapedRecipe hfRecipe = new ShapedRecipe(NamespacedKey.minecraft("heartfrag"), heartFrag);
        hfRecipe.shape(new String[]{"DDD", "DTD", "DDD"});
        hfRecipe.setIngredient('D', Material.DIAMOND_BLOCK);
        hfRecipe.setIngredient('T', Material.TOTEM_OF_UNDYING);
        Bukkit.addRecipe(hfRecipe);
    }
}
