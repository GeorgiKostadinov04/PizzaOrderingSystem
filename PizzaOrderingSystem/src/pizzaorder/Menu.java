package pizzaorder;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Product> availableProducts;

    public Menu() {
        availableProducts = new ArrayList<>();
        populateMenu();
    }

    public void populateMenu() {
        availableProducts.add(new Pizza("Margherita", 8.99));
        availableProducts.add(new Pizza("Pepperoni", 12.99));
        availableProducts.add(new Pizza("Veggie", 7.99));
        availableProducts.add(new Drink("Water", 3.50));
        availableProducts.add(new Drink("Coca-cola", 4.50));
        availableProducts.add(new Drink("Fanta Orange", 4.50));
    }

    public List<Product> getAvailableProducts() {
        return availableProducts;
    }

    public void displayMenu() {
        System.out.println("Menu:");
        for (int i = 0; i < availableProducts.size(); i++) {
            Product product = availableProducts.get(i);
            System.out.printf("%d. %s %s leva ",
                    i + 1, product.getName(), product.getPrice());
        }
    }
}
