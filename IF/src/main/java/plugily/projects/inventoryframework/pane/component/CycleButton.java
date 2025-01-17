package plugily.projects.inventoryframework.pane.component;

import plugily.projects.inventoryframework.gui.InventoryComponent;
import plugily.projects.inventoryframework.gui.type.util.Gui;
import plugily.projects.inventoryframework.gui.GuiItem;
import plugily.projects.inventoryframework.exception.XMLLoadException;
import plugily.projects.inventoryframework.pane.Pane;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A button for cycling between different options
 *
 * @since 0.5.0
 */
public class CycleButton extends Pane {

    /**
     * The list of pane used for display
     */
    private final List<Pane> panes = new ArrayList<>();

    /**
     * The current position of the cycle button
     */
    private int position = 0;

    public CycleButton(int x, int y, int length, int height, @NotNull Priority priority) {
        super(x, y, length, height, priority);
    }

    public CycleButton(int x, int y, int length, int height) {
        super(x, y, length, height);
    }

    public CycleButton(int length, int height) {
        super(length, height);
    }

    @Override
    public boolean click(@NotNull Gui gui, @NotNull InventoryComponent inventoryComponent,
                         @NotNull InventoryClickEvent event, int slot, int paneOffsetX, int paneOffsetY, int maxLength,
                         int maxHeight) {
        int length = Math.min(this.length, maxLength);
        int height = Math.min(this.height, maxHeight);

        int adjustedSlot = slot - (getX() + paneOffsetX) - inventoryComponent.getLength() * (getY() + paneOffsetY);

        int x = adjustedSlot % inventoryComponent.getLength();
        int y = adjustedSlot / inventoryComponent.getLength();

        //this isn't our item
        if (x < 0 || x >= length || y < 0 || y >= height) {
            return false;
        }

        position++;

        if (position == panes.size()) {
            position = 0;
        }

        callOnClick(event);

        Pane pane = panes.get(position);
        pane.click(gui, inventoryComponent, event, slot, paneOffsetX + x, paneOffsetY + y,
            length, height);

        gui.update();

        return true;
    }

    @Override
    public void display(@NotNull InventoryComponent inventoryComponent, int paneOffsetX, int paneOffsetY, int maxLength,
                        int maxHeight) {
        int newX = paneOffsetX + x;
        int newY = paneOffsetY + y;

        int newMaxLength = Math.min(maxLength, length);
        int newMaxHeight = Math.min(maxHeight, height);

        panes.get(position).display(inventoryComponent, newX, newY, newMaxLength, newMaxHeight);
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public CycleButton copy() {
        CycleButton cycleButton = new CycleButton(x, y, length, height, getPriority());

        for (Pane pane : panes) {
            cycleButton.addPane(pane);
        }

        cycleButton.setVisible(isVisible());
        cycleButton.onClick = onClick;

        cycleButton.position = position;

        cycleButton.uuid = uuid;

        return cycleButton;
    }

    @NotNull
    @Override
    public Collection<GuiItem> getItems() {
        return getPanes().stream().flatMap(pane -> pane.getItems().stream()).collect(Collectors.toList());
    }

    /**
     * Adds a pane to the current list of options
     *
     * @param index the index to insert the pane at
     * @param pane the pane to add
     * @since 0.5.0
     */
    public void addPane(int index, @NotNull Pane pane) {
        panes.add(index, pane);
    }

    /**
     * Adds a pane to the current list of options
     *
     * @param pane the pane to add
     * @since 0.5.0
     */
    public void addPane(@NotNull Pane pane) {
        panes.add(pane);
    }

    @Override
    public void clear() {
        panes.clear();
    }

    @NotNull
    @Override
    public Collection<Pane> getPanes() {
        return panes;
    }

    /**
     * Cycles through one option, making it go to the next one
     *
     * @since 0.5.0
     */
    public void cycle() {
        position++;
    }

    /**
     * Loads a cycle button from a given element
     *
     * @param instance the instance class
     * @param element the element
     * @return the cycle button
     * @since 0.5.0
     */
    @NotNull
    public static CycleButton load(@NotNull Object instance, @NotNull Element element) {
        int length;
        int height;

        try {
            length = Integer.parseInt(element.getAttribute("length"));
            height = Integer.parseInt(element.getAttribute("height"));
        } catch (NumberFormatException exception) {
            throw new XMLLoadException(exception);
        }

        CycleButton cycleButton = new CycleButton(length, height);

        Pane.load(cycleButton, instance, element);

        if (element.hasAttribute("populate")) {
            return cycleButton;
        }

        NodeList childNodes = element.getChildNodes();

        for (int j = 0; j < childNodes.getLength(); j++) {
            Node pane = childNodes.item(j);

            if (pane.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            cycleButton.addPane(Gui.loadPane(instance, pane));
        }

        return cycleButton;
    }
}
