package Biblioteca_v2;
import java.util.ArrayList;
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

  public static Pair validar(Biblioteca biblioteca, String opcion){
    ArrayList<User> listaUsuarios = biblioteca.getListaUsuarios();
    ArrayList<Admin> listaAdmins = biblioteca.getListaAdmins();
    Pair pair = new Pair();
    boolean found = false;
    boolean valid = false;
    Tools.mensaje("titulo", "inicie sesión", "");
    System.out.println("Introduce N.I.F.: ");
    String nif = Tools.prompt();
    if(opcion.equals("user")){
      for(int i = 0; i < listaUsuarios.size() && !found; i ++){
        if(nif.equals(listaUsuarios.get(i).getNif())){
          found = true;
          System.out.println();
          System.out.println("Introduce contraseña:");
          String contraseña = Tools.prompt();
          for(int j = 0; j < listaUsuarios.size() && !valid; j ++){
            if(contraseña.equals(listaUsuarios.get(i).getContraseña()) && nif.equals(listaUsuarios.get(i).getNif())){
              valid = true;
              Tools.mensaje("Bienvenid@ " + listaUsuarios.get(i).getNombre());
              pair.setUser(listaUsuarios.get(i));
              pair.setValid(true);
            }
          }
        }
      }
    }
    if(opcion.equals("admin")){
      for(int j = 0; j < listaAdmins.size() && !found; j ++){
        if(nif.equals(listaAdmins.get(j).getNif())){
          found = true;
          System.out.println();
          System.out.println("Introduce contraseña:");
          String contraseña = input.nextLine();
          for(int k = 0; k < listaAdmins.size() && !valid; k ++){
            if(contraseña.equals(listaAdmins.get(k).getContraseña()) && nif.equals(listaAdmins.get(k).getNif())){
              valid = true;
              Tools.mensaje("Bienvenid@ " + listaAdmins.get(k).getNombre());
              pair.setAdmin(listaAdmins.get(k));
              pair.setValid(true);
            }
          }
        }
      }
    }
    if(!found && !valid){
      Tools.mensaje("neg", "introduce un NIF válido", "continuar");
    }
    if(found && !valid){
      Tools.mensaje("neg", "contraseña incorrecta", "continuar");
    }
    return pair;
  }
}
