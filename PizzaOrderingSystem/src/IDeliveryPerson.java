public interface IDeliveryPerson {
    String getName();

    void setName(String name);

    void deliver(Order order);
}
