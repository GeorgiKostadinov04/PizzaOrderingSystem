package pizzaorder;

public class Customer extends User {
    private String address;

    public Customer(String username, char[] password,String address){
        super(username, password);
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }
}
