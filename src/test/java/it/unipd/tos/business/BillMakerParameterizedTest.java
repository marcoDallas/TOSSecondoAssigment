package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.BaseMenuItem;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItem.MenuItemType;

@RunWith(Parameterized.class)
public class BillMakerParameterizedTest {
    
    private BillMaker billMaker = null;
    private ArrayList<MenuItem> menuList = null;
    private double expectedAmount = 0;
    private static final double DELTA = 0.001;

    @Before
    public void before() {
        billMaker = new BillMaker();
    }
    
    public BillMakerParameterizedTest(ArrayList<MenuItem> menuList, 
            double expectedAmount) {
        this.menuList = menuList;
        this.expectedAmount = expectedAmount;
    }
    
    @Parameters
    public static Collection<Object[]> testConditions() {
        ArrayList<MenuItem> simpleList = buildSimpleList();
        
        ArrayList<MenuItem> invertedSimpleList = 
                new ArrayList<MenuItem>(simpleList); 
        Collections.reverse(invertedSimpleList);
        
        ArrayList<MenuItem> emptyList = new ArrayList<MenuItem>();
        
        ArrayList<MenuItem> sixSandwiches = buildSixSandwiches();
        
        ArrayList<MenuItem> invertedSixSandwiches =
                new ArrayList<MenuItem>(sixSandwiches);
        Collections.reverse(invertedSixSandwiches);
        
        ArrayList<MenuItem> listOf51Drinks = new ArrayList<MenuItem>();
        for(int i = 0; i < 26; ++i) {
            listOf51Drinks.add(new BaseMenuItem(MenuItemType.BEVANDE, 
                    "spritz", 2));
        }
        
        ArrayList<MenuItem> listOf51Sandwiches = new ArrayList<MenuItem>();
        for(int i = 0; i < 26; ++i) {
            listOf51Sandwiches.add(new BaseMenuItem(MenuItemType.PANINI, 
                    "primavera", 2));
        }
        
        ArrayList<MenuItem> twoDrinks = new ArrayList<MenuItem>();
        for(int i = 0; i < 2; ++i) {
            twoDrinks.add(new BaseMenuItem(MenuItemType.BEVANDE, 
                    "acqua", 1));
        }
        
        return Arrays.asList(new Object[][] {
            {simpleList, 22},
            {invertedSimpleList, 22},
            {emptyList, 0},
            {sixSandwiches, 28.5},
            {invertedSixSandwiches, 28.5},
            {listOf51Drinks, 46.8},
            {listOf51Sandwiches, 45.90} ,
            {twoDrinks, 2.5}
        });
    }
            
    @Test
    public void getOrderPrice_TestListInputsWithExpectedOutputs() {
        try {
            assertEquals(expectedAmount, billMaker.getOrderPrice(menuList), 
                    DELTA);
        } catch (TakeAwayBillException e) {
            fail("Unexpected Exception: " + e.getMessage());
        }
    }
    
    private static ArrayList<MenuItem> buildSimpleList() {
        ArrayList<MenuItem> simpleList = new ArrayList<MenuItem>();
        simpleList.add(new BaseMenuItem(MenuItemType.BEVANDE, "spritz", 3.5));
        simpleList.add(new BaseMenuItem(MenuItemType.BEVANDE, "spritz", 3.5));
        simpleList.add(new BaseMenuItem(MenuItemType.BEVANDE, "spritz", 3.5));
        simpleList.add(new BaseMenuItem(MenuItemType.BEVANDE, "hugo", 3.5));
        simpleList.add(new BaseMenuItem(MenuItemType.FRITTI, "olive", 4.0));
        simpleList.add(new BaseMenuItem(MenuItemType.PANINI, "primavera", 4.0));
        return simpleList;
    }
        
    private static ArrayList<MenuItem> buildSixSandwiches() {
        ArrayList<MenuItem> sixSandwiches = new ArrayList<MenuItem>();
        for(int i = 0; i < 5; ++i) {
            sixSandwiches.add(new BaseMenuItem(MenuItemType.PANINI, 
                    "primavera", 4.0));
        }
        sixSandwiches.add(new BaseMenuItem(MenuItemType.PANINI, 
                "toast", 2.0));
        sixSandwiches.add(new BaseMenuItem(MenuItemType.BEVANDE, 
                "spritz", 3.5));
        sixSandwiches.add(new BaseMenuItem(MenuItemType.FRITTI, 
                "olive", 4.0));
        return sixSandwiches;
    }
}
