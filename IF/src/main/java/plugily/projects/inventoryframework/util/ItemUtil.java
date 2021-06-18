package plugily.projects.inventoryframework.util;

import plugily.projects.inventoryframework.util.version.Version;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class ItemUtil {
    private static final ItemStack PLAYER_HEAD;
    private static final ItemStack GREEN_STAINED_GLASS_PANE;
    private static final ItemStack RED_STAINED_GLASS_PANE;

    static {
        if (Version.CURRENT.getMinor() >= 13) {
            PLAYER_HEAD = new ItemStack(Material.valueOf("PLAYER_HEAD"));
            GREEN_STAINED_GLASS_PANE = new ItemStack(Material.valueOf("GREEN_STAINED_GLASS_PANE"));
            RED_STAINED_GLASS_PANE = new ItemStack(Material.valueOf("RED_STAINED_GLASS_PANE"));
        } else {
            PLAYER_HEAD = new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (short) 3);
            GREEN_STAINED_GLASS_PANE = new ItemStack(Material.valueOf("STAINED_GLASS_PANE"), 1, (short) 13);
            RED_STAINED_GLASS_PANE = new ItemStack(Material.valueOf("STAINED_GLASS_PANE"), 1, (short) 14);
        }
    }
    private ItemUtil() {
    }

    public static ItemStack getPlayerHead() {
        return PLAYER_HEAD.clone();
    }

    public static ItemStack getGreenStainedGlassPane() {
        return GREEN_STAINED_GLASS_PANE.clone();
    }

    public static ItemStack getRedStainedGlassPane() {
        return RED_STAINED_GLASS_PANE.clone();
    }
}
