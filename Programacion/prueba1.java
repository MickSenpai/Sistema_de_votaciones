package Programacion;

import java.util.Scanner;
import java.util.ArrayList;

public class prueba1 {

    static void Menu(){
        Scanner menu = new Scanner(System.in);
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

            if(menuOpcion == 1){
                Admin_Loggin();
            } else if(menuOpcion == 2){
                Votante_Loggin();
            } else if(menuOpcion == 3){
                System.exit(0);
            } else {
                System.out.println("Ingrese una opcion valida");
            }
        } catch (Exception e) {
            menu.nextLine();
            System.out.println("Por favor ingrese una opcion en numeros.");
            System.out.println("Presione ENTER para continuar");
            @SuppressWarnings("unused")
            String message = menu.nextLine();

            Menu();
        }

        menu.close();
    }

    static void Admin_Loggin(){

        limpiarConsola();

        String passwd = "BoKuLe1967"; // Contrasena modificable 

        Scanner password = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");
        System.out.print  ("-- Por favor ingrese el password de administrador: ");
        System.out.println("-----------------------------------------------------");

        String pwdUser = password.next();

        if (pwdUser.equals(passwd)) {
            Admin();
        } else {
            Votante_Loggin();
        }

        password.close();
    }

    static void Votante_Loggin(){
        limpiarConsola();
        Scanner matricula = new Scanner(System.in);

        System.out.println("-----------------------------------");
        System.out.println("---- Inicio de sesion votante -----");
        System.out.println("-----------------------------------");
        System.out.println();

        //Manejo de error en caso de que agregue algo que no sean numeros.
        try {
            System.out.print("Ingresa tu ID: "); //ID = Matricula
            int id = matricula.nextInt();

            Listado_IDs(id); //Se manda a validacion en la funcion Listado_IDs
        } catch (Exception e) {
            matricula.nextLine();

            System.out.println();
            System.out.println("Por favor ingrese solo numeros");
            System.out.println("Presiona ENTER para continuar");
            @SuppressWarnings("unused")
            String message = matricula.nextLine();

            Votante_Loggin();
        }

        matricula.close();
    }

    static ArrayList <Integer> ListaID_Votantes = new ArrayList<>(); //Creamos el listado de IDs

    static void Listado_IDs(int id){ //Se hace la validacion de ID existente

        boolean id_Existente = ListaID_Votantes.contains(id);

        if(id_Existente == true){
            UsuarioRepetido();
        } else {
            ListaID_Votantes.add(id);
            Votante_Menu();
        }
    }

    static void UsuarioRepetido(){ //Muestra mensaje de repeticion
        Scanner repetido = new Scanner(System.in);
        System.out.println();
        System.out.println("El usuario ya esta registado");
        System.out.println("Presione ENTER para continuar");
        @SuppressWarnings("unused")
        String repeticion = repetido.nextLine();

        Votante_Loggin();
        repetido.close();
    }

    static void Votante_Menu(){
        limpiarConsola();
        Scanner opcion_Votante = new Scanner(System.in);

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

        if(opcion == 1){
            System.out.println("Aqui van las votaciones");
        } else if( opcion == 2){
            System.out.println("Aqui va lo de ver plantillas");
        } else if( opcion == 3){
            Votante_Loggin();
        } else {
            System.out.println("Ingresa una opcion valida");
        }

        opcion_Votante.close();
    }

    static void Admin(){
        limpiarConsola();
        Scanner adminOpcion = new Scanner(System.in);

        System.out.println("-----------------------------------");
        System.out.println("---- Bienvenido Administrador  ----");
        System.out.println("-----------------------------------");
        System.out.println();

        System.out.println("-----------------------------------");
        System.out.println("------ Selecciona tu opcion: ------");
        System.out.println("------ 1- Agregar planillas  ------");
        System.out.println("------ 2- Eliminar planillas ------");
        System.out.println("------ 3- Integrantes planillas ---");
        System.out.println("------ 4- Finalizar votacion ------");
        System.out.println("-----------------------------------");
        System.out.print("Opcion: ");
        int opcion = adminOpcion.nextInt();

        if(opcion == 1){
            Agregar_Planillas();
        } else if( opcion == 2){
            System.out.println("Aqui va lo de ver plantillas");
        } else if( opcion == 3){
            Votante_Loggin();
        } else {
            System.out.println("Ingresa una opcion valida");
        }

        adminOpcion.close();
    }

    static void Agregar_Planillas(){
        ArrayList<ArrayList <String>>  planillaList = new ArrayList<>(); //Listado tiulos planillas

        limpiarConsola();
        Scanner planillas = new Scanner(System.in);
        System.out.println("-----------------------------------");
        System.out.println("------- Agregar Planillas   -------");
        System.out.println("-----------------------------------");
        System.out.println();

        System.out.println("Cuantas planillas desea agregar?");
        int cantidadPlanillas = planillas.nextInt();

        for(int p = 1; p <= cantidadPlanillas; p++){
            ArrayList <String> Titulos = new ArrayList<>();

            planillas.nextLine();

            System.out.println("Planilla " + p);

            System.out.print("Ingrese el nombre de la planilla: ");
            String nombrePlanilla = planillas.nextLine();

            System.out.println("Cuantos integrantes habra en la planilla?");
            int numIntegrantes = planillas.nextInt();

            planillas.nextLine();

            for(int i = 1; i <= numIntegrantes; i++){
                System.out.print("Ingrese el cargo del integrante " + i + ": ");
                String cargo = planillas.nextLine();

                Titulos.add(cargo);
            }

            System.out.println();

            for(int i = 0; i < Titulos.size(); i++){
                System.out.println("Ingrese el nombre del " + Titulos.get(i));
                String nombreIntegrante = planillas.nextLine();

                String cargoActual = Titulos.get(i);

                Titulos.set(i, cargoActual + ": " + nombreIntegrante);
            }

            planillaList.add(Titulos);
        }

        System.out.println();

        System.out.println("Planillas ingresadas: ");
        for (int i = 0; i < planillaList.size(); i++) {
            System.out.println("Planilla " + (i + 1) + ": " + planillaList.get(i));
        }

        System.out.println();

        System.out.println("Los datos son correctos?");
        System.out.println("1- Si");
        System.out.println("2- No");
        System.out.print("Opcion: ");
        int datosIngresados = planillas.nextInt();

        System.out.println();

        if(datosIngresados == 1){
            System.out.println("Datos guardados correctamente");
        } else if(datosIngresados == 2){
            System.out.println("Datos eliminados");
        } else {
            System.out.println("Por favor, ingrese una opcion valida");
        }

        System.out.println();

        System.out.println("Le gustaria agregar otra planilla?");
        System.out.println("1- Si");
        System.out.println("2- No");
        System.out.print("Opcion: ");
        int agregarPlanilla = planillas.nextInt();

        if(agregarPlanilla == 1){
            Agregar_Planillas();
        } else if(agregarPlanilla == 2){
            Admin();
        } else {
            System.out.println("Por favor, ingrese una opcion valida");
        }
    }

    static void Eliminar_Planillas(){

    }

    static void Integrantes_Planillas(){

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
        } catch (Exception e) {
            System.out.println("Error al limpiar la consola: " + e.getMessage());
        }
        System.out.println("Hola tonoto");
        System.out.println("Hola tonoto2");
    }
}
