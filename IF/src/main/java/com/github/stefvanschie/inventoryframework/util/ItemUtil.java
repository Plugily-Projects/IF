package com.github.stefvanschie.inventoryframework.util;

import com.github.stefvanschie.inventoryframework.util.version.Version;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemUtil {
    private ItemUtil() {
    }

    @SuppressWarnings("deprecation")
    public static ItemStack getPlayerHead() {
        if (Version.CURRENT.getMinor() >= 13) {
            return new ItemStack(Material.valueOf("PLAYER_HEAD"));
        } else {
            // noinspection deprecation
            return new ItemStack(Material.valueOf("SKULL_ITEM"), 1, (short) 3);
        }
    }

    @SuppressWarnings("deprecation")
    public static ItemStack getGreenStainedGlassPane() {
        if (Version.CURRENT.getMinor() >= 13) {
            return new ItemStack(Material.valueOf("GREEN_STAINED_GLASS_PANE"));
        } else {
            // noinspection deprecation
            return new ItemStack(Material.valueOf("STAINED_GLASS_PANE"), 1, (short) 13);
        }
    }

    @SuppressWarnings("deprecation")
    public static ItemStack getRedStainedGlassPane() {
        if (Version.CURRENT.getMinor() >= 13) {
            return new ItemStack(Material.valueOf("RED_STAINED_GLASS_PANE"));
        } else {
            // noinspection deprecation
            return new ItemStack(Material.valueOf("STAINED_GLASS_PANE"), 1, (short) 14);
        }
    }
}
