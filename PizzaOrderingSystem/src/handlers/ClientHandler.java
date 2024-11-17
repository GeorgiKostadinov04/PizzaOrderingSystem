package handlers;

import pizzaorder.Order;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final List<Order> orders;

    public ClientHandler(Socket clientSocket, List<Order> orders) {
        this.clientSocket = clientSocket;
        this.orders = orders;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            // Примерно обработване на клиентски заявки
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Получено от клиента: " + inputLine);

                // Обработка на поръчка или друга заявка от клиента
                if ("getOrders".equalsIgnoreCase(inputLine)) {
                    out.println("Текущи поръчки: " + orders.toString());
                } else if ("exit".equalsIgnoreCase(inputLine)) {
                    out.println("Прекратяване на връзката с клиента.");
                    break;
                } else {
                    out.println("Неизвестна команда: " + inputLine);
                }
            }

        } catch (IOException e) {
            System.err.println("Грешка в обработката на клиента: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Неуспешно затваряне на сокета: " + e.getMessage());
            }
        }
    }
}