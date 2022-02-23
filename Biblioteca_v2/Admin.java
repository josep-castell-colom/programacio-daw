package Biblioteca_v2;
import java.util.Scanner;

public class Admin extends Persona {
  public static Scanner input = new Scanner(System.in);

  public Admin(){}
  
  public Admin(String nombre, String apellido1, String apellido2, String nif, String contraseña){
    super(nombre, apellido1, apellido2, nif, contraseña);
  }

  public static void añadirAdmin(Biblioteca biblioteca){
    Tools.br();
    System.out.println("\t\tAÑADIR ADMINISTRADOR");
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
        Admin admin = new Admin(nombre, apellido1, apellido2, nif, contraseña);
        biblioteca.getListaAdmins().add(admin);
        System.out.println("[+] Administrador añadido");
        ok = true;
        Tools.continuar();
      } else {
        System.out.println("[-] La contraseña debe tener 8 caracteres como mínimo");
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
            System.out.println("[!] Introduce 1 o 2");
          }
        }while(!ok2);
      }
    }while(!ok);
  }

  public static void eliminarAdmin(Biblioteca biblioteca){
    Tools.br();
    System.out.println("\t\tELIMINAR ADMINISTRADOR");
    Tools.br();
    System.out.println("Introduce el N.I.F > ");
    String nif = input.nextLine();
    boolean found = false;
    for(int j = 0; j < biblioteca.getListaAdmins().size(); j ++){
      if(nif.equals(biblioteca.getListaAdmins().get(j).getNif())){
        found = true;
        Tools.br();
        System.out.println("\n[!] Está a punto de eliminar el siguiente administrador:\n");
        System.out.println(biblioteca.getListaAdmins().get(j));
        Tools.br();
        if(Tools.confirmar()){
          biblioteca.getListaAdmins().remove(biblioteca.getListaAdmins().get(j));
          Tools.br();
          System.out.println("\n[+] Administrador eliminado\n");
        } else {
          Tools.br();
          System.out.println("\n[-] Operación interrumpida\n");
        }
        Tools.continuar();
        j = biblioteca.getListaAdmins().size();
      } 
    }
    if (!found){
      Tools.br();
      System.out.println("\n[-] No encontramos a ningún administrador con N.I.F " + nif + "\n");
      Tools.br();
      Tools.continuar();
    }
  }

  
}

