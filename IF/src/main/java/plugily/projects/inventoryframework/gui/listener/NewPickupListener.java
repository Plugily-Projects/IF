package plugily.projects.inventoryframework.gui.listener;

import plugily.projects.inventoryframework.gui.type.util.Gui;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class NewPickupListener extends AbstractGuiListener {
    /**
     * Handles users picking up items while their bottom inventory is in use.
     *
     * @param event the event fired when an player picks up an item
     * @since 0.9.6
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onEntityPickupItem(@NotNull EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof HumanEntity)) {
            return;
        }

        Gui gui = getGui(((HumanEntity) event.getEntity()).getOpenInventory().getTopInventory());

        if (gui == null || !gui.isPlayerInventoryUsed()) {
            return;
        }

        int leftOver = gui.getHumanEntityCache().add((HumanEntity) event.getEntity(), event.getItem().getItemStack());

        if (leftOver == 0) {
            event.getItem().remove();
        } else {
            ItemStack itemStack = event.getItem().getItemStack();

            itemStack.setAmount(leftOver);

            event.getItem().setItemStack(itemStack);
        }

        event.setCancelled(true);
    }
}
