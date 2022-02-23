package Biblioteca_v2;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends Persona {
  public static Scanner input = new Scanner(System.in);
  private ArrayList<Libro> librosReservados= new ArrayList<Libro>() ;

  public User(){}

  public User(String nombre, String apellido1, String apellido2, String nif, String contraseña){
    super(nombre, apellido1, apellido2, nif, contraseña);
  }

  public ArrayList<Libro> getLibrosReservados(){
    return librosReservados;
  }

  public void setLibrosReservados(ArrayList<Libro> lista){
    librosReservados = lista;
  }

  public static void añadirUsuario(Biblioteca biblioteca){
    Tools.br();
    System.out.println("\t\tAÑADIR USUARIO");
    Tools.br();
    System.out.println("Nombre > ");
    String nombre = input.nextLine();
    System.out.println("Apellido1 > ");
    String apellido1 = input.nextLine();
    System.out.println("Apellido2 > ");
    String apellido2 = input.nextLine();
    System.out.println("N.I.F. > ");
    String nif = input.nextLine();
    String contraseña;
    boolean ok = false;
    do{
      System.out.println("Contraseña > ");
      contraseña = input.nextLine();
      if(contraseña.length() >= 8){
        User user = new User(nombre, apellido1, apellido2, nif, contraseña);
        biblioteca.getListaUsuarios().add(user);
        Tools.br();
        System.out.println("\n[+] Usuario añadido\n");
        System.out.println(user + "\n");
        Tools.br();
        ok = true;
        Tools.continuar();
      } else {
        System.out.println("[-] La contraseña debe tener 8 caracteres como mínimo");
        boolean ok2 = false;
        do{
          System.out.println("1. Reintroducir contraseña\t2. Salir");
          String opt = input.nextLine();
          if(opt.equals("1")){
            ok2 = true;
          } else if (opt.equals("2")){
            ok2 = true;
            ok = true;
          } else {
            System.out.println("[!] Introduce 1 o 2");
          }
        }while(!ok2);
      }
    }while(!ok);
  }

  public static void eliminarUser(Biblioteca biblioteca){
    Tools.br();
    System.out.println("\t\tELIMINAR USUARIO");
    Tools.br();
    System.out.println("Introduce el N.I.F > ");
    String nif = input.nextLine();
    boolean found = false;
    for(int i = 0; i < biblioteca.getListaUsuarios().size(); i ++){
      if(nif.equals(biblioteca.getListaUsuarios().get(i).getNif())){
        found = true;
        if(biblioteca.getListaUsuarios().get(i).getLibrosReservados().size() == 0){
          Tools.br();
          System.out.println("\n[!] Está a punto de eliminar el siguiente usuario:\n");
          System.out.println(biblioteca.getListaUsuarios().get(i));
          Tools.br();
          if(Tools.confirmar()){
            biblioteca.getListaUsuarios().remove(biblioteca.getListaUsuarios().get(i));
            Tools.br();
            System.out.println("\n[+] Usuario eliminado\n");
            Tools.continuar();
          } else {
            Tools.br();
            System.out.println("\n[-] Operación interrumpida\n");
            Tools.continuar();
          }
          i = biblioteca.getListaUsuarios().size();
        } else {
          Tools.br();
          System.out.println("\n[-] No se puede eliminar el usuario porque tiene libros reservados\n");
          Tools.br();
          Tools.continuar();
        }
      } 
    }
    if (!found){
      Tools.br();
      System.out.println("\n[-] No encontramos a nadie con N.I.F " + nif + "\n");
      Tools.br();
      Tools.continuar();
    }
  }

  public void reservarLibro(Biblioteca biblioteca){
    Tools.br();
    System.out.println("\t\tRESERVAR LIBRO");
    Tools.br();
    int posicion = Libro.buscarIsbn(biblioteca.getListaLibros());
    if(posicion > -1){
      boolean repetido = false;
      for(int i = 0; i < this.getLibrosReservados().size(); i ++){
        if(this.getLibrosReservados().get(i).getIsbn() == biblioteca.getListaLibros().get(posicion).getIsbn()){
          repetido = true;
        }
      }
      if(biblioteca.getListaLibros().get(posicion).getNumCopiasDisponibles() > 0 && !repetido){
        Tools.br();
        System.out.println("Está a punto de reservar el libro " + biblioteca.getListaLibros().get(posicion).getTitulo());
        if(Tools.confirmar()){
          this.getLibrosReservados().add(biblioteca.getListaLibros().get(posicion));
          biblioteca.getListaLibros().get(posicion).setNumCopiasDisponibles(biblioteca.getListaLibros().get(posicion).getNumCopiasDisponibles() - 1);
          Tools.br();
          System.out.println("[+] Libro reservado");
          Tools.br();
        }
      } else if(biblioteca.getListaLibros().get(posicion).getNumCopiasDisponibles() == 0 && !repetido){
        Tools.br();
        System.out.println("[-] Lo sentimos, no disponemos de copias disponibles para el libro " + biblioteca.getListaLibros().get(posicion).getTitulo());
      } else if(repetido){
        Tools.br();
        System.out.println("\n[-] Usted ya dispone de una copia de este libro\n");
        Tools.br();
      }
      Tools.continuar();
    }
  }

  public void devolverLibro(ArrayList<Libro> lista){
    Tools.br();
    System.out.println("\t\tDEVOLVER LIBRO");
    Tools.br();
    int posicion = Libro.buscarIsbn(this.getLibrosReservados());
    if(posicion > -1){
      Tools.br();
      System.out.println("Está a punto de devolver el libro " + this.getLibrosReservados().get(posicion).getTitulo());
      if(Tools.confirmar()){
        for(int i = 0; i < lista.size(); i ++){
          if(this.getLibrosReservados().get(posicion).getIsbn().equals(lista.get(i).getIsbn())){
            lista.get(i).setNumCopiasDisponibles(lista.get(i).getNumCopiasDisponibles() + 1);
            i = lista.size();
          }
        }
        this.getLibrosReservados().remove(this.getLibrosReservados().get(posicion));
        Tools.br();
        System.out.println("[+] Libro devuelto");
        Tools.br();
      }
    } else {
      Tools.br();
      System.out.println("\n[-] El libro no está entre sus reservas\n");
      Tools.br();
    }
    Tools.continuar();
  }

  public void mostrarLibrosReservados(){
    Tools.br();
    System.out.println("\tMOSTRANDO LIBROS RESERVADOS POR '" + this.getNombre() + "'");
    Tools.br();
    if(this.getLibrosReservados().size() > 0){
      for(int i = 0; i < this.getLibrosReservados().size(); i ++){
        System.out.println(this.getLibrosReservados().get(i));
        Tools.br();
      }
    } else {
      System.out.println("[-] No tiene ningún libro reservado");
    }
    Tools.continuar();
  }
}
