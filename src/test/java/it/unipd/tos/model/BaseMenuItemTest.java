////////////////////////////////////////////////////////////////////
// [Marco] [Dalla Libera] [1170634]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import it.unipd.tos.model.MenuItem.MenuItemType;

public class BaseMenuItemTest {

    private BaseMenuItem baseMenuItem = null;
    private static final double DELTA = 0.001;
        
    // getType
    @Test
    public void getType_TypeInitialized_TypeReturned() {
        baseMenuItem = new BaseMenuItem(MenuItemType.BEVANDE, "bevanda", 0.0);
        assertEquals(MenuItemType.BEVANDE, baseMenuItem.getType());
    }
    
    @Test
    public void getType_NullInitialize_NullReturn() {
        baseMenuItem = new BaseMenuItem(null, null, 0);
        assertNull(baseMenuItem.getType());
    }

    // getName
    @Test
    public void getName_NameInitialized_NameReturned() {
        baseMenuItem = new BaseMenuItem(null, "test", 0.0);
        assertEquals("test", baseMenuItem.getName());
    }
    
    @Test
    public void getName_NullInitialize_NullReturn() {
        baseMenuItem = new BaseMenuItem(null, null, 0);
        assertNull(baseMenuItem.getName());
    }
    
    // Price
    @Test
    public void getName_PriceInitialized_PriceReturned() {
        baseMenuItem = new BaseMenuItem(null, null, 3.5);
        assertEquals(3.5, baseMenuItem.getPrice(), DELTA);
    }
    
    // Constructor
    @Test(expected = IllegalArgumentException.class)
    public void constructor_NegativePriceGiven_ExceptionThrown() {
        baseMenuItem = new BaseMenuItem(null, null, -1);
        baseMenuItem.getType();
    }
}
