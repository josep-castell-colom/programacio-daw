package Biblioteca_v2;
import java.util.Scanner;

public abstract class Tools {
  public static Scanner input = new Scanner(System.in);
  private static Pair pair = new Pair();

  public static void portada(){
    System.out.println();
    System.out.println("\t             ______________ _____________");
    System.out.println("\t            /              V             /");
    System.out.println("\t           /   En algún   / tiempo que  //");
    System.out.println("\t          / lugar de La  / vivía un    //");
    System.out.println("\t         /  Mancha, de  / hidalgo de  //");
    System.out.println("\t        /  cuyo nombre /los de lanza //");
    System.out.println("\t       /  no quiero   /en astillero,//");
    System.out.println("\t      /  acordarme,  /   adarga    //");
    System.out.println("\t     / no ha mucho  /   antigua   //");
    System.out.println("\t    /              /     ...     //");
    System.out.println("\t   /_____________ /____________ //");
    System.out.println("\t   |_____________V_____________|/");
    System.out.println();
  }

  public static void br(){
    System.out.println("---------------------------------------------------------------");
  }

  public static void continuar(){
    System.out.println("\nContinuar <ENTER>");
    input.nextLine();
  }

  public static boolean confirmar(){
    boolean ok = false;
    boolean out = false;
    String opt;
    do{
      System.out.println("¿Desea continuar? (si/no)");
      opt = (input.nextLine());
      if(opt.equalsIgnoreCase("si")){
        ok = true;
        out = true;
      } else if (opt.equalsIgnoreCase("no")){
        ok = true;
        out = false;
      }
    } while(!ok);
    return out;
  }

  public static void enConstruccion(){
    br();
    System.out.println("[-] Lo sentimos, esta sección esta en construcción.");
    continuar();
  }

  public static boolean comprobarNum(String string){
    boolean okNum = true;
    char[] numeros = {'1','2','3','4','5','6','7','8','9','0'};
    for (int i = 0; i < string.length(); i ++){
      boolean[] validaNum = new boolean[10];
      for (int j = 0; j < numeros.length; j ++){
        if(string.charAt(i) == numeros[j]){
          validaNum[j] = true;
        } else {
          validaNum[j] = false;
        }
      }
      boolean tf = false;
      for (int j = 0; j < validaNum.length; j ++){
        if (validaNum[j] == true){
          tf = true;
        }
      }
      if(!tf){
        okNum = false;
        i = string.length();
      }
    }
    return okNum;
  }

  public static void noEncontrar(int posicion){
    if(posicion == -1){
      br();
      System.out.println("\n[-] Lo sentimos, el libro no está entre nuestros títulos\n");
      br();
      continuar();
    }
  }

  public static void mainMenu(Biblioteca biblioteca){
    br();
    System.out.println();
    portada();
    System.out.println();
    br();
    System.out.println("BIENVENID@ A LA BIBLIOTECA '" + biblioteca.getNombre() + "'");
    br();
    do {
      System.out.println("1 - Menú de usuario");
      System.out.println("2 - Añadir usuario");
      System.out.println("3 - Manú de administrador");
      System.out.println("0 - Salir");
      System.out.print("> ");
      String opt = input.nextLine();
      switch(opt){
        case "1": pair = Persona.validar(biblioteca);
                  if(pair.getValid()){
                    Main.setCurrentUser(pair.getUser());
                    userMenu(biblioteca);
                  }
                  break;
        case "2": User.añadirUsuario(biblioteca);
                  break;
        case "3": pair = Persona.validar(biblioteca);
                  if(pair.getValid()){
                    Main.setCurrentAdmin(pair.getAdmin());
                    adminMenu(biblioteca);
                  }
                  break;
        case "0": Main.setOn(false);
                  br();
                  System.out.println("Hasta pronto! ;)");
                  break;
        default:  Tools.br();
                  System.out.println("[!] Por favor, introduce una de las opciones disponibles");
                  Tools.continuar();
      }
    } while (Main.getOn());
  }

