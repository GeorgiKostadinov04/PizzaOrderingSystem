import pizzaorder.Menu;
import pizzaorder.Order;
import pizzaorder.Product;
import pizzaorder.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        User user = new User("Georgi", "12324412");
        user.setPassword("ilovemydog");
        user.checkPassword("ilovemydog");
        Order order = new Order(user,menu.getAvailableProducts());

        System.out.println("Welcome to the Pizza Ordering System!");

        boolean ordering = true;
        while (ordering) {
            menu.displayMenu();
            System.out.print("Enter the number of the pizza you want to order (or 0 to finish): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                ordering = false;
            } else if (choice > 0 && choice <= menu.getAvailableProducts().size()) {
                Product selectedProduct = menu.getAvailableProducts().get(choice - 1);
                order.addProduct(selectedProduct);
                System.out.println("Added " + selectedProduct.getName() + " to your order.");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Thank you for your order! Here are the details:");
        order.printOrderDetails();
        scanner.close();
    }
}