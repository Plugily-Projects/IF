package plugily.projects.inventoryframework.pane;

import plugily.projects.inventoryframework.gui.InventoryComponent;
import plugily.projects.inventoryframework.gui.type.util.Gui;
import plugily.projects.inventoryframework.gui.GuiItem;
import plugily.projects.inventoryframework.exception.XMLLoadException;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A pane for panes that should be spread out over multiple pages
 */
public class PaginatedPane extends Pane {

    /**
     * A set of panes for the different pages
     */
    @NotNull
    private final Map<Integer, List<Pane>> panes = new HashMap<>();

    /**
     * The current page
     */
    private int page;

    public PaginatedPane(int x, int y, int length, int height, @NotNull Priority priority) {
        super(x, y, length, height, priority);
    }

    public PaginatedPane(int x, int y, int length, int height) {
        super(x, y, length, height);
    }

    public PaginatedPane(int length, int height) {
        super(length, height);
    }

    /**
     * Returns the current page
     *
     * @return the current page
     */
    public int getPage() {
        return page;
    }

    /**
     * Returns the amount of pages
     *
     * @return the amount of pages
     */
    public int getPages() {
        return panes.size();
    }
    /**
     * Assigns a pane to a selected page
     *
     * @param page the page to assign the pane to
     * @param pane the new pane
     */
    public void addPane(int page, @NotNull Pane pane) {
        if (!this.panes.containsKey(page))
            this.panes.put(page, new ArrayList<>());

        this.panes.get(page).add(pane);

        this.panes.get(page).sort(Comparator.comparing(Pane::getPriority));
    }

    /**
     * Sets the current displayed page
     *
     * @param page the page
     */
    public void setPage(int page) {
		if (!panes.containsKey(page))
			throw new ArrayIndexOutOfBoundsException("page outside range");
		this.page = page;
    }

	/**
	 * Populates the PaginatedPane based on the provided list by adding new pages until all items can fit.
	 * This can be helpful when dealing with lists of unknown size.
	 *
	 * @param items The list to populate the pane with
	 */
	@Contract("null -> fail")
	public void populateWithItemStacks(@NotNull List<ItemStack> items) {
		//Don't do anything if the list is empty
		if (items.isEmpty()) {
		    return;
        }

		int itemsPerPage = this.height * this.length;
		int pagesNeeded = (int) Math.max(Math.ceil(items.size() / (double) itemsPerPage), 1);

		for (int i = 0; i < pagesNeeded; i++) {
			OutlinePane page = new OutlinePane(0, 0, this.length, this.height);

			for (int j = 0; j < itemsPerPage; j++) {
				//Check if the loop reached the end of the list
				int index = i * itemsPerPage + j;

				if (index >= items.size()) {
				    break;
                }

				page.addItem(new GuiItem(items.get(index)));
			}

			this.addPane(i, page);
		}
	}


    /**
     * Populates the PaginatedPane based on the provided list by adding new pages until all items can fit.
     * This can be helpful when dealing with lists of unknown size.
     *
     * @param items The list to populate the pane with
     */
    @Contract("null -> fail")
    public void populateWithGuiItems(@NotNull List<GuiItem> items) {
        //Don't do anything if the list is empty
        if (items.isEmpty()) {
            return;
        }

        int itemsPerPage = this.height * this.length;
        int pagesNeeded = (int) Math.max(Math.ceil(items.size() / (double) itemsPerPage), 1);

        for (int i = 0; i < pagesNeeded; i++) {
            OutlinePane page = new OutlinePane(0, 0, this.length, this.height);

            for (int j = 0; j < itemsPerPage; j++) {
                int index = i * itemsPerPage + j;

				//Check if the loop reached the end of the list
                if (index >= items.size()) {
                    break;
                }

                page.addItem(items.get(index));
            }

            this.addPane(i, page);
        }
    }

	/**
	 * This method creates a list of ItemStacks all with the given {@code material} and the display names.
	 * After that it calls {@link #populateWithItemStacks(List)}
	 * This method also translates the color char {@code &} for all names.
	 *
	 * @param displayNames The display names for all the items
	 * @param material The material to use for the {@link org.bukkit.inventory.ItemStack}s
	 */
	@Contract("null, _ -> fail")
	public void populateWithNames(@NotNull List<String> displayNames, @Nullable Material material) {
		if(material == null || material == Material.AIR) return;

		populateWithItemStacks(displayNames.stream().map(name -> {
			ItemStack itemStack = new ItemStack(material);
			ItemMeta itemMeta = itemStack.getItemMeta();
			itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
			itemStack.setItemMeta(itemMeta);
			return itemStack;
		}).collect(Collectors.toList()));
	}

