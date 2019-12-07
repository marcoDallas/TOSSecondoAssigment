////////////////////////////////////////////////////////////////////
// [Marco] [Dalla Libera] [1170634]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

public interface MenuItem {

    public enum MenuItemType {
        PANINI, FRITTI, BEVANDE;
    }

    String getName();

    MenuItemType getType();

    double getPrice();
}
