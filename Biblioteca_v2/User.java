package Biblioteca_v2;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends Persona {
  public static Scanner input = new Scanner(System.in);
  private ArrayList<Libro> librosReservados= new ArrayList<Libro>() ;

  public User(){}

  public User(String nombre, String apellido1, String apellido2, String nif, String contraseña){
    super(nombre, apellido1, apellido2, nif, contraseña);
  }

  public ArrayList<Libro> getLibrosReservados(){
    return librosReservados;
  }

  public void setLibrosReservados(ArrayList<Libro> lista){
    librosReservados = lista;
  }

  public static void eliminarUser(Biblioteca biblioteca){
    Tools.mensaje("titulo", "eliminar usuario", "");
    System.out.println("Introduce el N.I.F > ");
    String nif = input.nextLine();
    boolean found = false;
    for(int i = 0; i < biblioteca.getListaUsuarios().size(); i ++){
      if(nif.equals(biblioteca.getListaUsuarios().get(i).getNif())){
        found = true;
        if(biblioteca.getListaUsuarios().get(i).getLibrosReservados().size() == 0){
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
    if(this.getLibrosReservados().size() < 5){
      Tools.mensaje("titulo", "reservar libro", "");
      int posicion = Libro.buscarIsbn(biblioteca.getListaLibros());
      if(posicion > -1){
        boolean repetido = false;
        for(int i = 0; i < this.getLibrosReservados().size(); i ++){
          if(this.getLibrosReservados().get(i).getIsbn() == biblioteca.getListaLibros().get(posicion).getIsbn()){
            repetido = true;
          }
        }
        if(biblioteca.getListaLibros().get(posicion).getNumCopiasDisponibles() > 0 && !repetido){
          Tools.br();
          System.out.println("Está a punto de reservar el libro " + biblioteca.getListaLibros().get(posicion).getTitulo());
          if(Tools.confirmar()){
            this.getLibrosReservados().add(biblioteca.getListaLibros().get(posicion));
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
    int posicion = Libro.buscarIsbn(this.getLibrosReservados());
    if(posicion > -1){
      Tools.br();
      System.out.println("Está a punto de devolver el libro " + this.getLibrosReservados().get(posicion).getTitulo());
      if(Tools.confirmar()){
        for(int i = 0; i < lista.size(); i ++){
          if(this.getLibrosReservados().get(posicion).getIsbn().equals(lista.get(i).getIsbn())){
            lista.get(i).setNumCopiasDisponibles(lista.get(i).getNumCopiasDisponibles() + 1);
            this.getLibrosReservados().remove(this.getLibrosReservados().get(posicion));
            i = lista.size();
            Tools.mensaje("pos", "libro devuelto", "continuar");
          }
        }
      }
    }
  }

  public void mostrarLibrosReservados(){
    Tools.mensaje("titulo", "mostrando los libros reservados por '" + this.getNombre() + "'", "");
    if(this.getLibrosReservados().size() > 0){
      for(int i = 0; i < this.getLibrosReservados().size(); i ++){
        System.out.println(this.getLibrosReservados().get(i));
        Tools.br();
      }
    } else {
      Tools.mensaje("neg", "no tiene ningún libro reservado", "");
    }
    Tools.continuar();
  }
}
