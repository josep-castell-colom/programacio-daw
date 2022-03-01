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

  public boolean setContraseña(String contraseña) {
    boolean valid = false;
    if(contraseña.length() > 7){
      this.contraseña = contraseña;
      valid = true;
    } else {
      Tools.mensaje("neg", "la contraseña debe contener al menos 8 caracteres", "continuar");
      
    }
    return valid;
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

  public static void añadirPersona(String modo, Biblioteca biblioteca){
    switch(modo){
      case "user":  Tools.mensaje("titulo", "añadir usuario", "");
                    break;
      case "admin": Tools.mensaje("titulo", "añadir administrador", "");
    }
    System.out.println("Nombre");
    String nombre = Tools.nameFirstUpperCase(Tools.prompt());
    System.out.println("Apellido1");
    String apellido1 = Tools.nameFirstUpperCase(Tools.prompt());
    System.out.println("Apellido2");
    String apellido2 = Tools.nameFirstUpperCase(Tools.prompt());
    System.out.println("N.I.F.");
    String nif = Tools.prompt();
    String contraseña;
    switch(modo){
      case "user":
        User user = new User();
        user.setNombre(nombre);
        user.setApellidos(apellido1, apellido2);
        user.setNif(nif);
        System.out.println("Contraseña");
        contraseña = Tools.prompt();
        boolean userPassOk = user.setContraseña(contraseña);
        if(!userPassOk){
          while(!userPassOk && Tools.repetirContraseña()){
            System.out.println("Contraseña");
            contraseña = Tools.prompt();
            userPassOk = user.setContraseña(contraseña);
          }
        } 
        if (userPassOk) {
          biblioteca.getListaUsuarios().add(user);
          Tools.mensaje("pos", "usuario añadido: \n\n" + user, "continuar");
        }
          break;
      case "admin":
        Admin admin = new Admin();
        admin.setNombre(nombre);
        admin.setApellidos(apellido1, apellido2);
        admin.setNif(nif);
        System.out.println("Contraseña");
        contraseña = Tools.prompt();
        boolean adminPassOk = admin.setContraseña(contraseña);
        if(!adminPassOk){
          while(!adminPassOk && Tools.repetirContraseña()){
            System.out.println("Contraseña");
            contraseña = Tools.prompt();
            adminPassOk = admin.setContraseña(contraseña);
          }
        } 
        if (adminPassOk) {
          biblioteca.getListaAdmins().add(admin);
          Tools.mensaje("pos", "administrador añadido: \n\n" + admin, "continuar");
        }
    }

    
  }
}
