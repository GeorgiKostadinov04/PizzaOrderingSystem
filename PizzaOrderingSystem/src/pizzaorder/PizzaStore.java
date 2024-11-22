package pizzaorder;

import customexceptions.InvalidLoginException;
import customexceptions.ProductAlreadyExistsException;
import customexceptions.ProductNotFoundException;
import customexceptions.UserAlreadyExistsException;
import handlers.JSONHandler;
import interfaces.IPizzaStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PizzaStore implements IPizzaStore {
    private Menu menu;
    private List<Order> currentOrders;
    private List<Order> completedOrders;
    private List<User> users;
    private User loggedInUser;

    public PizzaStore(){
        menu.populateMenu();
        currentOrders = new ArrayList<>();
        completedOrders = new ArrayList<>();
        users = new ArrayList<>();
        loggedInUser = null;
    }

    public User getLoggedInUser(){
        return this.loggedInUser;
    }

    public void setLoggedInUser(User user){
        loggedInUser = user;
    }

    public List<Order> getCompletedOrders(){
        return this.completedOrders;
    }

    public List<Order> getCurrentOrders(){
        return this.currentOrders;
    }

    public void setCompletedOrders(List<Order> orders){
        this.completedOrders = orders;
    }

    @Override
    public void displayActiveProducts() {
        System.out.println("Active products:");
        for(Product product : menu.getAvailableProducts())
            if (product.isActive()) {
                System.out.println("- " + product.getName() + ": " + product.getPrice() + " BGN.");
            }
    }

    @Override
    public void deactivateProduct(String productName) throws ProductNotFoundException {
        try {
            Product product = getProductByName(productName);
            product.deactivate();
            System.out.println(productName + " has been deactivated.");

        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public Product getProductByName(String name) throws ProductNotFoundException {
        for(Product product : menu.getAvailableProducts()){
            if(product.getName().equals(name)){
                return product;
            }
        }
        throw new ProductNotFoundException("Product with name " + name + " not found.");
    }

    @Override
    public void addOrder(Order order) {
        currentOrders.add(order);
        System.out.println("Order added for customer: " + order.getCustomer().getUsername());
    }

    @Override
    public void completeOrders() {
        try{
            if(!getCurrentOrders().isEmpty()){
                for(Order order : getCurrentOrders()){
                    order.completeOrder();
                }
                JSONHandler.saveOrders(getCurrentOrders());
                getCurrentOrders().clear();
                System.out.println("All orders completed");
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void displayCurrentOrders() {
        System.out.println("Current orders:");
        for (Order order : getCurrentOrders()) {
            order.printOrderDetails();
        }
    }

    @Override
    public void displayCompletedOrders() {
        try{
            setCompletedOrders(JSONHandler.loadOrders());
            for(Order order : getCompletedOrders()){
                order.printOrderDetails();
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void repeatOrder(int index) {
        if(completedOrders.isEmpty() || index < 0 || (!completedOrders.isEmpty() && index > completedOrders.size())){
            throw new NoSuchElementException("Error! Try again later.");
        }
        addOrder(getCompletedOrders().get(index));
        System.out.println("Order " + index + " repeated");


    }

    public void registerUser(User user) throws UserAlreadyExistsException {
        for (User existingUser : users) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                throw new UserAlreadyExistsException("User already exists: " + user.getUsername());
            }
        }
        users.add(user);
        System.out.println(user.getUsername() + " has been registered.");

    }

    public boolean login(String username, char[] password){
        try{
            for (User user : users){
                if(user.login(username,password)){
                    loggedInUser = user;
                    System.out.println("Successful login attempt.");
                    return true;
                }
            }
        }
        catch (InvalidLoginException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void logout(){
        if(getLoggedInUser() != null){
            getLoggedInUser().logout();
            setLoggedInUser(null);
        }
        else{
            System.out.println("No user is currently logged in.");
        }
    }


}
