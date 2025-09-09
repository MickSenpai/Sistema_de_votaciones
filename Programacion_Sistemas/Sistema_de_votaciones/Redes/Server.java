package Sistema_de_votaciones.Redes;

import Sistema_de_votaciones.Programacion.SistemaVotaciones;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexiones en el puerto " + puerto);

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clienteSocket.getInetAddress());

                // Streams de entrada/salida con el cliente
                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(clienteSocket.getInputStream()));
                PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);

                // Redirigimos System.out y System.in al cliente
                PrintStream originalOut = System.out;
                InputStream originalIn = System.in;

                try {
                    System.setOut(new PrintStream(clienteSocket.getOutputStream(), true));
                    System.setIn(clienteSocket.getInputStream());

                    // Ejecutamos el sistema de votaciones completo
                    SistemaVotaciones.main(new String[]{});
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.setOut(originalOut);
                    System.setIn(originalIn);
                    clienteSocket.close();
                    System.out.println("Cliente desconectado.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
