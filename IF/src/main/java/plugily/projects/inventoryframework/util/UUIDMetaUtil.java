package plugily.projects.inventoryframework.util;

import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UUIDMetaUtil {
    private static final Map<ItemMeta, UUID> metaMap = new ConcurrentHashMap<>();

    private UUIDMetaUtil() {
    }

    public static void put(ItemMeta meta, UUID uuid) {
        metaMap.put(meta, uuid);
    }

    public static UUID getUUID(ItemMeta meta) {
        return metaMap.get(meta);
    }

    public static void remove(ItemMeta meta) {
        metaMap.remove(meta);
    }
}
