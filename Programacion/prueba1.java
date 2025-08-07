package Programacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class prueba1 {

    static void Menu(){
        limpiarConsola();
        try (Scanner menu = new Scanner(System.in)) {
            System.out.println("-----------------------------------");
            System.out.println("--Bienvenido Seleccione su opcion--");
            System.out.println("-----------------------------------");
            System.out.println();
            System.out.println("-----------------------------------");
            System.out.println("----  1 - Administrador  ----------");
            System.out.println("----  2 - Votante        ----------");
            System.out.println("----  3 - Salir          ----------");
            System.out.println("-----------------------------------");
            try {
                System.out.print("Opcion: ");
                int menuOpcion = menu.nextInt();
                
                switch (menuOpcion) {
                    case 1:
                        Admin_Loggin();
                        break;
                    case 2:
                        Votante_Loggin();
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("*************************");
                        System.out.println("Ingrese una opcion valida");
                        System.out.println("*************************");
                        break;
                }
            } catch (Exception e) {
                menu.nextLine();
                System.out.println("******************************");
                System.out.println("Por favor ingrese solo numeros");
                System.out.println("Presiona ENTER para continuar");
                System.out.println("******************************");
                @SuppressWarnings("unused")
                String message = menu.nextLine();
                
                Menu();
            }
        }
    }

    static void Admin_Loggin(){

        limpiarConsola();

        String passwd = "BoKuLe1967"; // Contrasena modificable 
        try (Scanner password = new Scanner(System.in)) {
            System.out.println("-----------------------------------------------------");
            System.out.print  ("-- Por favor ingrese el password de administrador: ");
            System.out.println("-----------------------------------------------------");

            System.out.print("Password: ");
            String pwdUser = password.next();
            
            if(pwdUser.trim().isEmpty()){
                password.nextLine();
                System.out.println("Por favor ingrese una contrasena");
                @SuppressWarnings("unused")
                String message = password.nextLine();

                Admin_Loggin();
            }else if (pwdUser.equals(passwd)) {
                Admin();
            } else {
                password.nextLine();
                System.out.println();
                System.out.println("Contrasena incorrecta, intentelo de nuevo.");
                System.out.println("Presione ENTER para continuar");
                @SuppressWarnings("unused")
                String message = password.nextLine();
                
                Menu();
            }
        }
    }

    static void Votante_Loggin(){
        limpiarConsola();
        try (Scanner matricula = new Scanner(System.in)) {
            System.out.println("-----------------------------------");
            System.out.println("---- Inicio de sesion votante -----");
            System.out.println("-----------------------------------");
            System.out.println();

            //Manejo de error en caso de que agregue algo que no sean numeros.
            try {
                System.out.print("Ingresa tu ID: "); //ID = Matricula
                String id = matricula.nextLine();

                if(id.trim().isEmpty()){
                    System.out.println("No se ha ingresado ningun dato. Por favor, intente de nuevo.");
                    Votante_Loggin();
                } else {
                    try {
                        int idNumero = Integer.parseInt(id.trim());
                        Listado_IDs(idNumero); //Se manda a validacion en la funcion Listado_IDs
                    } catch (Exception e) {
                        System.out.println();
                        System.out.println("****************************************");
                        System.out.println("Ha ocurrido un error... Intente de nuevo");
                        System.out.println("---- Presione ENTER para continuar -----");
                        System.out.println("****************************************");
                        @SuppressWarnings("unused")
                        String message = matricula.nextLine();

                        Menu();
                    }
                }
            } catch (Exception e) {
                matricula.nextLine();
                
                System.out.println();
                System.out.println("******************************");
                System.out.println("Por favor ingrese solo numeros");
                System.out.println("Presione ENTER para continuar");
                System.out.println("******************************");
                @SuppressWarnings("unused")
                String message = matricula.nextLine();
                
                Votante_Loggin();
            }
        }
    }

    static ArrayList <Integer> ListaID_Votantes = new ArrayList<>(); //Creamos el listado de IDs (GLobal)

    static void Listado_IDs(int id){ //Se hace la validacion de ID existente

        boolean id_Existente = ListaID_Votantes.contains(id);

        if(id_Existente == true){
            UsuarioRepetido();
        } else {
            ListaID_Votantes.add(id);
            Votante_Menu();
        }
    }

    static void UsuarioRepetido(){ try ( //Muestra mensaje de repeticion
        Scanner repetido = new Scanner(System.in)) {
        System.out.println();
        System.out.println("****************************");
        System.out.println("El usuario ya esta registado");
        System.out.println("Presione ENTER para continuar");
        System.out.println("****************************");
        @SuppressWarnings("unused")
        String repeticion = repetido.nextLine();
        
        Votante_Loggin();
        }
    }

    static void Votante_Menu(){
        limpiarConsola();
        try (Scanner opcion_Votante = new Scanner(System.in)) {
            System.out.println("-----------------------------------");
            System.out.println("------ Bienvenido Votante    ------");
            System.out.println("-----------------------------------");
            System.out.println();
            
            System.out.println("-----------------------------------");
            System.out.println("------ Selecciona tu opcion: ------");
            System.out.println("------ 1- Votar              ------");
            System.out.println("------ 2- Ver plantillas     ------");
            System.out.println("------ 3- Regresar           ------");
            System.out.println("-----------------------------------");
            System.out.print("Opcion: ");

            int opcion = opcion_Votante.nextInt();
            
            switch (opcion) {
                case 1 -> Votaciones();
                case 2 -> Integrantes_Planillas();
                case 3 -> Votante_Loggin();
                default -> System.out.println("Ingresa una opcion valida");
            }
        }
    }

    static void Admin(){
        limpiarConsola();
        try (Scanner adminOpcion = new Scanner(System.in)) {
            System.out.println("-----------------------------------");
            System.out.println("---- Bienvenido Administrador  ----");
            System.out.println("-----------------------------------");
            System.out.println();
            
            System.out.println("-----------------------------------");
            System.out.println("------ Selecciona tu opcion: ------");
            System.out.println("------ 1- Agregar planillas  ------");
            System.out.println("------ 2- Eliminar planillas ------");
            System.out.println("------ 3- Integrantes planillas ---");
            System.out.println("------ 4- Regresar al menu   ------");
            System.out.println("------ 5- Finalizar votacion ------");
            System.out.println("-----------------------------------");
            System.out.print("Opcion: ");
            int opcion = adminOpcion.nextInt();
            
            switch (opcion) {
                case 1 -> Agregar_Planillas();
                case 2 -> Eliminar_Planillas();
                case 3 -> Integrantes_Planillas();
                case 4 -> Menu();
                case 5 -> System.exit(0);
                default -> System.out.println("Ingresa una opcion valida");
            }
        }
    }

    static ArrayList<ArrayList <String>>  planillaList = new ArrayList<>(); //Listado tiulos planillas (Global)

   static void Agregar_Planillas() {
    
    limpiarConsola();
    try (Scanner planillas = new Scanner(System.in)) {
        System.out.println("-----------------------------------");
        System.out.println("------- Agregar Planillas   -------");
        System.out.println("-----------------------------------");
        System.out.println();
        try {
            System.out.println("Cuantas planillas desea agregar?");
            int cantidadPlanillas = planillas.nextInt();
            
            for(int p = 1; p <= cantidadPlanillas; p++){
                ArrayList<String> Titulos = new ArrayList<>();
            
                planillas.nextLine();
                
                System.out.println("Planilla " + p);
                
                System.out.print("Ingrese el nombre de la planilla: "); //Posible a remover
                String nombrePlanilla = planillas.nextLine();
                Titulos.add(nombrePlanilla); 

                int numIntegrantes;

                System.out.println("Cuantos integrantes habra en la planilla?");
                numIntegrantes = planillas.nextInt();
                planillas.nextLine();
                
                ArrayList<String> cargos = new ArrayList<>();
                for(int i = 1; i <= numIntegrantes; i++){
                    System.out.print("Ingrese el cargo del integrante " + i + ": ");
                    String cargo = planillas.nextLine();
                    cargos.add(cargo);
                }
                
                System.out.println();
                
                for(int i = 0; i < numIntegrantes; i++){
                    System.out.print("Ingrese el nombre para " + cargos.get(i) + ": ");
                    String nombre = planillas.nextLine();
                    Titulos.add(cargos.get(i) + ": " + nombre);  
                }
                
                planillaList.add(Titulos);
            }

            
            System.out.println();
            System.out.println("Los datos son correctos?");
            System.out.println("1- Si");
            System.out.println("2- No");
            System.out.print("Opcion: ");
            int datosIngresados = planillas.nextInt();
            
            int guardarDatos = datosIngresados;

            System.out.println();
            
            switch (guardarDatos) {
                case 1 :System.out.println("Datos guardados correctamente"); break;
                case 2 : System.out.println("Datos eliminados"); planillaList.removeAll(planillaList); break;
                default: System.out.println("Por favor, ingrese una opcion valida"); break;
            }
            
            System.out.println();
            
            System.out.println("Le gustaria agregar otra planilla?");
            System.out.println("1- Si");
            System.out.println("2- No");
            System.out.println("3- Eliminar planillas agregadas");
            System.out.print("Opcion: ");
            int agregarPlanilla = planillas.nextInt();
            
            switch (agregarPlanilla) {
                case 1 -> Agregar_Planillas();
                case 2 -> Admin();
                case 3 -> Eliminar_Planillas();
                default -> System.out.println("Ingrese una opcion valida");
            }

        } catch (Exception e) {
            planillas.nextLine();
            System.out.println();
            System.out.println("****************************");
            System.out.println("Algo ha salido mal, por favor intentalo de nuevo...");
            System.out.println("Presione ENTER para continuar");
            System.out.println("****************************");
            @SuppressWarnings("unused")
            String repeticion = planillas.nextLine();
            Agregar_Planillas();
        }
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
                    System.out.println("Entrada inválida.");
                }
            }
            
            System.out.println("Presione ENTER para continuar");
            elim.nextLine();
            
            Admin();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error, por favor intentelo de nuevo...");
        }
    }

    static void Integrantes_Planillas() {
        limpiarConsola();
        Scanner integrantes = new Scanner(System.in);

        System.out.println("-----------------------------------");
        System.out.println("---Integrantes de las plantillas---");
        System.out.println("-----------------------------------");
        
        if (planillaList.isEmpty()) {
            System.out.println("No hay plantillas registradas");
            System.out.println();
            System.out.println("Presiona ENTER para volver al menu...");
            integrantes.nextLine();
            Votante_Menu();
        }

        // Mostrar cada planilla
        for (ArrayList<String> planilla : planillaList) {
            // El primer elemento es el nombre de la planilla
            System.out.println("\nplantilla [" + planilla.get(0) + "]");
            
            // Mostrar cada cargo y persona (empezando desde el segundo elemento)
            for (int j = 1; j < planilla.size(); j++) {
                System.out.println(planilla.get(j));
            }
        }
        
        System.out.println("\n-----------------------------------");
        System.out.println("--- Presione ENTER para volver  ---");
        System.out.println("-----------------------------------");

        integrantes.nextLine();
        Votaciones();

        integrantes.close();
    }

    static void Votaciones(){
        limpiarConsola();
        try (Scanner votacion = new Scanner(System.in)){
            System.out.println("-----------------------------------------");
            System.out.println("--------   Sistema de votaciones  -------");
            System.out.println("-----------------------------------------");
            System.out.println();
            System.out.println("-----------------------------------------");
            System.out.println("--- Por favor seleccione su planilla  ---");
            System.out.println("-----------------------------------------");
            

            for (int i = 0; i < planillaList.size(); i++) {
                ArrayList<String> planillaActual = planillaList.get(i);
            
                // Obtener el nombre de la planilla (primer elemento)
                String nombrePlanilla = planillaActual.get(0);
                System.out.println("\nPlanilla " + (i+1) + "[" + nombrePlanilla + "]");

                for(int j = 1; j < planillaActual.size(); j++) {
                    String[] partes = planillaActual.get(j).split(": ");
                    if(partes.length > 1) {
                        System.out.println("puesto" + j + " : " + partes[1]); 
                    } else {
                        System.out.println("puesto" + j + " : Formato incorrecto");
                    }
                }
            }

            System.out.println();

            try {
                System.out.print("Ingrese su opcion: ");
                int voto = votacion.nextInt();

                ArrayList<String> planillasVotada = planillaList.get(voto);

                if (voto > 0 && voto <= planillaList.size()) {
                    System.out.println("------------------------------");
                    System.out.println("La planillas seleccionada es: ");
                    System.out.println("------------------------------");

                    String nombrePlanilla = planillasVotada.get(0);
                    System.out.println("\nPlanilla: " + nombrePlanilla);

                    for(int j = 1; j < planillasVotada.size(); j++) {
                        String[] partes = planillasVotada.get(j).split(": ");
                        if(partes.length > 1) {
                            System.out.println("puesto" + j + " : " + partes[1]); 
                        } else {
                            System.out.println("puesto" + j + " : Formato incorrecto");
                        }
                    }

                    System.out.println();
                    System.out.println("--------------------------------");
                    System.out.println("--- Es correcta su eleccion? ---");
                    System.out.println("--- 1- Si              ---------");
                    System.out.println("--- 2- No              ---------");
                    System.out.println("--------------------------------");
                    int confirmacionVoto = votacion.nextInt();

                    switch (confirmacionVoto) {
                        case 1:
                            Conteo_Votos(confirmacionVoto);
                            break;
                            
                        case 2:
                            Votaciones();
                            break;
                    
                        default:
                            System.out.println("Ingrese una opcion valida, por favor.");
                        break;
                    }
                }

            } catch (Exception e) {
                votacion.nextLine();
                System.out.println("Entrada inválida.");
                @SuppressWarnings("unused")
                String message = votacion.nextLine();

                Menu();
            }

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error, por favor intentelo de nuevo.");
        }
    }

    public static void Conteo_Votos(int numeroPlanilla) {
        ArrayList<String> planillaVotada = planillaList.get(numeroPlanilla);

        String nombrePlanilla = planillaVotada.get(0);
        System.out.println("\nSe registró un voto para la Planilla [" + nombrePlanilla + "]");

        for (int i = 1; i < planillaVotada.size(); i++) {
            String[] partes = planillaVotada.get(i).split(": ");
            if (partes.length == 2) {
                String puesto = partes[0];
                try {
                    int votos = Integer.parseInt(partes[1].trim());
                    votos++;
                    // Se actualiza lista
                    planillaVotada.set(i, puesto + ": " + votos);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error al leer votos del puesto: " + puesto);
                }
            }
        }
    }



    public static void main(String[] args){
        Menu();
    }

    public static void limpiarConsola() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al limpiar la consola: " + e.getMessage());
        }
    }
}