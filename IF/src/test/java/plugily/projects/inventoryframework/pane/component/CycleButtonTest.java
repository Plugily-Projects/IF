package plugily.projects.inventoryframework.pane.component;

import plugily.projects.inventoryframework.pane.Pane;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CycleButtonTest {

    @Test
    void testCopy() {
        CycleButton original = new CycleButton(6, 2, 2, 3, Pane.Priority.HIGH);
        original.setVisible(true);

        original.addPane(new CycleButton(1, 1));

        CycleButton copy = original.copy();

        assertNotSame(original, copy);

        Assertions.assertEquals(original.getX(), copy.getX());
        Assertions.assertEquals(original.getY(), copy.getY());
        Assertions.assertEquals(original.getLength(), copy.getLength());
        Assertions.assertEquals(original.getHeight(), copy.getHeight());
        Assertions.assertEquals(original.getPriority(), copy.getPriority());
        Assertions.assertEquals(original.isVisible(), copy.isVisible());
        assertEquals(original.getPanes().size(), copy.getPanes().size());
        Assertions.assertEquals(original.getUUID(), copy.getUUID());
    }
}
