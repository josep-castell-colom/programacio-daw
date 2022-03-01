package Biblioteca_v2;
import java.util.Scanner;

public class Admin extends Persona {
  public static Scanner input = new Scanner(System.in);

  public Admin(){}
  
  public Admin(String nombre, String apellido1, String apellido2, String nif, String contraseña){
    super(nombre, apellido1, apellido2, nif, contraseña);
  }

  public static void añadirAdmin(Biblioteca biblioteca){
    Tools.mensaje("titulo", "añadir administrador", "");
    System.out.println("Nombre");
    String nombre = Tools.prompt();
    System.out.println("Apellido1");
    String apellido1 = Tools.prompt();
    System.out.println("Apellido2");
    String apellido2 = Tools.prompt();
    System.out.println("N.I.F.");
    String nif = Tools.prompt();
    String contraseña;
    boolean ok = false;
    do{
      System.out.println("Contraseña");
      contraseña = Tools.prompt();
      if(contraseña.length() >= 8){
        Admin admin = new Admin(nombre, apellido1, apellido2, nif, contraseña);
        biblioteca.getListaAdmins().add(admin);
        Tools.mensaje("pos", "administrador añadido", "continuar");
        ok = true;
      } else {
        Tools.mensaje("alert", "la contraseña debe tener 8 caracteres como mínimo");
        boolean ok2 = false;
        do{
          System.out.println("1. Reintroducir contraseña\t2. Salir");
          int opt = Integer.parseInt(input.nextLine());
          if(opt == 1){
            ok2 = true;
          } else if (opt == 2){
            ok2 = true;
            ok = true;
          } else {
            Tools.mensaje("alert", "introduce 1 o 2", "continuar");
          }
        }while(!ok2);
      }
    }while(!ok);
  }

  public static void eliminarAdmin(Biblioteca biblioteca){
    Tools.mensaje("titulo", "eliminar administrador", "");
    System.out.println("Introduce el N.I.F");
    String nif = Tools.prompt();
    boolean found = false;
    for(int j = 0; j < biblioteca.getListaAdmins().size(); j ++){
      if(nif.equals(biblioteca.getListaAdmins().get(j).getNif()) && !nif.equals("josep")){
        found = true;
        Tools.mensaje("alert", "está a punto de eliminar el siguiente administrador\n" + biblioteca.getListaAdmins().get(j), "");
        if(Tools.confirmar()){
          biblioteca.getListaAdmins().remove(biblioteca.getListaAdmins().get(j));
          Tools.mensaje("pos", "administrador eliminado", "continuar");
        }
        j = biblioteca.getListaAdmins().size();
      } else if (nif.equals("josep")){
        Tools.mensaje("neg", "no puede eliminar a Josep", "continuar");
        j = biblioteca.getListaAdmins().size();
        found = true;
      }
    }
    if (!found){
      Tools.mensaje("neg", "no encontramos a ningún administrador con NIF " + nif, "continuar");
    }
  }

  
}