    @Override
    public void display(@NotNull InventoryComponent inventoryComponent, int paneOffsetX, int paneOffsetY, int maxLength,
                        int maxHeight) {
        List<Pane> panes = this.panes.get(page);

        if (panes == null) {
            return;
        }

        for (Pane pane : panes) {
            int newPaneOffsetX = paneOffsetX + getX();
            int newPaneOffsetY = paneOffsetY + getY();
            int newMaxLength = Math.min(length, maxLength);
            int newMaxHeight = Math.min(height, maxHeight);

            pane.display(inventoryComponent, newPaneOffsetX, newPaneOffsetY, newMaxLength, newMaxHeight);
        }
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

		callOnClick(event);

        boolean success = false;

        for (Pane pane : this.panes.getOrDefault(page, Collections.emptyList())) {
            success = success || pane.click(gui, inventoryComponent, event, slot,paneOffsetX + getX(),
                paneOffsetY + getY(), length, height);
        }

        return success;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public PaginatedPane copy() {
	    PaginatedPane paginatedPane = new PaginatedPane(x, y, length, height, getPriority());

        for (Map.Entry<Integer, List<Pane>> entry : panes.entrySet()) {
            for (Pane pane : entry.getValue()) {
                paginatedPane.addPane(entry.getKey(), pane.copy());
            }
        }

        paginatedPane.setVisible(isVisible());
        paginatedPane.onClick = onClick;

        paginatedPane.uuid = uuid;

        paginatedPane.page = page;

        return paginatedPane;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public Collection<Pane> getPanes() {
        Collection<Pane> panes = new HashSet<>();

        this.panes.forEach((integer, p) -> {
            p.forEach(pane -> panes.addAll(pane.getPanes()));
            panes.addAll(p);
        });

        return panes;
    }

    /**
     * Gets all the panes from inside the specified page of this pane. If the specified page is not existent, this
     * method will throw an {@link IllegalArgumentException}. If the specified page is existent, but doesn't
     * have any panes, the returned collection will be empty. The returned collection is unmodifiable. The returned
     * collection is not synchronized and no guarantees should be made as to the safety of concurrently accessing the
     * returned collection. If synchronized behaviour should be allowed, the returned collection must be synchronized
     * externally.
     *
     * @param page the panes of this page will be returned
     * @return a collection of panes belonging to the specified page
     * @since 0.5.13
     * @throws IllegalArgumentException if the page does not exist
     */
    @NotNull
    @Contract(pure = true)
    public Collection<Pane> getPanes(int page) {
        Collection<Pane> panes = this.panes.get(page);

        if (panes == null) {
            throw new IllegalArgumentException("Invalid page");
        }

        return Collections.unmodifiableCollection(panes);
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public Collection<GuiItem> getItems() {
        return getPanes().stream().flatMap(pane -> pane.getItems().stream()).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        panes.clear();
    }

    /**
     * Loads a paginated pane from a given element
     *
     * @param instance the instance class
     * @param element the element
     * @return the paginated pane
     */
    @NotNull
    public static PaginatedPane load(@NotNull Object instance, @NotNull Element element) {
        try {
            PaginatedPane paginatedPane = new PaginatedPane(
                Integer.parseInt(element.getAttribute("length")),
                Integer.parseInt(element.getAttribute("height"))
            );

            Pane.load(paginatedPane, instance, element);

            if (element.hasAttribute("populate"))
                return paginatedPane;

            int pageCount = 0;

            NodeList childNodes = element.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);

                if (item.getNodeType() != Node.ELEMENT_NODE)
                    continue;

                NodeList innerNodes = item.getChildNodes();

                for (int j = 0; j < innerNodes.getLength(); j++) {
                    Node pane = innerNodes.item(j);

                    if (pane.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }

					paginatedPane.addPane(pageCount, Gui.loadPane(instance, pane));
                }

                pageCount++;
            }

            return paginatedPane;
        } catch (NumberFormatException exception) {
            throw new XMLLoadException(exception);
        }
    }
}
