package plugily.projects.inventoryframework.util.version;

import plugily.projects.inventoryframework.abstraction.AnvilInventory;
import plugily.projects.inventoryframework.abstraction.BeaconInventory;
import plugily.projects.inventoryframework.abstraction.CartographyTableInventory;
import plugily.projects.inventoryframework.abstraction.EnchantingTableInventory;
import plugily.projects.inventoryframework.abstraction.GrindstoneInventory;
import plugily.projects.inventoryframework.abstraction.SmithingTableInventory;
import plugily.projects.inventoryframework.abstraction.StonecutterInventory;
import plugily.projects.inventoryframework.exception.UnsupportedVersionException;
import plugily.projects.inventoryframework.nms.v1_14_R1.AnvilInventoryImpl;
import plugily.projects.inventoryframework.nms.v1_14_R1.BeaconInventoryImpl;
import plugily.projects.inventoryframework.nms.v1_14_R1.CartographyTableInventoryImpl;
import plugily.projects.inventoryframework.nms.v1_14_R1.EnchantingTableInventoryImpl;
import plugily.projects.inventoryframework.nms.v1_14_R1.GrindstoneInventoryImpl;
import plugily.projects.inventoryframework.nms.v1_14_R1.StonecutterInventoryImpl;
import plugily.projects.inventoryframework.nms.v1_16_R1.SmithingTableInventoryImpl;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class containing versioning related methods.
 *
 * @since 0.8.0
 */
public class VersionMatcher {

  /**
   * Gets a new anvil inventory for the specified version of the specified inventory holder.
   *
   * @param inventoryHolder the inventory holder
   * @return the anvil inventory
   * @throws UnsupportedVersionException when the version is not supported
   * @since 0.8.0
   */
  @NotNull
  @Contract(pure = true)
  public static AnvilInventory newAnvilInventory(@NotNull InventoryHolder inventoryHolder) {
    int minor = Version.CURRENT.getMinor();
    int micro = Version.CURRENT.getMicro();
    switch(minor) {
      case 14:
        return new AnvilInventoryImpl(inventoryHolder);
      case 15:
        return new plugily.projects.inventoryframework.nms.v1_15_R1.AnvilInventoryImpl(inventoryHolder);
      case 16:
        if(micro == 1) {
          return new plugily.projects.inventoryframework.nms.v1_16_R1.AnvilInventoryImpl(inventoryHolder);
        } else if(micro == 2) {
          return new plugily.projects.inventoryframework.nms.v1_16_R2.AnvilInventoryImpl(inventoryHolder);
        } else {
          return new plugily.projects.inventoryframework.nms.v1_16_R3.AnvilInventoryImpl(inventoryHolder);
        }
      case 17:
        return new plugily.projects.inventoryframework.nms.v1_17_R1.AnvilInventoryImpl(inventoryHolder);
      default:
        throw new UnsupportedVersionException("Cannot create the inventory for this version");
    }
  }

  /**
   * Gets a new beacon inventory for the specified version of the specified inventory holder.
   *
   * @param inventoryHolder the inventory holder
   * @return the beacon inventory
   * @throws UnsupportedVersionException when the version is not supported
   * @since 0.8.0
   */
  @NotNull
  @Contract(pure = true)
  public static BeaconInventory newBeaconInventory(@NotNull InventoryHolder inventoryHolder) {
    int minor = Version.CURRENT.getMinor();
    int micro = Version.CURRENT.getMicro();
    switch(minor) {
      case 14:
        return new BeaconInventoryImpl(inventoryHolder);
      case 15:
        return new plugily.projects.inventoryframework.nms.v1_15_R1.BeaconInventoryImpl(inventoryHolder);
      case 16:
        if(micro == 1) {
          return new plugily.projects.inventoryframework.nms.v1_16_R1.BeaconInventoryImpl(inventoryHolder);
        } else if(micro == 2) {
          return new plugily.projects.inventoryframework.nms.v1_16_R2.BeaconInventoryImpl(inventoryHolder);
        } else {
          return new plugily.projects.inventoryframework.nms.v1_16_R3.BeaconInventoryImpl(inventoryHolder);
        }
      case 17:
        return new plugily.projects.inventoryframework.nms.v1_17_R1.BeaconInventoryImpl(inventoryHolder);
      default:
        throw new UnsupportedVersionException("Cannot create the inventory for this version");
    }
  }

