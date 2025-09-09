package Sistema_de_votaciones.Programacion;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SistemaVotacionesRemoto {

    // Variables globales
    public static ArrayList<ArrayList<String>> planillaList = new ArrayList<>();
    public static ArrayList<Long> ListaID_Votantes = new ArrayList<>();
    public static ArrayList<Integer> indicesEmpatados = new ArrayList<>();
    public static boolean empate = false;
    public static int votosEmpate = 0;
    public static String fechaVotacion = "01/01/2025";
    public static String lugar = "Auditorio";
    public static int periodoGestion = 12;
    public static String motivoVotacion = "Elecciones internas";
    public static int limintatID = 5;
    public static String password = "admin";

    // Scanner y PrintWriter globales (cliente-servidor)
    public static Scanner sc;
    public static PrintWriter out;

    // -------------------- Menú Principal --------------------
    public static void Menu() {
        limpiarConsola();
        out.println("-----------------------------------");
        out.println("--Bienvenido Seleccione su opcion--");
        out.println("-----------------------------------");
        out.println();
        out.println("-----------------------------------");
        out.println("----  1 - Administrador  ----------");
        out.println("----  2 - Votante        ----------");
        out.println("----  3 - Integrantes Planillas ---");
        out.println("----  4 - Informacion    ----------");
        out.println("----  5 - Salir          ----------");
        out.println("-----------------------------------");
        out.print("Opcion: ");

        try {
            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    Admin_Loggin();
                    break;
                case 2:
                    Votaciones();
                    break;
                case 3:
                    Integrantes_Planillas();
                    break;
                case 4:
                    InformacionVotacion();
                    break;
                case 5:
                    cerrarProgram();
                    break;
                default:
                    out.println("******************************");
                    out.println("Por favor ingrese solo numeros validos");
                    out.println("******************************");
                    sc.nextLine();
                    Menu();
                    break;
            }
        } catch (Exception e) {
            sc.nextLine();
            out.println("******************************");
            out.println("Por favor ingrese solo numeros");
            out.println("******************************");
            Menu();
        }
    }

    // -------------------- Función Limpiar Consola --------------------
    public static void limpiarConsola() {
        out.println("\n\n\n\n\n"); // Simula limpieza de consola para cliente
    }

    // -------------------- Función Administrador --------------------
    static void Admin_Loggin() {
        limpiarConsola();
        out.println("---------------------------------------------------------------------------");
        out.println("-- Por favor ingrese el password de administrador para continuar: ");
        out.println("---------------------------------------------------------------------------");
        out.print("Password: ");

        String pwdUser = sc.nextLine().trim();

        if (pwdUser.isEmpty()) {
            out.println("************************************");
            out.println("* Por favor ingrese una contrasena *");
            out.println("************************************");
            Admin_Loggin();
        } else if (pwdUser.equals(password)) {
            Admin();
        } else {
            out.println("**********************************************");
            out.println("* Contrasena incorrecta, intentelo de nuevo. *");
            out.println("**********************************************");
            Menu();
        }
    }

    // -------------------- Menú Administrador --------------------
    static void Admin() {
        limpiarConsola();
        out.println("-----------------------------------");
        out.println("----- Menu Administrador ---------");
        out.println("-----------------------------------");
        out.println("1- Modificar Información de la votación");
        out.println("2- Eliminar Planillas");
        out.println("3- Ganadores");
        out.println("4- Volver al Menu");
        out.print("Opcion: ");

        try {
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    modificarInformacion();
                    break;
                case 2:
                    Eliminar_Planillas();
                    break;
                case 3:
                    Ganadores();
                    break;
                case 4:
                    Menu();
                    break;
                default:
                    out.println("Opcion invalida");
                    Admin();
                    break;
            }
        } catch (Exception e) {
            sc.nextLine();
            out.println("Opcion invalida");
            Admin();
        }
    }

     static void Eliminar_Planillas(){
        limpiarConsola();
         try (Scanner elim = new Scanner(System.in)) {

            System.out.println("-----------------------------------");
            System.out.println("------- Planillas actuales  -------");
            System.out.println("-----------------------------------");

            System.out.println();

            System.out.println("Planillas actuales: " + planillaList.size());
            if (planillaList.isEmpty()) {
                
                System.out.println("No hay planillas registradas.");

            } else {

                for (int i = 0; i < planillaList.size(); i++) {
                    System.out.println("Planilla " + (i + 1) + ": " + planillaList.get(i));
                }

                System.out.print("\nIngrese el número de planilla a eliminar o 0 para regresar: ");

                try {
                    int idx = elim.nextInt();
                    elim.nextLine();
                    if (idx > 0 && idx <= planillaList.size()) {
                        planillaList.remove(idx - 1);
                        System.out.println("Planilla eliminada.");
                    }
                } catch (Exception e) {
                    elim.nextLine();
                    System.out.println("*********************");
                    System.out.println("* Entrada inválida. *");
                    System.out.println("*********************");
                }
            }
            System.out.println("*********************************");
            System.out.println("* Presione ENTER para continuar *");
            System.out.println("*********************************");
            elim.nextLine();
            
            Admin();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error, por favor intentelo de nuevo...");
        }
    }

    // -------------------- Modificar Información --------------------
    static void modificarInformacion() {
        limpiarConsola();
        out.println("-------------------------------------");
        out.println("- Que informacion quiere modificar? -");
        out.println("--- 1- Fecha                    -----");
        out.println("--- 2- Lugar                    -----");
        out.println("--- 3- Tiempo de gestion        -----");
        out.println("--- 4- Motivo de la votacion    -----");
        out.println("--- 5- Limitador ID             -----");
        out.println("--- 6- No cambiar nada          -----");
        out.println("-------------------------------------");
        out.print("Opcion: ");

        try {
            int modificarOpcion = sc.nextInt();
            sc.nextLine();

            switch (modificarOpcion) {
                case 1:
                    limpiarConsola();
                    out.println("-------------------------------------");
                    out.println("- Ingrese la nueva fecha (dd/mm/aa) -");
                    out.println("-------------------------------------");
                    out.print("Nueva Fecha: ");
                    fechaVotacion = sc.nextLine();
                    out.println("Fecha modificada a: " + fechaVotacion);
                    break;
                case 2:
                    limpiarConsola();
                    out.println("-------------------------------------");
                    out.println("-      Ingrese el nuevo lugar       -");
                    out.println("-------------------------------------");
                    out.print("Nuevo Lugar: ");
                    lugar = sc.nextLine();
                    out.println("Lugar modificado a: " + lugar);
                    break;
                case 3:
                    limpiarConsola();
                    out.println("-----------------------------------------------");
                    out.println("- Ingrese el nuevo periodo de gestion (meses) -");
                    out.println("-----------------------------------------------");
                    out.print("Nuevo periodo: ");
                    periodoGestion = sc.nextInt();
                    sc.nextLine();
                    out.println("Periodo modificado a: " + periodoGestion + " meses");
                    break;
                case 4:
                    limpiarConsola();
                    out.println("-------------------------------------");
                    out.println("-   Ingrese el motivo de votacion   -");
                    out.println("-------------------------------------");
                    out.print("Nuevo motivo: ");
                    motivoVotacion = sc.nextLine();
                    out.println("Motivo modificado a: " + motivoVotacion);
                    break;
                case 5:
                    limpiarConsola();
                    out.println("-------------------------------------");
                    out.println("-   Ingrese el limitador de ID      -");
                    out.println("-------------------------------------");
                    out.print("Nuevo limitador: ");
                    limintatID = sc.nextInt();
                    sc.nextLine();
                    out.println("Limitador modificado a: " + limintatID + " numeros");
                    break;
                case 6:
                    Admin();
                    return;
                default:
                    out.println("Por favor ingrese una opcion valida");
                    modificarInformacion();
                    return;
            }

            out.println();
            out.println("-----------------------------");
            out.println("- Desea modificar algo mas? -");
            out.println("- 1- Si                   ---");
            out.println("- 2- No                   ---");
            out.println("-----------------------------");
            out.print("Opcion: ");

            int algoMas = sc.nextInt();
            sc.nextLine();

            switch (algoMas) {
                case 1:
                    modificarInformacion();
                    break;
                case 2:
                    Admin();
                    break;
                default:
                    out.println("Por favor ingrese una opcion valida");
                    break;
            }

        } catch (Exception e) {
            sc.nextLine();
            out.println("Por favor ingrese solo numeros");
            modificarInformacion();
        }
    }

    // -------------------- Integrantes Planillas --------------------
    static void Integrantes_Planillas() {
        limpiarConsola();

        if (planillaList.isEmpty()) {
            out.println("-- No hay plantillas registradas --");
            Menu();
            return;
        }

        for (int i = 0; i < planillaList.size(); i++) {
            ArrayList<String> planillaActual = planillaList.get(i);
            String nombrePlanilla = planillaActual.get(0);
            out.println("\nPlanilla " + (i + 1) + " [" + nombrePlanilla + "]");
            for (int j = 1; j < planillaActual.size(); j++) {
                out.println(planillaActual.get(j));
            }
        }
        Menu();
    }

    // -------------------- Votaciones --------------------
    static void Votaciones() {
        limpiarConsola();
        try {
            if (planillaList.isEmpty()) {
                out.println("No hay planillas registradas");
                Menu();
                return;
            }

            out.println("--- Por favor seleccione su planilla ---");
            for (int i = 0; i < planillaList.size(); i++) {
                ArrayList<String> planillaActual = planillaList.get(i);
                String nombrePlanilla = planillaActual.get(0);
                out.println((i + 1) + " - " + nombrePlanilla);
            }
            out.print("Ingrese su opción: ");
            int voto = sc.nextInt();
            sc.nextLine();

            if (voto >= 1 && voto <= planillaList.size()) {
                Conteo_Votos(voto - 1);
                Menu();
            } else {
                out.println("Opción inválida");
                Votaciones();
            }

        } catch (Exception e) {
            sc.nextLine();
            out.println("Entrada inválida, intente nuevamente");
            Votaciones();
        }
    }

    // -------------------- Conteo Votos --------------------
    static void Conteo_Votos(int numeroPlanilla) {
        ArrayList<String> planilla = planillaList.get(numeroPlanilla);
        String conteo = planilla.get(planilla.size() - 1);

        int votos;
        try {
            votos = Integer.parseInt(conteo);
        } catch (NumberFormatException e) {
            votos = 0;
        }
        votos++;
        planilla.set(planilla.size() - 1, String.valueOf(votos));
        out.println("Voto registrado para " + planilla.get(0));
    }

    // -------------------- Ganadores --------------------
    static void Ganadores() {
        limpiarConsola();
        int contadorVoto = -1;
        ArrayList<Integer> ganadores = new ArrayList<>();
        ArrayList<Integer> votosPorPlanilla = new ArrayList<>();

        for (int i = 0; i < planillaList.size(); i++) {
            ArrayList<String> planilla = planillaList.get(i);
            String conteo = planilla.get(planilla.size() - 1);
            int votos;
            try {
                votos = Integer.parseInt(conteo);
            } catch (NumberFormatException e) {
                votos = 0;
            }
            votosPorPlanilla.add(votos);

            if (votos > contadorVoto) {
                contadorVoto = votos;
                ganadores.clear();
                ganadores.add(i);
            } else if (votos == contadorVoto) {
                ganadores.add(i);
            }
        }

        out.println("Resultados: ");

        try {
            exportar(planillaList, votosPorPlanilla, new ArrayList<>(ListaID_Votantes));
        } catch (Exception e) {
            out.println("Error al guardar los resultados: " + e.getMessage());
        }

        if (contadorVoto == 0) {
            out.println("-------------------------------------");
            out.println("- Las planillas no recibieron votos -");
            out.println("-------------------------------------");
            Menu();
        } else if (ganadores.size() == 1) {
            int ganador = ganadores.get(0);
            String ganadorName = planillaList.get(ganador).get(0);
            out.println("------------------------------------------------------------------------------");
            out.println("La planilla con mayores votos es: " + ganadorName + " con " + contadorVoto + " votos a su favor");
            out.println("------------------------------------------------------------------------------");
            Menu();
        } else {
            empate = true;
            indicesEmpatados.clear();
            indicesEmpatados.addAll(ganadores);
            votosEmpate = contadorVoto;

            out.println("-----------------------------------------");
            out.println("--- Tenemos un empate entre: ------------");
            for (int i : ganadores) {
                String empatados = planillaList.get(i).get(0);
                out.println("Empatados: " + empatados + " con " + contadorVoto + " votos");
            }
            out.println("-----------------------------------------");
            Empatados();
        }
    }

    // -------------------- Empatados --------------------
    static void Empatados() {
        limpiarConsola();
        out.println("----------------------------------------------------");
        out.println("- Iniciando nueva votación solo para los empatados -");
        out.println("----------------------------------------------------");

        for (int idx : indicesEmpatados) {
            ArrayList<String> planilla = planillaList.get(idx);
            planilla.set(planilla.size() - 1, "0");
        }

        Menu();
    }

    // -------------------- Información Votación --------------------
    static void InformacionVotacion() {
        limpiarConsola();
        out.println("---------------------------------");
        out.println("- Informacion de las votaciones -");
        out.println("---------------------------------");
        out.println("Motivo de las votaciones: " + motivoVotacion);
        out.println("Fecha: " + fechaVotacion);
        out.println("Lugar donde se realizaran las votaciones: " + lugar);
        out.println("La duracion de Gestion de la planilla ganadora es de " + periodoGestion + " meses");
        out.println("Presiona ENTER para continuar");
        sc.nextLine();
        Menu();
    }

    // -------------------- Exportar resultados --------------------
    public static void exportar(ArrayList<ArrayList<String>> planillaList, ArrayList<Integer> votosPorPlanilla, ArrayList<Long> listaIDs) throws Exception {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String nombreArchivo = "resultado_votacion_" + fecha + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("===== RESULTADOS DE LA VOTACIÓN =====");
            writer.println("Cantidad total de planillas: " + planillaList.size());
            int totalVotos = votosPorPlanilla.stream().mapToInt(Integer::intValue).sum();
            writer.println("Total de votos: " + totalVotos);
            writer.println("Total de votantes: " + listaIDs.size());
            writer.println("IDs que votaron: " + listaIDs);

            if (votosPorPlanilla.isEmpty()) {
                writer.println("Empate:");
                writer.println("Ganador:");
                writer.println("Perdedores:");
            } else {
                int maxVotos = Collections.max(votosPorPlanilla);
                List<Integer> indicesGanadores = new ArrayList<>();
                for (int i = 0; i < votosPorPlanilla.size(); i++) {
                    if (votosPorPlanilla.get(i) == maxVotos) {
                        indicesGanadores.add(i);
                    }
                }

                if (indicesGanadores.size() > 1) {
                    writer.println("Empate: Sí");
                    for (int idx : indicesGanadores) {
                        String nombrePlanilla = planillaList.get(idx).get(0).replace("Nombre: ", "");
                        writer.println(" - " + nombrePlanilla + " (" + maxVotos + " votos)");
                    }
                    writer.println("Ganador:");
                    writer.println("Perdedores:");
                } else {
                    writer.println("Empate:");
                    int ganador = indicesGanadores.get(0);
                    String nombreGanador = planillaList.get(ganador).get(0).replace("Nombre: ", "");
                    writer.println("Ganador: " + nombreGanador + " (" + maxVotos + " votos)");
                    writer.println("Perdedores:");
                    for (int i = 0; i < votosPorPlanilla.size(); i++) {
                        if (i != ganador) {
                            String nombrePerdedor = planillaList.get(i).get(0).replace("Nombre: ", "");
                            writer.println(" - " + nombrePerdedor + " (" + votosPorPlanilla.get(i) + " votos)");
                        }
                    }
                }
            }
        }
        out.println("Resultados guardados en: " + nombreArchivo);
    }

    // -------------------- Cerrar Programa --------------------
    static void cerrarProgram() {
        limpiarConsola();
        out.println("---------------------------------------------------------------------------");
        out.println("-- Por favor ingrese el password de administrador para cerrar el programa: ");
        out.println("---------------------------------------------------------------------------");
        out.print("Password: ");

        String pwdUser = sc.nextLine().trim();

        if (pwdUser.isEmpty()) {
            out.println("************************************");
            out.println("* Por favor ingrese una contrasena *");
            out.println("************************************");
            Admin_Loggin();
        } else if (pwdUser.equals(password)) {
            limpiarConsola();
            out.println("----------------------------------");
            out.println("Programa cerrado de manera exitosa");
            out.println("----------------------------------");
            System.exit(0);
        } else {
            out.println("**********************************************");
            out.println("* Contrasena incorrecta, intentelo de nuevo. *");
            out.println("**********************************************");
            Menu();
        }
    }

    // -------------------- Main --------------------
    public static void main(Scanner scanner, PrintWriter writer) {
        sc = scanner;
        out = writer;
        Menu();
    }
}
