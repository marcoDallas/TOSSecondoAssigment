package it.unipd.tos.business;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.BaseMenuItem;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItem.MenuItemType;

public class BillMakerExceptionsTest {

    private BillMaker billMaker = null;
    private ArrayList<MenuItem> menuList = null;
    
    @Before
    public void before() {
        billMaker = new BillMaker();
        menuList = new ArrayList<MenuItem>();
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
    
    @Test(expected = TakeAwayBillException.class)
    public void getOrderPrice_MoreThan30Elements_ExceptionThrown() 
            throws TakeAwayBillException {
        for (int i = 0; i < 31; ++i) {
            menuList.add(new BaseMenuItem(MenuItemType.PANINI, 
                    "primavera", 4.0));
        }
        billMaker.getOrderPrice(menuList);
    }
}