  /**
   * Gets a new cartography table inventory for the specified version of the specified inventory holder.
   *
   * @param inventoryHolder the inventory holder
   * @return the cartography table inventory
   * @throws UnsupportedVersionException when the version is not supported
   * @since 0.8.0
   */
  @NotNull
  @Contract(pure = true)
  public static CartographyTableInventory newCartographyTableInventory(@NotNull InventoryHolder inventoryHolder) {
    int minor = Version.CURRENT.getMinor();
    int micro = Version.CURRENT.getMicro();
    switch(minor) {
      case 14:
        return new CartographyTableInventoryImpl(inventoryHolder);
      case 15:
        return new plugily.projects.inventoryframework.nms.v1_15_R1.CartographyTableInventoryImpl(inventoryHolder);
      case 16:
        if(micro == 1) {
          return new plugily.projects.inventoryframework.nms.v1_16_R1.CartographyTableInventoryImpl(inventoryHolder);
        } else if(micro == 2) {
          return new plugily.projects.inventoryframework.nms.v1_16_R2.CartographyTableInventoryImpl(inventoryHolder);
        } else {
          return new plugily.projects.inventoryframework.nms.v1_16_R3.CartographyTableInventoryImpl(inventoryHolder);
        }
      case 17:
        return new plugily.projects.inventoryframework.nms.v1_17_R1.CartographyTableInventoryImpl(inventoryHolder);
      default:
        throw new UnsupportedVersionException("Cannot create the inventory for this version");
    }
  }

  /**
   * Gets a new enchanting table inventory for the specified version of the specified inventory holder.
   *
   * @param inventoryHolder the inventory holder
   * @return the enchanting table inventory
   * @throws UnsupportedVersionException when the version is not supported
   * @since 0.8.0
   */
  @NotNull
  @Contract(pure = true)
  public static EnchantingTableInventory newEnchantingTableInventory(@NotNull InventoryHolder inventoryHolder) {
    int minor = Version.CURRENT.getMinor();
    int micro = Version.CURRENT.getMicro();
    switch(minor) {
      case 14:
        return new EnchantingTableInventoryImpl(inventoryHolder);
      case 15:
        return new plugily.projects.inventoryframework.nms.v1_15_R1.EnchantingTableInventoryImpl(inventoryHolder);
      case 16:
        if(micro == 1) {
          return new plugily.projects.inventoryframework.nms.v1_16_R1.EnchantingTableInventoryImpl(inventoryHolder);
        } else if(micro == 2) {
          return new plugily.projects.inventoryframework.nms.v1_16_R2.EnchantingTableInventoryImpl(inventoryHolder);
        } else {
          return new plugily.projects.inventoryframework.nms.v1_16_R3.EnchantingTableInventoryImpl(inventoryHolder);
        }
      case 17:
        return new plugily.projects.inventoryframework.nms.v1_17_R1.EnchantingTableInventoryImpl(inventoryHolder);
      default:
        throw new UnsupportedVersionException("Cannot create the inventory for this version");
    }
  }

