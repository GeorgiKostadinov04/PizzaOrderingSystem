package pizzaorder;

import interfaces.IDeliveryPerson;

public class DeliveryPerson implements IDeliveryPerson {
    private String name;

    public DeliveryPerson(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void deliver(Order order) {
        System.out.println("Delivery person " + name + " is delivering the order to " + order.getCustomer().getUsername());
    }
}
