package interfaces;

import customexceptions.ProductAlreadyExistsException;
import customexceptions.ProductNotFoundException;
import customexceptions.UserAlreadyExistsException;
import pizzaorder.Order;
import pizzaorder.Product;
import pizzaorder.User;

public interface IPizzaStore {

    void displayActiveProducts();

    void deactivateProduct(String productName) throws ProductNotFoundException;

    void addOrder(Order order);

    void completeOrders();

    void displayCurrentOrders();

    void displayCompletedOrders();

    void repeatOrder(int index);

    void registerUser(User user) throws UserAlreadyExistsException;

    boolean login(String username, char[] password);

    void logout();
}
