package pizzaorder;

import enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int idCounter = 0;
    private int orderId;
    private List<Product> products = new ArrayList<>();
    private User customer;
    private OrderStatus status;

    public Order(User customer, List<Product> products){
        this.orderId = ++idCounter;
        this.products = products;
        this.customer = customer;
        this.status = OrderStatus.CURRENT;
    }

    public Order(Order originalOrder) {
        this.orderId = ++idCounter;
        this.customer = originalOrder.customer;
        this.products = originalOrder.products;
        this.status = OrderStatus.CURRENT;
    }

    public int getOrderId() {
        return orderId;
    }

    public User getCustomer(){
        return this.customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        if(product.isActive()){
            products.add(product);
            System.out.println(product.getName() + " added to the order.");
        } else if (!product.isActive()) {
            System.out.println("Product " + product.getName() + " is deactivated and cannot be ordered.");
        }
        else System.out.println("Non existing product.");
    }

    public void completeOrder(){
        this.status = OrderStatus.COMPLETED;
    }

    public boolean isCompleted(){
        return this.status == OrderStatus.COMPLETED;
    }

    public void printOrderDetails(){
        System.out.println("Order for customer: " + customer.getUsername());
        for (Product product : products){
            System.out.println("- " + product.getName()+ ": " + product.getPrice() + " BGN.");
        }
        System.out.println("Status: " + (status == OrderStatus.CURRENT ? "Current" : "Completed"));
    }

    public void removeProduct(Product product){
        if(products.contains(product)){
            products.remove(product);
            System.out.println(product.getName() + " removed from the order.");
        }
        else System.out.println(product.getName() + " isn't part of the order");
    }
}
