package Programacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class prueba1 {

    static String password = "BoKuLe1967"; //Contrasena que puede modificarse
    static int limintatID = 10; //Limitante ID que puede ser ajustable
    static int periodoGestion = 4; //En meeses
    static String fechaVotacion = "08/13/25";
    static String motivoVotacion = "Escolar";
    static String lugar = "Universidad Tecnologica de Tijuana";
    static boolean empate = false;

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
            System.out.println("----  3 - Integrantes Planillas ---");
            System.out.println("----  4 - Informacion    ----------");
            System.out.println("----  5 - Salir          ----------");
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
                        Integrantes_Planillas();
                        break;
                     case 4:
                        InformacionVotacion();
                        break;
                    case 5:
                        cerrarProgram();
                        break;
                    default:
                        menu.nextLine();
                        System.out.println();
                        System.out.println("******************************");
                        System.out.println("* Ingrese una opcion valida  *");
                        System.out.println(" Presiona ENTER para continuar");
                        System.out.println("******************************");
                        @SuppressWarnings("unused")
                        String message = menu.nextLine();
                        
                        Menu();
                }
            } catch (Exception e) {
                menu.nextLine();
                System.out.println();
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

        String passwd = password;

        limpiarConsola();

        try (Scanner password = new Scanner(System.in)) {
            System.out.println("-----------------------------------------------------");
            System.out.println("-- Por favor ingrese el password de administrador ---");
            System.out.println("-----------------------------------------------------");

            System.out.print("Password: ");
            String pwdUser = password.next();
            
            if(pwdUser.trim().isEmpty()){
                password.nextLine();
                System.out.println("************************************");
                System.out.println("* Por favor ingrese una contrasena *");
                System.out.println("************************************");
                @SuppressWarnings("unused")
                String message = password.nextLine();

                Admin_Loggin();
            }else if (pwdUser.equals(passwd)) {
                Admin();
            } else {
                password.nextLine();
                System.out.println();
                System.out.println("**********************************************");
                System.out.println("* Contrasena incorrecta, intentelo de nuevo. *");
                System.out.println("*******  Presione ENTER para continuar  ******");
                System.out.println("**********************************************");
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
                
                } else if(id.length() != limintatID){
                    
                    System.out.println();
                    System.out.println("******************************************");
                    System.out.println("El ID debe contener " + limintatID + " charactares/digitos");
                    System.out.println("*****   Por favor intente de nuevo   *****");
                    System.out.println("******************************************");
                    @SuppressWarnings("unused")
                    String message = matricula.nextLine();

                    Menu();
                } else {
                    long idNumero = Long.parseLong(id.trim());
                    Listado_IDs(idNumero); //Se manda a validacion a la funcion Listado_IDs para ver si es repetido
                }

            } catch (NumberFormatException e) {
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
    }

    static ArrayList <Long> ListaID_Votantes = new ArrayList<>(); //Creamos el listado de IDs (GLobal)

    static void Listado_IDs(Long id){ //Se hace la validacion de ID existente

        boolean id_Existente = ListaID_Votantes.contains(id);

        if(id_Existente == true){
            UsuarioRepetido();
        } else {
            ListaID_Votantes.add(id);
            Votante_Menu();
        }
    }

    static void UsuarioRepetido(){ 
        try ( //Muestra mensaje de repeticion
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

        Scanner opcion_Votante = new Scanner(System.in);
        try{
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
                case 3 -> Menu();
                default -> System.out.println("Ingresa una opcion valida");
            }
        } catch (Exception e) {
            opcion_Votante.nextLine();
            
            System.out.println();
            System.out.println("******************************");
            System.out.println("Por favor ingrese solo numeros");
            System.out.println("Presione ENTER para continuar");
            System.out.println("******************************");
            @SuppressWarnings("unused")
            String message = opcion_Votante.nextLine();
            
            Votante_Menu();
        }

        opcion_Votante.close();
    }

    static void Admin(){
        limpiarConsola();
        Scanner adminOpcion = new Scanner(System.in);

        try{
            System.out.println("-----------------------------------");
            System.out.println("---- Bienvenido Administrador  ----");
            System.out.println("-----------------------------------");
            System.out.println();
            
            System.out.println("-----------------------------------");
            System.out.println("------ Selecciona tu opcion: ------");
            System.out.println("------ 1- Agregar planillas  ------");
            System.out.println("------ 2- Eliminar planillas ------");
            System.out.println("------ 3- Modificar informacion ---");
            System.out.println("------ 4- Regresar al menu   ------");
            System.out.println("------ 5- Conocer Resultados ------");
            System.out.println("------ 6- Finalizar votacion ------");
            System.out.println("-----------------------------------");
            System.out.print("Opcion: ");
            int opcion = adminOpcion.nextInt();
            
            switch (opcion) {
                case 1 -> Agregar_Planillas();
                case 2 -> Eliminar_Planillas();
                case 3 -> modificarInformacion();
                case 4 -> Menu();
                case 5 -> Ganadores();
                case 6 -> cerrarProgram();
                default -> System.out.println("Ingresa una opcion valida");
            }
        } catch (Exception e) {
            adminOpcion.nextLine();
            System.out.println();
            System.out.println("******************************");
            System.out.println("Por favor ingrese solo numeros");
            System.out.println("Presiona ENTER para continuar");
            System.out.println("******************************");
            @SuppressWarnings("unused")
            String message = adminOpcion.nextLine();
            
            Menu();
        }

        adminOpcion.close();
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
                limpiarConsola();

                System.out.println("-----------------------------------");
                System.out.println("------- Agregar Planillas   -------");
                System.out.println("-----------------------------------");
                System.out.println();

                System.out.print("Cuantas planillas desea agregar?: ");
                int cantidadPlanillas = planillas.nextInt();

                planillas.nextLine();

                System.out.println();
                
                for(int p = 1; p <= cantidadPlanillas; p++){
                    ArrayList<String> Titulos = new ArrayList<>();
                    
                    System.out.println("Planilla " + p);
                    
                    System.out.print("Ingrese el nombre de la planilla: "); //Posible a remover
                    String nombrePlanilla = planillas.nextLine();
                    Titulos.add(nombrePlanilla); 

                    int numIntegrantes;

                    System.out.print("Cuantos integrantes habra en la planilla?: ");
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

                    System.out.println();

                    planillaList.add(Titulos);
                }
                
                System.out.println();
                System.out.println("-------------------------------------------");
                System.out.println("-------- Los datos son correctos?  --------");
                System.out.println("----  1- Si                    ------------");
                System.out.println("----  2- No                    ------------");
                System.out.println("----  3- Eliminar planillas agregadas -----");
                System.out.println("-------------------------------------------");
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
                
                System.out.println("-------------------------------------------");
                System.out.println("--- Le gustaria agregar otra planilla?  ---");
                System.out.println("----  1- Si                    ------------");
                System.out.println("----  2- No                    ------------");
                System.out.println("----  3- Eliminar planillas agregadas -----");
                System.out.println("-------------------------------------------");
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
                System.out.println("****************************************************");
                System.out.println("* Algo ha salido mal, por favor intentalo de nuevo *");
                System.out.println("********   Presione ENTER para continuar   *********");
                System.out.println("****************************************************");
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

    static void Integrantes_Planillas() {
        limpiarConsola();
        Scanner integrantes = new Scanner(System.in);

        System.out.println("-----------------------------------");
        System.out.println("---Integrantes de las plantillas---");
        System.out.println("-----------------------------------");
        
        if (planillaList.isEmpty()) {
            System.out.println("-----------------------------------");
            System.out.println("-- No hay plantillas registradas --");
            System.out.println("-----------------------------------");
            System.out.println();
            System.out.println("-------------------------------------");
            System.out.println("Presiona ENTER para volver al menu...");
            System.out.println("-------------------------------------");
            integrantes.nextLine();
            Menu();
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
        
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("--- Presione ENTER para volver  ---");
        System.out.println("-----------------------------------");
        integrantes.nextLine();

        Menu();

        integrantes.close();
    }

    static void Votaciones() {
        limpiarConsola();

        Scanner votacion = new Scanner(System.in);
        
        try{
            System.out.println("-----------------------------------------");
            System.out.println("--------   Sistema de votaciones  -------");
            System.out.println("-----------------------------------------");
            System.out.println();
            System.out.println("-----------------------------------------");
            System.out.println("--- Por favor seleccione su planilla  ---");
            System.out.println("-----------------------------------------");

            for (int i = 0; i < planillaList.size(); i++) {
                ArrayList<String> planillaActual = planillaList.get(i);
                String nombrePlanilla = planillaActual.get(0);
                System.out.println("\nPlanilla " + (i + 1) + " [" + nombrePlanilla + "]");

                for (int j = 1; j < planillaActual.size(); j++) {
                    String[] partes = planillaActual.get(j).split(": ");
                    if (partes.length > 1) {
                        System.out.println("Puesto " + j + " : " + partes[1]);
                    }
                }
            }

            System.out.println();
            System.out.print("Ingrese su opción: ");
            int voto = votacion.nextInt();

            if (voto >= 1 && voto <= planillaList.size()) {
                ArrayList<String> planillaVotada = planillaList.get(voto - 1);
                
                limpiarConsola();

                System.out.println();
                System.out.println("------------------------------");
                System.out.println("La planilla seleccionada es: ");
                System.out.println("------------------------------\n");

                String nombrePlanilla = planillaVotada.get(0);
                System.out.println("Planilla: " + nombrePlanilla);

                System.out.println();
                System.out.println("--------------------------------");
                System.out.println("--- ¿Es correcta su elección? ---");
                System.out.println("--- 1 - Sí              --------");
                System.out.println("--- 2 - No              --------");
                System.out.println("--------------------------------");
                
                try {
                    int confirmacionVoto = votacion.nextInt();

                    switch (confirmacionVoto) {
                        case 1:
                            Conteo_Votos(voto - 1);

                            System.out.println();
                            System.out.println("--------------------------------");
                            System.out.println("--- Regresando al menu...  -----");
                            System.out.println("--------------------------------");
                            @SuppressWarnings("unused")
                            String message = votacion.nextLine();

                            Menu();
                            break;
                        case 2:
                            Votaciones();
                            break;
                        default:
                            System.out.println("--------------------------------");
                            System.out.println("--- Ingrese una opción válida. -");
                            System.out.println("--------------------------------");

                            Votaciones();
                    }
                } catch (Exception e) {
                    limpiarConsola();

                    votacion.nextLine();
                    System.out.println("------------------------------------");
                    System.out.println("Opción inválida. Intente nuevamente.");
                    System.out.println("------------------------------------");
                    @SuppressWarnings("unused")
                    String message = votacion.nextLine();
                    Votaciones();
                }

            } else {
                limpiarConsola();

                votacion.nextLine();
                System.out.println("------------------------------------");
                System.out.println("Opción inválida. Intente nuevamente.");
                System.out.println("------------------------------------");
                @SuppressWarnings("unused")
                String message = votacion.nextLine();
                Votaciones();
            }

            votacion.close();
        } catch (Exception e) {
            votacion.nextLine();
            System.out.println("------------------------------------");
            System.out.println("Opción inválida. Intente nuevamente.");
            System.out.println("------------------------------------");

            System.out.println(e);

            @SuppressWarnings("unused")
            String message = votacion.nextLine();
            Votaciones();
        }
    }

    static void Conteo_Votos(int numeroPlanilla) {
        limpiarConsola();
        
        ArrayList<String> planillaVotada = planillaList.get(numeroPlanilla);

        if (planillaVotada.size() == 0 || !planillaVotada.get(planillaVotada.size() - 1).matches("\\d+")) {
            planillaVotada.add("0");
        }

        int indiceVotos = planillaVotada.size() - 1;
        int votosActuales = Integer.parseInt(planillaVotada.get(indiceVotos));
        votosActuales++;
        planillaVotada.set(indiceVotos, String.valueOf(votosActuales));

        System.out.println();
        System.out.println("--------------------------------------------------------");
        System.out.println("Se registró un voto para la Planilla [" + planillaVotada.get(0) + "]");
        System.out.println("Total de votos: " + votosActuales);
        System.out.println("--------------------------------------------------------");

    }

    static void Ganadores(){
        limpiarConsola();
        int contadorVoto = -1;

        Scanner ganadoresMessage = new Scanner(System.in);
        ArrayList<Integer> ganadores = new ArrayList<>();

        for(int i = 0; i < planillaList.size(); i++){
            ArrayList<String> planilla = planillaList.get(i);
            String conteo = planilla.get(planilla.size() - 1);

            int votos;

            try{
                votos = Integer.parseInt(conteo);
            } catch (NumberFormatException e){
                votos = 0;
            }

            if(votos > contadorVoto){
                contadorVoto = votos;
                ganadores.clear();
                ganadores.add(i);
            } else if(votos == contadorVoto){
                ganadores.add(i);
            }
        }

        System.out.println("Resultados: ");

        if(contadorVoto == 0){

            System.out.println("-------------------------------------");
            System.out.println("- Las planillas no recibieron votos -");
            System.out.println("-------------------------------------");

        }else if(ganadores.size() == 1){

            int ganador = ganadores.get(0);
            String ganadorName = planillaList.get(ganador).get(0);

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("La planilla con mayores votos es: " + ganadorName + " con " + contadorVoto + " votos a su favor");
            System.out.println("------------------------------------------------------------------------------");
            @SuppressWarnings("unused")
            String message = ganadoresMessage.nextLine();

            Admin();
        } else {

            empate = true;
            System.out.println("-----------------------------------------");
            System.out.println("--- Tenemos un empate entre: ------------");

            for(int i : ganadores){
                String empatados = planillaList.get(i).get(0);

                System.out.println("Empadatos: " + empatados + " con " + contadorVoto + " votos");
            }

            System.out.println("-----------------------------------------");
            @SuppressWarnings("unused")
            String message = ganadoresMessage.nextLine();

            Empatados();
        }
        ganadoresMessage.close();
    }

    static void Empatados(){
        Scanner empat = new Scanner(System.in);

        empat.nextLine();
        if(empate == true){
            limpiarConsola();
            System.out.println("-------------------------------------------------------");
            System.out.println("Desea reagendar una segunda votacion para el desempate?");
            System.out.println("-------------------------------------------------------");
            System.out.println("--------------- 1- Si              --------------------");
            System.out.println("--------------- 2- No              --------------------");
            System.out.println("-------------------------------------------------------");
            System.out.print("Opcion: ");
            int desempate = empat.nextInt();

            try {
                switch (desempate) {
                    case 1:
                        limpiarConsola();
                        empat.nextLine();
                        System.out.println("-------------------------------------");
                        System.out.println("- Ingrese la nueva fecha (dd/mm/aa) -");
                        System.out.println("-------------------------------------");
                        System.out.print("Nueva Fecha: ");
                        fechaVotacion = empat.nextLine();

                        System.out.println();
                        System.out.println("Fecha modificada a: " + fechaVotacion);

                        limpiarConsola();
                        System.out.println("-------------------------------------");
                        System.out.println("-   Ingrese el motivo de votacion   -");
                        System.out.println("-------------------------------------");
                        System.out.print("Nuevo motivo: ");
                        motivoVotacion = empat.nextLine();

                        System.out.println();
                        System.out.println("Motivo modificado a: " + motivoVotacion);


                        empat.close();

                        limpiarConsola();
                        System.out.println("---------------------------------");
                        System.out.println("- La informacion fue cambiada   -");
                        System.out.println("---------------------------------");
                        System.out.println();
                        System.out.println("----------------------------------");
                        System.out.println("- Presione ENTER para ir al Menu -");
                        System.out.println("----------------------------------");
                        break;
                
                    case 2:
                        limpiarConsola();
                        System.out.println("---------------------------------");
                        System.out.println("- Entendible, tenga un buen dia -");
                        System.out.println("---------------------------------");
                        System.out.println();
                        System.out.println("----------------------------------");
                        System.out.println("- Presione ENTER para ir al Menu -");
                        System.out.println("----------------------------------");
                        empat.nextLine();
                        break;
                
                    default:
                        limpiarConsola();
                        System.out.println("---------------------------------");
                        System.out.println("-   Ingrese una opcion valida   -");
                        System.out.println("---------------------------------");
                        System.out.println();
                        System.out.println("----------------------------------");
                        System.out.println("- Presione ENTER para ir al Menu -");
                        System.out.println("----------------------------------");
                        empat.nextLine();
                        break;
                }
            } catch (Exception e) {
                empat.nextLine();
                System.out.println();
                System.out.println("****************************************");
                System.out.println("Por favor ingrese el formato solicitado ");
                System.out.println("***** Presiona ENTER para continuar ****");
                System.out.println("****************************************");
                @SuppressWarnings("unused")
                String message = empat.nextLine();
            }

            Menu();
            empat.close();
        }
    }

    static void modificarInformacion(){
        limpiarConsola();
        Scanner modificacion = new Scanner(System.in);

        System.out.println("-------------------------------------");
        System.out.println("- Que informacion quiere modificar? -");
        System.out.println("--- 1- Fecha                    -----");
        System.out.println("--- 2- Lugar                    -----");
        System.out.println("--- 3- Tiempo de gestion        -----");
        System.out.println("--- 4- Motivo de la votacion    -----");
        System.out.println("--- 5- Limitador ID             -----");
        System.out.println("--- 6- No cambiar nada          -----");
        System.out.println("-------------------------------------");

        try{
            System.out.print("Opcion: ");
            int modificarOpcion = modificacion.nextInt();

            switch (modificarOpcion) {
                case 1:
                    limpiarConsola();
                    modificacion.nextLine();
                    System.out.println();
                    System.out.println("-------------------------------------");
                    System.out.println("- Ingrese la nueva fecha (dd/mm/aa) -");
                    System.out.println("-------------------------------------");
                    System.out.print("Nueva Fecha: ");
                    fechaVotacion = modificacion.nextLine();

                    System.out.println();
                    System.out.println("Fecha modificada a: " + fechaVotacion);
                    break;
                
                case 2:
                    limpiarConsola();
                    modificacion.nextLine();
                    System.out.println();
                    System.out.println("-------------------------------------");
                    System.out.println("-      Ingrese el nuevo lugar       -");
                    System.out.println("-------------------------------------");
                    System.out.print("Nuevo Lugar: ");
                    lugar = modificacion.nextLine();

                    System.out.println();
                    System.out.println("Lugar modificado a: " + lugar);
                    break;

                case 3:
                    limpiarConsola();
                    modificacion.nextLine();
                    System.out.println();
                    System.out.println("-----------------------------------------------");
                    System.out.println("- Ingrese el nuevo periodo de gestion (meses) -");
                    System.out.println("-----------------------------------------------");
                    System.out.print("Nuevo periodo: ");
                    periodoGestion = modificacion.nextInt();

                    System.out.println();
                    System.out.println("Periodo modificado a: " + periodoGestion + " meses");
                    break;

                case 4:
                    limpiarConsola();
                    modificacion.nextLine();
                    System.out.println();
                    System.out.println("-------------------------------------");
                    System.out.println("-   Ingrese el motivo de votacion   -");
                    System.out.println("-------------------------------------");
                    System.out.print("Nuevo motivo: ");
                    motivoVotacion = modificacion.nextLine();

                    System.out.println();
                    System.out.println("Motivo modificado a: " + motivoVotacion);
                    break;

                case 5:
                    limpiarConsola();
                    modificacion.nextLine();
                    System.out.println();
                    System.out.println("-------------------------------------");
                    System.out.println("-   Ingrese el limitador de ID      -");
                    System.out.println("-------------------------------------");
                    System.out.print("Nuevo limitador: ");
                    limintatID = modificacion.nextInt();

                    System.out.println();
                    System.out.println("Limitador modificado a: " + limintatID + " numeros");
                    break;

                case 6:
                    Admin();
                    break;
            
                default:
                    modificacion.nextLine();
                    System.out.println();
                    System.out.println("***************************************");
                    System.out.println("* Por favor ingrese una opcion valida *");
                    System.out.println("***************************************");
                    @SuppressWarnings("unused")
                    String message = modificacion.nextLine();

                    modificarInformacion();
                    break;
            }
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("- Desea modificar algo mas? -");
            System.out.println("- 1- Si                   ---");
            System.out.println("- 2- No                   ---");
            System.out.println("-----------------------------");
            System.out.print("Opcion: ");
            int algoMas = modificacion.nextInt();

            switch (algoMas) {
                case 1:
                    modificarInformacion();
                    break;
                
                case 2:
                    Admin();
                    break;
            
                default:
                    System.out.println();
                    System.out.println("***************************************");
                    System.out.println("* Por favor ingrese una opcion valida *");
                    System.out.println("***************************************");
                    @SuppressWarnings("unused")
                    String message = modificacion.nextLine();
                    break;
            }
        } catch (Exception e) {
                modificacion.nextLine();
                System.out.println();
                System.out.println("******************************");
                System.out.println("Por favor ingrese solo numeros");
                System.out.println("Presiona ENTER para continuar");
                System.out.println("******************************");
                @SuppressWarnings("unused")
                String message = modificacion.nextLine();

                modificarInformacion();
        }

        modificacion.close();
    }

    static void InformacionVotacion(){
        limpiarConsola();
        Scanner informacion = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("- Informacion de las votaciones -");
        System.out.println("---------------------------------");

        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("Motivo de las votaciones: " + motivoVotacion);
        System.out.println();
        System.out.println("Fecha: " + fechaVotacion);
        System.out.println();
        System.out.println("Lugar donde se realizaran las votaciones: " + lugar);
        System.out.println();
        System.out.println("La duracion de Gestion de la planilla ganadora es de " + periodoGestion + " meses");
        System.out.println();
        System.out.println("--------------------------------------------------------");
        System.out.println();

        System.out.println("*********************************");
        System.out.println("Presiona ENTER para continuar");
        System.out.println("******************************");
        @SuppressWarnings("unused")
        String message = informacion.nextLine();

        Menu();

        informacion.close();
    }

    
    public static void main(String[] args){
        Menu();
    }

    static void cerrarProgram(){
        limpiarConsola();

        String passwd = password;

        try (Scanner password = new Scanner(System.in)) {
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("-- Por favor ingrese el password de administrador para cerrar el programa: ");
            System.out.println("---------------------------------------------------------------------------");

            System.out.print("Password: ");
            String pwdUser = password.next();
            
            if(pwdUser.trim().isEmpty()){
                password.nextLine();
                System.out.println("************************************");
                System.out.println("* Por favor ingrese una contrasena *");
                System.out.println("************************************");
                @SuppressWarnings("unused")
                String message = password.nextLine();

                Admin_Loggin();
            }else if (pwdUser.equals(passwd)) {

                limpiarConsola();
                System.out.println();
                System.out.println("----------------------------------");
                System.out.println("Programa cerrado de manera exitosa");
                System.out.println("----------------------------------");
                System.out.println();

                System.exit(0);

            } else {
                password.nextLine();
                System.out.println();
                System.out.println("**********************************************");
                System.out.println("* Contrasena incorrecta, intentelo de nuevo. *");
                System.out.println("****    Presione ENTER para continuar    *****");
                System.out.println("**********************************************");
                @SuppressWarnings("unused")
                String message = password.nextLine();
                
                Menu();
            }
        }
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