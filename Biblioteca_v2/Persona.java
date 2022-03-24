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
      Tools.mensaje("-", "introduce un NIF válido", "continuar");
    }
    if(found && !valid){
      Tools.mensaje("-", "contraseña incorrecta", "");
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

  public static int buscarPersona(Biblioteca biblioteca){
    Tools.mensaje("titulo", "buscar usuario/admin", "");
    System.out.println("Introduce correo electrónico (usuario) o NIF (admin)");
    String busqueda = Tools.prompt();
    int posicion = -1;
    boolean found = false;
    Persona persona = new Admin();
    Pair pair = Tools.splitPersonas(biblioteca);
    try{
      for(int i = 0; i < pair.getListaUsuarios().size() && !found; i ++){
        if(busqueda.equals(pair.getListaUsuarios().get(i).getEmail())){
          persona = pair.getListaUsuarios().get(i);
          found = true;
          Tools.mensaje("+", "usuario encontrado", "continuar");
        }
      }
      for(int i = 0; i < pair.getListaAdmins().size() && !found; i ++){
        if(busqueda.equals(pair.getListaAdmins().get(i).getNif())){
          found = true;
          persona = pair.getListaAdmins().get(i);
          Tools.mensaje("+", "bibliotecario encontrado", "continuar");
        }
      }
      if(!found){
        throw new CustomException(1);
      }
    }catch(CustomException exception){
      Tools.mensaje("-", exception.getMessage(), "continuar");
    }
    if(found){
      for(int i = 0; i < biblioteca.getListaPersonas().size() && posicion == -1; i ++){
        if(persona.compararPersona(biblioteca.getListaPersonas().get(i))){
          posicion = i;
        }
      }
    }
    return posicion;
  }

  public static void eliminarPersona(Biblioteca biblioteca){
    int posicion = buscarPersona(biblioteca);
    if(posicion != -1){
      if(Tools.checkType(biblioteca.getListaPersonas().get(posicion), User.class)){
        User user = (User)biblioteca.getListaPersonas().get(posicion);
        if(user.getListaReservas().size() > 0){
          Tools.mensaje("-", "no se puede eliminar al usuario " + user.getNombre() + " porque tiene libros reservados", "continuar");
        } else {
          Tools.mensaje("!", "está a punto de eliminar al usuario " + user.getNombre() + " " + user.getApellidos(), "");
          if(Tools.confirmar()){
            biblioteca.getListaPersonas().remove(biblioteca.getListaPersonas().get(posicion));
          }
        }
      } else {
        Tools.mensaje("!", "está a punto de eliminar al bibliotecario " + biblioteca.getListaPersonas().get(posicion).getNombre() + " " + biblioteca.getListaPersonas().get(posicion).getApellidos());
        if(Tools.confirmar()){
          biblioteca.getListaPersonas().remove(biblioteca.getListaPersonas().get(posicion));
        }
      }
    }
  }

  public boolean compararPersona(Persona persona){
    boolean found = false;
    if(persona.getNombre().equals(this.getNombre()) && persona.getApellidos().equals(this.getApellidos()) && persona.getEdad().equals(this.getEdad())){
      found = true;
    }
    return found;
  }
}
