package Sistema_de_votaciones.Redes;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String ipServidor = "127.0.0.1"; // Cambia a la IP del servidor si es otra máquina
        int puerto = 5000;

        try (Socket socket = new Socket(ipServidor, puerto);
             BufferedReader servidorEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter servidorSalida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader usuarioEntrada = new BufferedReader(new InputStreamReader(System.in))) {

            Thread leerServidor = new Thread(() -> {
                String linea;
                try {
                    while ((linea = servidorEntrada.readLine()) != null) {
                        System.out.println(linea);
                    }
                } catch (IOException e) {
                    System.out.println("Conexión cerrada por el servidor.");
                }
            });

            leerServidor.start();

            String lineaUsuario;
            while ((lineaUsuario = usuarioEntrada.readLine()) != null) {
                servidorSalida.println(lineaUsuario);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
