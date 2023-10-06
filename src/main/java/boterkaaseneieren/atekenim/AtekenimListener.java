package boterkaaseneieren.atekenim;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
public class AtekenimListener implements Listener {
    Map<Player, Integer> sugarConsumedCount = new HashMap<>();
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction().toString().contains("RIGHT_CLICK")
                && player.getInventory().getItemInMainHand().getType() == Material.SUGAR) {
            ItemStack sugar = event.getItem();
            int amount = sugar.getAmount();
            if (amount > 1) {
                // Decrease the sugar amount by 1
                sugar.setAmount(amount - 1);
            } else {
                // Remove the last piece of sugar from the player's hand
                event.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            }
            int count = sugarConsumedCount.getOrDefault(player, 0) + 1;
            sugarConsumedCount.put(player, count);

            // Apply nausea effect
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 1));
            player.sendMessage(ChatColor.RED + "Beetje vaag man...");

            if (count >= 6) {
                // Teleport the player to the End dimension
                if (player.getWorld().getEnvironment() != World.Environment.THE_END) {
                    World endWorld = player.getServer().getWorlds().stream()
                            .filter(world -> world.getEnvironment() == World.Environment.THE_END)
                            .findFirst()
                            .orElse(null);

                    if (endWorld != null) {
                        Location endLocation = new Location(endWorld, 0, 50, 0); // Adjust the coordinates
                        player.teleport(endLocation);
                        player.sendMessage(ChatColor.YELLOW + "Teleported to the End dimension!");
                    } else {
                        player.sendMessage(ChatColor.RED + "The End dimension is not available on this server.");
                    }
                }
            } else if (count >= 4) {
                for (int i = 0; i < 5; i++) {
                    Location horseLocation = player.getLocation().add(2 * i, 0, 2 * i);
                    Horse horse = player.getWorld().spawn(horseLocation, Horse.class);
                    // Customize horse properties as needed
                }
                player.sendMessage(ChatColor.BLUE + "Ze willen je shit nakken");
            }
        }
    }
}


