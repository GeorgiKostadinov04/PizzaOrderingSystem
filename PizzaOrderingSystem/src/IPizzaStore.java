public interface IPizzaStore {
    void addProduct(Product product) throws ProductAlreadyExistsException;

    void displayActiveProducts();

    void deactivateProduct(String productName) throws ProductNotFoundException;

    void addOrder(Order order);

    void completeOrders();

    void displayCurrentOrders();

    void displayCompletedOrders();

    void repeatOrder(int index);

    void registerUser(User user) throws UserAlreadyExistsException;

    boolean login(String username, String password);

    void logout();
}
