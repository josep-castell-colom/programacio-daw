package Biblioteca_v2;
import java.util.Scanner;

public abstract class Persona {
  public static Scanner input = new Scanner(System.in);
  private String nombre;
  private String apellido1;
  private String apellido2;
  private String nif;
  private String contraseña;

  public Persona(){}

  public Persona(String nombre, String apellido1, String apellido2, String nif, String contraseña) {
    this.nombre = nombre;
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
    this.nif = nif;
    this.contraseña = contraseña;
  }

  public Persona(Persona persona){
    nombre = persona.getNombre();
    apellido1 = persona.getApellido1();
    apellido2 = persona.getApellido2();
    nif = persona.getNif();
    contraseña = persona.getContraseña();
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido1() {
    return apellido1;
  }

  public void setApellido1(String apellido1) {
    this.apellido1 = apellido1;
  }

  public String getApellido2() {
    return apellido2;
  }

  public void setApellido2(String apellido2) {
    this.apellido2 = apellido2;
  }

  public String getApellidos(){
    return apellido1 + " " + apellido2;
  }

  public void setApellidos(String apellido1, String apellido2){
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
  }

  public String getNif() {
    return nif;
  }

  public void setNif(String nif) {
    this.nif = nif;
  }

  public String getContraseña() {
    return contraseña;
  }

  public void setContraseña(String contraseña) {
    this.contraseña = contraseña;
  }

  @Override
  public String toString(){
    return
    "NOMBRE:\t\t" + nombre +
    "\nAPELLIDOS:\t" + apellido1 + " " + apellido2 +
    "\nN.I.F.:\t\t" + nif +
    "\nCONTRASEÑA:\t" + contraseña;
  }

  public static void eliminarPersona(Biblioteca biblioteca){
    System.out.println("Introduce el N.I.F > ");
    String nif = input.nextLine();
    boolean found = false;
    for(int i = 0; i < biblioteca.getListaUsuarios().size(); i ++){
      if(nif.equals(biblioteca.getListaUsuarios().get(i).getNif())){
        found = true;
        if(biblioteca.getListaUsuarios().get(i).getLibrosReservados().size() == 0){
          Tools.br();
          System.out.println("Está a punto de eliminar el siguiente usuario:");
          System.out.println(biblioteca.getListaUsuarios().get(i));
          Tools.br();
          if(Tools.confirmar()){
            biblioteca.getListaUsuarios().remove(biblioteca.getListaUsuarios().get(i));
            System.out.println("[+] Usuario eliminado");
            Tools.continuar();
          } else {
            System.out.println("[-] Operación interrumpida");
            Tools.continuar();
          }
          i = biblioteca.getListaUsuarios().size();
        } else {
          System.out.println("[-] No se puede eliminar el usuario porque tiene libros reservados");
        }
      } 
    }
    if (!found){
      for(int j = 0; j < biblioteca.getListaAdmins().size(); j ++){
        if(nif.equals(biblioteca.getListaAdmins().get(j).getNif())){
          found = true;
          Tools.br();
          System.out.println("Está a punto de eliminar el siguiente administrador:");
          System.out.println(biblioteca.getListaAdmins().get(j));
          Tools.br();
          if(Tools.confirmar()){
            biblioteca.getListaAdmins().remove(biblioteca.getListaAdmins().get(j));
            System.out.println("[+] Administrador eliminado");
          } else {
            System.out.println("[-] Operación interrumpida");
            Tools.continuar();
          }
          j = biblioteca.getListaAdmins().size();
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
}
