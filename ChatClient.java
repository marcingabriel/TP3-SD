import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 12345);
            System.out.println("Conectado ao servidor");

            // Thread para receber mensagens
            new Thread(() -> {
                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message;
                    while ((message = input.readLine()) != null) {
                        System.out.println("Recebido: \"" + message + "\"");
                    }
                } catch (IOException e) {
                    System.out.println("Conexão encerrada.");
                }
            }).start();

            // Enviando mensagens
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite suas mensagens:");

            while (true) {
                String message = scanner.nextLine();
                if (!message.trim().isEmpty()) { // Enviar apenas mensagens não vazias
                    System.out.println("Enviado: \"" + message + "\"");
                    output.println(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
