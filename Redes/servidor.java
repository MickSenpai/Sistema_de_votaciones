import java.io.*;
import java.net.*;

public class servidor {
    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {

            System.out.println("ervidor escuchando en puerto" + puerto + "...");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado desde " + cliente.getInetAddress());
                BufferedReader entrada = new BufferedReader(
                new InputStreamReader(cliente.getInputStream())
                );
            
                PrintWriter archivo = new PrintWriter(new FileWriter("respuestas.txt", true));
                archivo.println("===== Nueva encuesta =====");
                String linea;
                while ((linea = entrada.readLine()) != null && !linea.isEmpty()) {
                archivo.println(linea);
                }
                archivo.println();
                archivo.close();
                cliente.close();

                System.out.println("Encuesta guardada correctamente.");
            }

        } catch (IOException e) {

            System.out.println("Error en el servidor:" + e.getMessage());
            
        }

    }
}