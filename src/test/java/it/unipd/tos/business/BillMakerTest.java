package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.BaseMenuItem;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItem.MenuItemType;

public class BillMakerTest {
    
    private BillMaker billMaker = null;
    private ArrayList<MenuItem> menuList = null;
    private static final double DELTA = 0.001;

    @Before
    public void before() {
        billMaker = new BillMaker();
        menuList = new ArrayList<MenuItem>();
    }
    
    @Test
    public void getOrderPrice_ValidMenuItem_CorrectOrderAmount() {
        menuList.add(new BaseMenuItem(MenuItemType.BEVANDE, "spritz", 3.5));
        menuList.add(new BaseMenuItem(MenuItemType.BEVANDE, "spritz", 3.5));
        menuList.add(new BaseMenuItem(MenuItemType.BEVANDE, "spritz", 3.5));
        menuList.add(new BaseMenuItem(MenuItemType.BEVANDE, "hugo", 3.5));
        menuList.add(new BaseMenuItem(MenuItemType.FRITTI, "olive", 4.0));
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "primavera", 4.0));
        try {
            assertEquals(22, billMaker.getOrderPrice(menuList), DELTA);
        } catch (TakeAwayBillException e) {
            fail("Unexpected Exception: " + e.getMessage());
        }
    }
    
    @Test
    public void getOrderPrice_InvertedValidMenuItem_CorrectOrderAmount() {
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "primavera", 4.0));
        menuList.add(new BaseMenuItem(MenuItemType.FRITTI, "olive", 4.0));
        menuList.add(new BaseMenuItem(MenuItemType.BEVANDE, "hugo", 3.5));
        menuList.add(new BaseMenuItem(MenuItemType.BEVANDE, "spritz", 3.5));
        menuList.add(new BaseMenuItem(MenuItemType.BEVANDE, "spritz", 3.5));
        menuList.add(new BaseMenuItem(MenuItemType.BEVANDE, "spritz", 3.5));
        try {
            assertEquals(22, billMaker.getOrderPrice(menuList), DELTA);
        } catch (TakeAwayBillException e) {
            fail("Unexpected Exception: " + e.getMessage());
        }
    }
    
    @Test
    public void getOrderPrice_EmptyList_ZeroAmount() {
        try {
            assertEquals(0, billMaker.getOrderPrice(menuList), DELTA);
        } catch (TakeAwayBillException e) {
            fail("Unexpected Exception: " + e.getMessage());
        }
    }
    
    @Test(expected = NullPointerException.class)
    public void getOrderPrice_ListContainsNull_ExceptionThrown() {
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "primavera", 4.0));
        menuList.add(null);
        try {
            billMaker.getOrderPrice(menuList);
        } catch (TakeAwayBillException e) {
            fail("Unexpected Exception: " + e.getMessage());
        }
    }
    
    @Test(expected = NullPointerException.class)
    public void getOrderPrice_NullList_ExceptionThrown() {
        try {
            billMaker.getOrderPrice(null);
        } catch (TakeAwayBillException e) {
            fail("Unexpected Exception: " + e.getMessage());
        }
    }
    
    @Test
    public void getOrderPrice_SixSandwiches_LeastExpensiveDiscounted() {
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "primavera", 4.0));
        menuList.add(new BaseMenuItem(MenuItemType.BEVANDE, "spritz", 3.5));
        menuList.add(new BaseMenuItem(MenuItemType.FRITTI, "olive", 4.0));
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "primavera", 4.0));
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "primavera", 4.0));
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "primavera", 4.0));
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "vegetariano", 3.5));
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "vegetariano", 3.5));
        try {
            assertEquals(28.75, billMaker.getOrderPrice(menuList), DELTA);
        } catch (TakeAwayBillException e) {
            fail("Unexpected Exception: " + e.getMessage());
        }
    }
    
    @Test
    public void getOrderPrice_SixSandwichesInverted_LeastExpensiveDiscounted() {
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "vegetariano", 3.5));
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "vegetariano", 3.5));
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "primavera", 4.0));
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "primavera", 4.0));
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "primavera", 4.0));
        menuList.add(new BaseMenuItem(MenuItemType.FRITTI, "olive", 4.0));
        menuList.add(new BaseMenuItem(MenuItemType.BEVANDE, "spritz", 3.5));
        menuList.add(new BaseMenuItem(MenuItemType.PANINI, "primavera", 4.0));
        try {
            assertEquals(28.75, billMaker.getOrderPrice(menuList), DELTA);
        } catch (TakeAwayBillException e) {
            fail("Unexpected Exception: " + e.getMessage());
        }
    }
    
}
