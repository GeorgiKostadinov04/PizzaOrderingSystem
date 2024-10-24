import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class PizzaClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            String userCommand;
            while ((userCommand = userInput.readLine()) != null) {
                out.println(userCommand);  // Изпращаме командата към сървъра
                System.out.println("Сървърът казва: " + in.readLine());  // Четем отговора от сървъра

                // Прекратяване, ако потребителят въведе "exit"
                if ("exit".equalsIgnoreCase(userCommand)) {
                    break;
                }
            }

        } catch (Exception e) {
            System.err.println("Грешка при свързване със сървъра: " + e.getMessage());
        }
    }
}