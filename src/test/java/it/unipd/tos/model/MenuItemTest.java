package it.unipd.tos.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.unipd.tos.model.MenuItem.MenuItemType;

public class MenuItemTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void enumaration_WrongValueOf_ExceptionThrown() {
        MenuItemType.valueOf("null");
    }
    
    @Test
    public void enumaration_ValidValueOf_CorrectValue() {
        MenuItemType menuItemType = MenuItemType.valueOf("BEVANDE");
        
        assertEquals(MenuItemType.BEVANDE, menuItemType);
    }
    
    @Test
    public void enumaration_RequiredValuesArray_CorrectValuesReturned() {
        MenuItemType inputTypes[] = MenuItemType.values();
        MenuItemType expectedOutputTypes[] = {
                MenuItemType.PANINI,
                MenuItemType.FRITTI,
                MenuItemType.BEVANDE };
        
        assertArrayEquals(expectedOutputTypes, inputTypes);
    }

}
