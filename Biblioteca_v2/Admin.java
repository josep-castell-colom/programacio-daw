package Biblioteca_v2;
import java.util.Scanner;

public class Admin extends Persona {
  public static Scanner input = new Scanner(System.in);

  private String puesto;
  private String nif;
  private String contraseña;

  public Admin(){}
  
  public Admin(String nombre, String apellido1, String apellido2, String edad, String puesto, String nif, String contraseña){
    super(nombre, apellido1, apellido2, edad);
    this.setPuesto(puesto);
    this.setNif(nif);
    this.setContraseña(contraseña);
  }

  public String getPuesto() {
    return puesto;
  }

  public void setPuesto(String puesto) {
    this.puesto = puesto;
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
    "PUESTO: " + puesto +
    "\nNIF: " + nif +
    "\nCONTRASEÑA: " + contraseña;
  }

  @Override
  public void solicitarDatos(){
    Tools.mensaje("titulo", "modificar datos", "");
    System.out.println("Nombre");
    this.setNombre(Tools.nameFirstUpperCase(Tools.prompt()));
    System.out.println("Primer apellido");
    this.setApellido1(Tools.nameFirstUpperCase(Tools.prompt()));
    System.out.println("Segundo apellido");
    this.setApellido2(Tools.nameFirstUpperCase(Tools.prompt()));
    System.out.println("Puesto de trabajo");
    this.setPuesto(Tools.prompt());
    System.out.println("Nif");
    this.setNif(Tools.prompt());
    System.out.println("Contraseña");
    this.setContraseña(Tools.prompt());
  }

  public static void reservarLibro(Biblioteca biblioteca){
    int posicion = Persona.buscarPersona(biblioteca);
    if(Tools.checkType(biblioteca.getListaPersonas().get(posicion), User.class)){

    }else{

    }
  }

  
}

