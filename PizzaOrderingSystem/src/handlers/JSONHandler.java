package handlers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pizzaorder.Order;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JSONHandler {
    private static final String ORDER_FILE = "orders.json";

    public static void saveOrders(List<Order> newOrders) throws IOException {
        Gson gson = new Gson();
        List<Order> existingOrders = new ArrayList<>();

        try (FileReader reader = new FileReader(ORDER_FILE)) {
            existingOrders = gson.fromJson(reader, new TypeToken<List<Order>>(){}.getType());
            if (existingOrders == null) {
                existingOrders = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("No existing orders found or file does not exist.");
        }

        existingOrders.addAll(newOrders);

        try (FileWriter writer = new FileWriter(ORDER_FILE)) {
            gson.toJson(existingOrders, writer);
            System.out.println("Orders saved successfully.");
        }
    }

    public static List<Order> loadOrders() throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(ORDER_FILE)) {
            System.out.println("The orders are successfully loaded!");
            return gson.fromJson(reader, new TypeToken<List<Order>>(){}.getType());
        }
        catch (IOException e){
            System.err.println("Грешка при зареждането на поръчките: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}