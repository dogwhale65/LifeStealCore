package lifesteal.lifestealcore.itemmanagers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManagerHeart {
    public static ItemStack Heart;

    public ItemManagerHeart() {
    }

    public static void init() {
        createHeart();
    }

    private static void createHeart() {
        ItemStack heart = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = heart.getItemMeta();
        meta.setDisplayName("§4Heart");
        List<String> lore = new ArrayList<>();
        lore.add("§dRight-Click to equip a heart!"); // Add the lore here
        for (int i = 0; i < lore.size(); i++) {
            String line = lore.get(i);
            line = line.replace("§o", "");
            lore.set(i, line);
        }
        meta.setLore(lore);
        heart.setItemMeta(meta);
        Heart = heart;
        ShapedRecipe hRecipe = new ShapedRecipe(NamespacedKey.minecraft("heart"), heart);
        hRecipe.shape(new String[]{"NHN", "HEH", "NHN"});
        hRecipe.setIngredient('E', Material.ENCHANTED_GOLDEN_APPLE);
        hRecipe.setIngredient('N', Material.NETHER_STAR);
        hRecipe.setIngredient('H', ItemManagerHeartFrag.HeartFrag);
        Bukkit.addRecipe(hRecipe);
    }
}