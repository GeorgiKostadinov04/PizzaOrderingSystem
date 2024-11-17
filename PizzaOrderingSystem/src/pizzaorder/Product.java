package pizzaorder;

public class Product {
    protected String name;
    protected double price;
    protected boolean isActive;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.isActive = true;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public boolean isActive(){
        return this.isActive;
    }

    public void deactivate(){
        this.isActive = false;
    }

    public void activate(){
        this.isActive = true;
    }


}
