import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PizzaServer {
    private static final int PORT = 12345;
    private static final int THREAD_POOL_SIZE = 10;
    private static List<Order> orders;

    public static void main(String[] args) {
        // Зареждане на поръчките от JSON файл при стартиране на сървъра
        try{
            orders = JSONHandler.loadOrders();
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }


        // Създаваме thread pool за многопоточност
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сървърът слуша на порт " + PORT);

            // Стартираме сървъра и приемаме множество клиенти
            while (true) {
                Socket clientSocket = serverSocket.accept();  // Приемане на нова клиентска връзка
                System.out.println("Нов клиент се свърза!");

                // Стартираме нов ClientHandler за всеки клиент
                executorService.execute(new ClientHandler(clientSocket, orders));
            }

        } catch (IOException e) {
            System.err.println("Грешка при стартиране на сървъра: " + e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }
}