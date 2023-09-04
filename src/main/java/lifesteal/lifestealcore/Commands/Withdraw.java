package lifesteal.lifestealcore.Commands;


import lifesteal.lifestealcore.itemmanagers.ItemManagerHeart;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Withdraw implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length > 0) {
                    int amount;
                    try {
                        amount = Integer.parseInt(args[0]);
                    } catch (NumberFormatException e) {
                        // Handle if the input is not a valid number
                        player.sendMessage("Invalid amount, please provide a number.");
                        return true;
                    }

                    int gmh = amount * 2;
                    int valp = (int) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                    if (amount < 20){
                        if (valp > gmh) {
                            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(valp - gmh);
                            player.sendMessage("§8[§a✓§8] You have withdrawn " + amount + " hearts!");
                            ItemStack items = ItemManagerHeart.Heart;
                            items.setAmount(amount);
                            player.getInventory().addItem(items);
                        } else {
                            player.sendMessage("§8[§4✕§8] Not enough hearts to withdraw.");
                        }
                    } else {
                        player.sendMessage("§cYou do not have enough hearts to withdraw " + amount + "§c.");
                    }
                } else {
                    // Handle if the /withdraw command is used without specifying an amount
                    player.sendMessage("Usage: /withdraw <amount>");
                }
            }
            return true;
        }
}