  /**
   * Gets a new grindstone inventory for the specified version of the specified inventory holder.
   *
   * @param inventoryHolder the inventory holder
   * @return the grindstone inventory
   * @throws UnsupportedVersionException when the version is not supported
   * @since 0.8.0
   */
  @NotNull
  @Contract(pure = true)
  public static GrindstoneInventory newGrindstoneInventory(@NotNull InventoryHolder inventoryHolder) {
    int minor = Version.CURRENT.getMinor();
    int micro = Version.CURRENT.getMicro();
    switch(minor) {
      case 14:
        return new GrindstoneInventoryImpl(inventoryHolder);
      case 15:
        return new plugily.projects.inventoryframework.nms.v1_15_R1.GrindstoneInventoryImpl(inventoryHolder);
      case 16:
        if(micro == 1) {
          return new plugily.projects.inventoryframework.nms.v1_16_R1.GrindstoneInventoryImpl(inventoryHolder);
        } else if(micro == 2) {
          return new plugily.projects.inventoryframework.nms.v1_16_R2.GrindstoneInventoryImpl(inventoryHolder);
        } else {
          return new plugily.projects.inventoryframework.nms.v1_16_R3.GrindstoneInventoryImpl(inventoryHolder);
        }
      case 17:
        return new plugily.projects.inventoryframework.nms.v1_17_R1.GrindstoneInventoryImpl(inventoryHolder);
      default:
        throw new UnsupportedVersionException("Cannot create the inventory for this version");
    }
  }

  /**
   * Gets a new smithing table inventory for the specified version of the specified inventory holder. If a smithing
   * table is requested for a version that does not have smithing tables, an {@link UnsupportedVersionException} is
   * thrown.
   *
   * @param inventoryHolder the inventory holder
   * @return the smithing table inventory
   * @throws UnsupportedVersionException when a smithing table is requested on a version without smithing tables
   * @since 0.8.0
   */
  @NotNull
  @Contract(pure = true)
  public static SmithingTableInventory newSmithingTableInventory(@NotNull InventoryHolder inventoryHolder) {
    int minor = Version.CURRENT.getMinor();
    int micro = Version.CURRENT.getMicro();
    switch(minor) {
      case 16:
        if(micro == 1) {
          return new SmithingTableInventoryImpl(inventoryHolder);
        } else if(micro == 2) {
          return new plugily.projects.inventoryframework.nms.v1_16_R2.SmithingTableInventoryImpl(inventoryHolder);
        } else {
          return new plugily.projects.inventoryframework.nms.v1_16_R3.SmithingTableInventoryImpl(inventoryHolder);
        }
      case 17:
        return new plugily.projects.inventoryframework.nms.v1_17_R1.SmithingTableInventoryImpl(inventoryHolder);
      default:
        throw new UnsupportedVersionException("Smithing tables didn't exist in this version");
    }
  }

  /**
   * Gets a new stonecutter inventory for the specified version of the specified inventory holder.
   *
   * @param inventoryHolder the inventory holder
   * @return the stonecutter inventory
   * @throws UnsupportedVersionException when the version is not supported
   * @since 0.8.0
   */
  @NotNull
  @Contract(pure = true)
  public static StonecutterInventory newStonecutterInventory(@NotNull InventoryHolder inventoryHolder) {
    int minor = Version.CURRENT.getMinor();
    int micro = Version.CURRENT.getMicro();
    switch(minor) {
      case 14:
        return new StonecutterInventoryImpl(inventoryHolder);
      case 15:
        return new plugily.projects.inventoryframework.nms.v1_15_R1.StonecutterInventoryImpl(inventoryHolder);
      case 16:
        if(micro == 1) {
          return new plugily.projects.inventoryframework.nms.v1_16_R1.StonecutterInventoryImpl(inventoryHolder);
        } else if(micro == 2) {
          return new plugily.projects.inventoryframework.nms.v1_16_R2.StonecutterInventoryImpl(inventoryHolder);
        } else {
          return new plugily.projects.inventoryframework.nms.v1_16_R3.StonecutterInventoryImpl(inventoryHolder);
        }
      case 17:
        return new plugily.projects.inventoryframework.nms.v1_17_R1.StonecutterInventoryImpl(inventoryHolder);
      default:
        throw new UnsupportedVersionException("Cannot create the inventory for this version");
    }
  }
}
