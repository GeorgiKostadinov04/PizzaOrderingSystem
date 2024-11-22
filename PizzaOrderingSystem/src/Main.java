import pizzaorder.*;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();
        if(console == null){
            System.out.println("No console available");
            return;
        }

        String username = console.readLine("Enter username: ");
        char[] password = console.readPassword("Enter password: ");

        Customer customer = new Customer(username, password, "bul. Malinov");
        if(customer.checkPassword(("Password").toCharArray())){
            System.out.println("Right password");
        }
        Order order = new Order(customer);


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
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Thank you for your order! Here are the details:");
        order.printOrderDetails();
        scanner.close();
    }
}