////////////////////////////////////////////////////////////////////
// [Marco] [Dalla Libera] [1170634]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

public class BaseMenuItem implements MenuItem {

    private MenuItemType type;
    private String name;
    private double price;

    public BaseMenuItem(MenuItemType type, String name, double price) {
        if (price < 0)
        {
            throw new IllegalArgumentException("Negative price");
        }
        
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public MenuItemType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
