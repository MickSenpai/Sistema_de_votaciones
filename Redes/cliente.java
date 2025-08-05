import java.io.*;
import java.net.*;
import java.util.Scanner;
public class cliente {
    public static void main(String[] args) {
        String servidorIP = "127.0.0.1"; // Cambiar a la IP real en pruebas de red
        int puerto = 5000;

        try (Socket socket = new Socket(servidorIP, puerto)) {
            Scanner scanner = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.print("Ingresa tu nombre: ");
            String nombre = scanner.nextLine();
            out.println("Nombre: " + nombre);

            String[] preguntas = {
                "¿Qué opinas de las instalaciones de la universidad?",
                "¿Qué te parecen los servicios administrativos?",
                "¿Recomendarías esta universidad a otros?",
                "¿Qué materia te ha gustado más hasta ahora?",
                "¿Qué mejorarías en la universidad?"
            };

            for (String pregunta : preguntas) {
                System.out.println(pregunta);
                String respuesta = scanner.nextLine();
                out.println(pregunta + " "+ respuesta);
            }

            System.out.println("Gracias por responder la encuesta.");

            scanner.close();
        } catch (IOException e) {

            System.out.println("Error en el cliente: " + e.getMessage());

        }
    }

}