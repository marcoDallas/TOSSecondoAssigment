////////////////////////////////////////////////////////////////////
// [Marco] [Dalla Libera] [1170634]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItem.MenuItemType;

public class BillMaker implements TakeAwayBill {

    private static final double SANDWICH_DISCOUNT_VALUE = 0.5;
    private static final int SANDWICH_DISCOUNT_LIMIT = 5;
    private static final int AMOUNT_DISCOUNT_LIMIT = 50;
    private static final double DISCOUNT_VALUE = 0.9;
    private static final int MAX_ELEMENTS_PER_ORDER = 30;
    private static final int SURCHARGE_MIN_ELEMENTS = 10;
    private static final double SURCHARGE_VALUE = 0.5;
    
    public double getOrderPrice(List<MenuItem> itemsOrdered) 
            throws TakeAwayBillException {
        double amount = 0;
        int sandwichCount = 0;
        double leastExpensive = Double.MAX_VALUE;
        
        if (itemsOrdered.size() > MAX_ELEMENTS_PER_ORDER) {
            throw new TakeAwayBillException("Superato numero massimo elementi");
        }
            
        
        for (MenuItem menuItem : itemsOrdered) {
            double price = menuItem.getPrice();
            if (menuItem.getType().equals(MenuItemType.PANINI)) {
                ++sandwichCount;
                
                if (price < leastExpensive) {
                    leastExpensive = price;
                }
            }
            amount += price;
        }
        if (sandwichCount > SANDWICH_DISCOUNT_LIMIT) {
            amount -= (leastExpensive * SANDWICH_DISCOUNT_VALUE);
        }
        
        if (amount > AMOUNT_DISCOUNT_LIMIT) {
            amount *= DISCOUNT_VALUE;
        }
        
        if (amount > 0 && amount < SURCHARGE_MIN_ELEMENTS) {
            amount += SURCHARGE_VALUE;
        }
            
        
        return amount;
    }
}
