package Biblioteca_v2;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class User extends Persona {
  public static Scanner input = new Scanner(System.in);
  private String tel;
  private String direccion;
  private String codigoPostal;
  private String email;
  private ArrayList<Reserva> listaReservas;

  public User(){}

  public User(String nombre, String apellido1, String apellido2, String edad, String nif, String contraseña){
    super(nombre, apellido1, apellido2, edad, nif, contraseña);
    listaReservas = new ArrayList<Reserva>();
  }

  public ArrayList<Reserva> getListaReservas(){
    return listaReservas;
  }

  public void setListaReservas(ArrayList<Reserva> lista){
    listaReservas = lista;
  }

  

  public static Scanner getInput() {
    return input;
  }

  public static void setInput(Scanner input) {
    User.input = input;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getCodigoPostal() {
    return codigoPostal;
  }

  public void setCodigoPostal(String codigoPostal) {
    this.codigoPostal = codigoPostal;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public static void eliminarUser(Biblioteca biblioteca){
    Tools.mensaje("titulo", "eliminar usuario", "");
    System.out.println("Introduce el N.I.F > ");
    String nif = input.nextLine();
    boolean found = false;
    for(int i = 0; i < biblioteca.getListaUsuarios().size(); i ++){
      if(nif.equals(biblioteca.getListaUsuarios().get(i).getNif())){
        found = true;
        if(biblioteca.getListaUsuarios().get(i).getListaReservas().size() == 0){
          Tools.mensaje("alert", "está a punto de eliminar el siguiente usuario:\n" + biblioteca.getListaUsuarios().get(i), "");
          if(Tools.confirmar()){
            biblioteca.getListaUsuarios().remove(biblioteca.getListaUsuarios().get(i));
            Tools.mensaje("pos", "usuario eliminado", "continuar");
          } else {
            Tools.mensaje("neg", "operación interrumpida", "continuar");
          }
          i = biblioteca.getListaUsuarios().size();
        } else {
          Tools.mensaje("neg", "no se puede eliminar el usuario porque tiene libros reservados\n", "continuar");
        }
      } 
    }
    if (!found){
      Tools.mensaje("neg", "no encontramos a nadie con nif " + nif, "continuar");
    }
  }

  public void reservarLibro(Biblioteca biblioteca){
    if(this.getListaReservas().size() < 5){
      Tools.mensaje("titulo", "reservar libro", "");
      int posicion = Libro.buscarIsbn(biblioteca.getListaLibros());
      if(posicion > -1){
        boolean repetido = false;
        for(int i = 0; i < this.getListaReservas().size(); i ++){
          if(this.getListaReservas().get(i).getLibroReservado().getIsbn() == biblioteca.getListaLibros().get(posicion).getIsbn()){
            repetido = true;
          }
        }
        if(biblioteca.getListaLibros().get(posicion).getNumCopiasDisponibles() > 0 && !repetido){
          Date date = new Date();
          Reserva reserva = new Reserva(biblioteca.getListaLibros().get(posicion), date);
          Tools.br();
          System.out.println("Está a punto de reservar el libro " + biblioteca.getListaLibros().get(posicion).getTitulo());
          if(Tools.confirmar()){
            this.getListaReservas().add(reserva);
            biblioteca.getListaLibros().get(posicion).setNumCopiasDisponibles(biblioteca.getListaLibros().get(posicion).getNumCopiasDisponibles() - 1);
            Tools.mensaje("pos", "libro reservado", "continuar");
          }
        } else if(biblioteca.getListaLibros().get(posicion).getNumCopiasDisponibles() == 0 && !repetido){
          Tools.mensaje("neg", "lo sentimos, no disponemos de copias disponibles para el libro " + biblioteca.getListaLibros().get(posicion).getTitulo(), "continuar");
        } else if(repetido){
          Tools.mensaje("neg", "usted ya dispone de una copia de este libro", "continuar");
        }
      }
    } else {
      Tools.mensaje("neg", "usted ya tiene 5 libros reservados", "");
      Tools.maxReservas();
    }
  }

  public void devolverLibro(ArrayList<Libro> lista){
    Tools.mensaje("titulo", "devolver libro", "");
    int posicion = Libro.buscarIsbnReserva(this.getListaReservas());
    if(posicion > -1){
      Tools.br();
      System.out.println("Está a punto de devolver el libro " + this.getListaReservas().get(posicion).getLibroReservado().getTitulo());
      if(Tools.confirmar()){
        for(int i = 0; i < lista.size(); i ++){
          if(this.getListaReservas().get(posicion).getLibroReservado().getIsbn().equals(lista.get(i).getIsbn())){
            lista.get(i).setNumCopiasDisponibles(lista.get(i).getNumCopiasDisponibles() + 1);
            this.getListaReservas().remove(this.getListaReservas().get(posicion));
            i = lista.size();
            Tools.mensaje("pos", "libro devuelto", "continuar");
          }
        }
      }
    }
  }

  public void mostrarLibrosReservados(){
    Tools.mensaje("titulo", "mostrando los libros reservados por '" + this.getNombre() + "'", "");
    if(this.getListaReservas().size() > 0){
      for(int i = 0; i < this.getListaReservas().size(); i ++){
        System.out.println(this.getListaReservas().get(i).getLibroReservado());
        Tools.br();
      }
    } else {
      Tools.mensaje("neg", "no tiene ningún libro reservado", "");
    }
    Tools.continuar();
  }
}
