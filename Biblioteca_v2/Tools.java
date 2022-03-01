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

  public static String prompt(){
    System.out.print("> ");
    String out = input.nextLine();
    return out;
  }
  
  public static String firstUpperCase(String mensaje){
    String firstUpperCase = "";
    for(int i = 0; i < mensaje.length(); i ++){
      if(i == 0 || (i > 1 && mensaje.charAt(i - 2) == '.')){
        firstUpperCase += Character.toUpperCase(mensaje.charAt(i));
      } else {
        firstUpperCase += mensaje.charAt(i);
      }
    }
    return firstUpperCase;
  }

  public static String nameFirstUpperCase(String name){
    String firstUpperCase = "";
    for(int i = 0; i < name.length(); i ++){
      if(i == 0 || (i > 1 && name.charAt(i - 1) == ' ')){
        firstUpperCase += Character.toUpperCase(name.charAt(i));
      } else {
        firstUpperCase += name.charAt(i);
      }
    }
    return firstUpperCase;
  }

  public static void mensaje(String mensaje){
    br();
    System.out.println("\n" + firstUpperCase(mensaje) + "\n");
  }

  public static void mensaje(String mensaje, String continuar){
    br();
    System.out.println("\n" + firstUpperCase(mensaje) + ".\n");
    if(continuar.equals("continuar")){
      continuar();
    }
  }

  public static void mensaje(String valor, String mensaje, String continuar){
    br();
    switch(valor){
      case "pos": System.out.println("\n[+] " + firstUpperCase(mensaje) + "\n");
      break;
      case "neg": System.out.println("\n[-] " + firstUpperCase(mensaje) + "\n");
      break;
      case "alert": System.out.println("\n[!] " + firstUpperCase(mensaje) + "\n");
      break;
      case "titulo":  if(mensaje.length() < 20){
                        System.out.println("\t\t\t" + mensaje.toUpperCase());
                      } else if(mensaje.length() < 40) {
                        System.out.println("\t\t" + mensaje.toUpperCase());
                      } else {
                        System.out.println("\t" + mensaje.toUpperCase());
                      }
      break;
      case default: System.out.println("\n" + firstUpperCase(mensaje) + "\n");
    }
    br();
    if(continuar.equals("continuar")){
      continuar();
    }
  }

  public static boolean confirmar(){
    boolean ok = false;
    boolean out = false;
    String opt;
    do{
      System.out.println("¿Desea continuar? (si/no)");
      opt = prompt();
      if(opt.equalsIgnoreCase("si")){
        ok = true;
        out = true;
      } else if (opt.equalsIgnoreCase("no")){
        mensaje("neg", "operación cancelada por el usuario", "");
        ok = true;
        out = false;
      }
    } while(!ok);
    return out;
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
      mensaje("neg", "lo sentimos, el libro no esta entre nuestros títulos", "continuar");
    }
  }

  public static void maxReservas(){
    boolean continua = false;
    do {
      System.out.println("1 - Ver sus libros reservados");
      System.out.println("2 - Devolver libro");
      System.out.println("3 - Volver al menú");
      String opt = Tools.prompt();
      switch(opt){
        case "1": Main.getCurrentUser().mostrarLibrosReservados();
                  break;
        case "2": Main.getCurrentUser().devolverLibro(Main.getCurrentBiblioteca().getListaLibros());
                  break;
        case "3": continua = true;
        break;
        case default: Tools.mensaje("alert", "introduce una de las opciones disponibles", "continuar");
      }
    } while(!continua);
  }

  public static void mainMenu(Biblioteca biblioteca){
    br();
    System.out.println();
    portada();
    System.out.println();
    mensaje("titulo", biblioteca.getNombre(), "");
    do {
      mensaje("titulo", "bienvenido", "");
      System.out.println("1 - Menú de usuario");
      System.out.println("2 - Añadir usuario");
      System.out.println("3 - Manú de administrador");
      System.out.println("0 - Salir");
      String opt = prompt();
      switch(opt){
        case "1": pair = Persona.validar(biblioteca, "user");
                  if(pair.getValid()){
                    Main.setCurrentUser(pair.getUser());
                    userMenu(biblioteca);
                  }
                  break;
        case "2": User.añadirUsuario(biblioteca);
                  break;
        case "3": pair = Persona.validar(biblioteca, "admin");
                  if(pair.getValid()){
                    Main.setCurrentAdmin(pair.getAdmin());
                    adminMenu(biblioteca);
                  }
                  break;
        case "0": Main.setOn(false);
                  br();
                  System.out.println("Hasta pronto! ;)");
                  break;
        default:  mensaje("alert", "por favor, introduce una de las opciones disponibles", "");
      }
    } while (Main.getOn());
  }

  public static void userMenu(Biblioteca biblioteca){
    do {
    mensaje("titulo", "menú de usuario", "");
      System.out.println("1 - Reservar libro");
      System.out.println("2 - Devolver libro");
      System.out.println("3 - Buscar libro por ISBN");
      System.out.println("4 - Buscar libro por título");
      System.out.println("5 - Ver todos los libros");
      System.out.println("6 - Ver sólo los libros disponibles");
      System.out.println("7 - Ver sus libros reservados");
      System.out.println("9 - Volver al menú principal");
      System.out.println("0 - Salir");
      String opt = prompt();
      switch (opt){
        case "1": Main.getCurrentUser().reservarLibro(biblioteca);
                  break;
        case "2": Main.getCurrentUser().devolverLibro(biblioteca.getListaLibros());
                  break;
        case "3": Libro.buscarIsbn(biblioteca.getListaLibros());
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
        default:  mensaje("alert", "por favor, introduce una de las opciones disponibles", "continuar");
      }
    } while (Main.getOn());
  }

  public static void adminMenu(Biblioteca biblioteca){
    do {
      mensaje("titulo", "menú de administrador", "");
      System.out.println("\n\t\tGESTIONAR LIBROS\n");
      System.out.println("1 - Añadir libro");
      System.out.println("2 - Eliminar libro");
      System.out.println("3 - Buscar libro por ISBN");
      System.out.println("4 - Buscar libro por título");
      System.out.println("5 - Ver todos los libros");
      System.out.println("6 - Ver sólo los libros disponibles");
      System.out.println("7 - Ver sólo los libros sin reservas\t[UNDER CONSTRUCTION]");
      System.out.println("8 - Ver todos los libros reservados");
      System.out.println();
      System.out.println("\n\tGESTIONAR USUARIOS Y ADMINISTRADORES\n");
      System.out.println("11 - Añadir usuario");
      System.out.println("12 - Eliminar usuario");
      System.out.println("13 - Añadir administrador");
      System.out.println("14 - Eliminar administrador");
      System.out.println("15 - Ver todos los usuarios");
      System.out.println("16 - Ver todos los administradores");
      System.out.println();
      System.out.println("9 - Volver al menú principal");
      System.out.println("0 - Salir");
      String opt = prompt();
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
        case "7": biblioteca.mostrarSinReservas();
                  break;
        case "8": biblioteca.mostrarLibrosReservados();
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
        default:  mensaje("alert", "por favor, introduce una de las opciones disponibles", "continuar");
      }
    } while (Main.getOn());
  }
}
