package Biblioteca_v2;
import java.util.Scanner;

public class Admin extends Persona {
  public static Scanner input = new Scanner(System.in);

  private String puesto;
  private String nif;
  private String contraseña;

  public Admin(){}
  
  public Admin(String nombre, String apellido1, String apellido2, String edad, String nif, String contraseña){
    super(nombre, apellido1, apellido2, edad, nif, contraseña);
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