  public static void userMenu(Biblioteca biblioteca){
    do {
    br();
    System.out.println("\t\tMENÚ DE USUARIO");
    br();
      System.out.println("1 - Reservar libro");
      System.out.println("2 - Devolver libro");
      System.out.println("3 - Buscar libro por ISBN");
      System.out.println("4 - Buscar libro por título");
      System.out.println("5 - Ver todos los libros");
      System.out.println("6 - Ver sólo los libros disponibles");
      System.out.println("7 - Ver sus libros reservados");
      System.out.println("9 - Volver al menú principal");
      System.out.println("0 - Salir");
      System.out.print("> ");
      String opt = input.nextLine();
      switch (opt){
        case "1": Main.getCurrentUser().reservarLibro(biblioteca);
                  break;
        case "2": Main.getCurrentUser().devolverLibro(biblioteca.getListaLibros());
                  break;
        case "3": noEncontrar(Libro.buscarIsbn(biblioteca.getListaLibros()));
                  break;
        case "4": Libro.buscarTitulo(biblioteca.getListaLibros());
                  break;
        case "5": biblioteca.mostrarLibros();
                  break;
        case "6": biblioteca.mostrarDisponibles();
                  break;
        case "7": Main.getCurrentUser().mostrarLibrosReservados();
                  break;
        case "9": mainMenu(biblioteca);
                  break;
        case "0": Main.setOn(false);
                  br();
                  System.out.println("Hasta pronto! ;)");
                  break;
        default:  Tools.br();
                  System.out.println("\n[!] Por favor, introduce una de las opciones disponibles");
                  Tools.continuar();
      }
    } while (Main.getOn());
  }

  public static void adminMenu(Biblioteca biblioteca){
    do {
    br();
    System.out.println("\t\tMENU DE ADMINISTRADOR");
    br();
      System.out.println("\t\tGESTIONAR LIBROS:");
      System.out.println("1 - Añadir libro");
      System.out.println("2 - Eliminar libro");
      System.out.println("3 - Buscar libro por ISBN");
      System.out.println("4 - Buscar libro por título");
      System.out.println("5 - Ver todos los libros");
      System.out.println("6 - Ver sólo los libros disponibles");
      System.out.println("7 - Ver sólo los libros sin reservas\t[UNDER CONSTRUCTION]");
      System.out.println();
      System.out.println("\tGESTIONAR USUARIOS Y ADMINISTRADORES");
      System.out.println("11 - Añadir usuario");
      System.out.println("12 - Eliminar usuario");
      System.out.println("13 - Añadir administrador");
      System.out.println("14 - Eliminar administrador");
      System.out.println("15 - Ver todos los usuarios");
      System.out.println("16 - Ver todos los administradores");
      System.out.println();
      System.out.println("9 - Volver al menú principal");
      System.out.println("0 - Salir");
      System.out.print("> ");
      String opt = input.nextLine();
      switch (opt){
        case "1": Libro.añadirLibro(biblioteca.getListaLibros());
                  break;
        case "2": Libro.eliminarLibro(biblioteca.getListaLibros());
                  break;
        case "3": Libro.buscarIsbn(biblioteca.getListaLibros());
                  break;
        case "4": Libro.buscarTitulo(biblioteca.getListaLibros());
                  break;
        case "5": biblioteca.mostrarLibros();
                  break;
        case "6": biblioteca.mostrarDisponibles();
                  break;
        case "7": enConstruccion();
                  break;
        case "11":User.añadirUsuario(biblioteca);
                  break;
        case "12":User.eliminarUser(biblioteca);
                  break;
        case "13":Admin.añadirAdmin(biblioteca);
                  break;
        case "14":Admin.eliminarAdmin(biblioteca);
                  break;
        case "15":biblioteca.mostrarUsuarios();
                  break;
        case "16":biblioteca.mostrarAdmin();
                  break;
        case "9": mainMenu(biblioteca);
                  break;
        case "0": Main.setOn(false);
                  br();
                  System.out.println("Hasta pronto! ;)");
                  break;
        default:  Tools.br();
                  System.out.println("[!] Por favor, introduce una de las opciones disponibles");
                  Tools.continuar();
      }
    } while (Main.getOn());
  }
}
