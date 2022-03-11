package Biblioteca_v2;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Persona {
  public static Scanner input = new Scanner(System.in);
  private String nombre;
  private String apellido1;
  private String apellido2;
  private String edad;

  public Persona(){}

  public Persona(String nombre, String apellido1, String apellido2, String edad) {
    this.setNombre(nombre);
    this.setApellidos(apellido1, apellido2);
    this.setEdad(edad);
  }

  public Persona(Persona persona){
    this.setNombre(persona.getNombre());
    this.setApellidos(persona.getApellido1(), persona.getApellido2());
    this.setEdad(persona.getEdad());
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

  public String getEdad(){
    return this.edad;
  }

  public void setEdad(String edad){
    this.edad = edad;
  }

  @Override
  public String toString(){
    return
    "NOMBRE:\t\t" + nombre +
    "\nAPELLIDOS:\t" + apellido1 + " " + apellido2 +
    "\nEDAD:\t" + edad;
  }

  public static Pair validarAdmin(Biblioteca biblioteca){
    ArrayList<Admin> listaAdmins = new ArrayList<Admin>();
    Pair pair = new Pair();
    boolean found = false;
    boolean valid = false;
    for(int i = 0; i < biblioteca.getListaPersonas().size(); i ++){
      if(Tools.checkType(biblioteca.getListaPersonas().get(i), Admin.class)){
        listaAdmins.add((Admin)biblioteca.getListaPersonas().get(i));
      }
    }
    if(listaAdmins.size() > 0){
      Tools.mensaje("titulo", "inicie sesión", "");
      System.out.println("Introduce N.I.F.: ");
      String nif = Tools.prompt();
  
      for(int i = 0; i < listaAdmins.size() && !found; i ++){
        if(nif.equals(listaAdmins.get(i).getNif())){
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
      Tools.mensaje("neg", "contraseña incorrecta", "");
      Tools.repetirContraseña();
    }
    return pair;
  }

  public void solicitarDatos(){
        Tools.mensaje("titulo", "modificar datos", "");
        System.out.println("Nombre");
        this.setNombre(Tools.nameFirstUpperCase(Tools.prompt()));
        System.out.println("Primer apellido");
        this.setApellido1(Tools.nameFirstUpperCase(Tools.prompt()));
        System.out.println("Segundo apellido");
        this.setApellido2(Tools.nameFirstUpperCase(Tools.prompt()));
        System.out.println("Edad");
        this.setEdad(Tools.prompt());
  }
}
