package Biblioteca_v2;
import java.util.ArrayList;

public class Biblioteca {
  private String nombre;
  private ArrayList<Libro> listaLibros;
  private ArrayList<User> listaUsuarios;
  private ArrayList<Admin> listaAdmins;

  // CONSTRUCTORES
  public Biblioteca(){
    this.listaLibros = new ArrayList<Libro>();
    this.listaAdmins = new ArrayList<Admin>();
    this.listaUsuarios = new ArrayList<User>();
  }

  public Biblioteca(String nombre){
    this.nombre = nombre;
    this. listaLibros = new ArrayList<Libro>();
    this.listaUsuarios = new ArrayList<User>();
    this.listaAdmins = new ArrayList<Admin>();
  }

  public Biblioteca(Biblioteca biblioteca){
    this.nombre = biblioteca.getNombre();
    this.listaLibros = biblioteca.getListaLibros();
    this.listaUsuarios = biblioteca.getListaUsuarios();
    this.listaAdmins = biblioteca.getListaAdmins();
  }

  // GETTERS Y SETTERS
  public String getNombre(){
    return nombre;
  }
  
  public void setNombre(String nombre){
    this.nombre = nombre;
  }

  public ArrayList<Libro> getListaLibros(){
    return listaLibros;
  }

  public void setListaLibros(ArrayList<Libro> lista){
    this.listaLibros = lista;
  }

  public ArrayList<User> getListaUsuarios(){
    return listaUsuarios;
  }

  public void setListaUsuarios(ArrayList<User> lista){
    this.listaUsuarios = lista;
  }

  public ArrayList<Admin> getListaAdmins(){
    return listaAdmins;
  }

  public void setListaAdmins(ArrayList<Admin> lista){
    this.listaAdmins = lista;
  }

  @Override
  public String toString(){
    return
    "NOMBRE: " + this.nombre +
    "\nLista libros: " + this.listaLibros +
    "\nLista de usuarios: " + this.listaUsuarios +
    "\nLista de administradores: " + this.listaAdmins;
  }

  //METODOS
  public void mostrarLibros(){
    if(this.getListaLibros().size() > 0){
      Tools.mensaje("pos", "mostrando todos los libros:", "");
      for(int i = 0; i < this.getListaLibros().size(); i ++){
        System.out.println(this.getListaLibros().get(i));
        Tools.br();
      }
    } else {
      Tools.mensaje("neg", "esta biblioteca no dispone de ning??n libro", "continuar");
    }
    Tools.continuar();
  }

  public void mostrarDisponibles(){
    if(this.getListaLibros().size() > 0){
      boolean found = false;
      for(int i = 0; i < this.getListaLibros().size(); i ++){
        if(this.getListaLibros().get(i).getNumCopiasDisponibles() > 0){
          found = true;
          Tools.mensaje("titulo", "mostrando los libros disponibles:","");
          i = this.getListaLibros().size();
        }
      }
      if (found){
        for(int i = 0; i < this.getListaLibros().size(); i ++){
          if(this.getListaLibros().get(i).getNumCopiasDisponibles() > 0){
              System.out.println(this.getListaLibros().get(i));
              Tools.br();
              found = true;
            }
        }
      } else {
        Tools.mensaje("neg", "lo sentimos, no hay ning??n libro disponible","");
      }
    } else {
      Tools.mensaje("neg", "esta biblioteca no dispone de ning??n libro","");
    }
    Tools.continuar();
  }

  public void mostrarLibrosReservados(){
    Tools.mensaje("titulo", "todos los libros reservados", "");
    boolean hayLibros = false;
    if(this.getListaUsuarios().size() > 0){
      for(int i = 0; i < this.getListaUsuarios().size(); i ++){
        if(this.getListaUsuarios().get(i).getLibrosReservados().size() > 0){
          hayLibros = true;
          System.out.println("[+] Mostrando libros reservados por " + this.getListaUsuarios().get(i).getNombre());
          for(int j = 0; j < this.getListaUsuarios().get(i).getLibrosReservados().size(); j ++){
            Tools.br();
            System.out.println(this.getListaUsuarios().get(i).getLibrosReservados().get(j));
            Tools.br();
          }
        }
      }
      if(!hayLibros){
        Tools.mensaje("neg", "ning??n usuario ha reservado ning??n libro", "");
      }
    } else {
      Tools.mensaje("neg", "no hay usuarios registrados", "");
    }
    Tools.continuar();
  }

  public void mostrarSinReservas(){
    Tools.mensaje("titulo", "mostrando libros sin ninguna reserva", "");
    boolean reservas = false;
    if(this.getListaLibros().size() > 0){
      for(int i = 0; i < this.getListaLibros().size(); i ++){
        if(this.getListaLibros().get(i).getNumCopias() == this.getListaLibros().get(i).getNumCopiasDisponibles()){
          Tools.mensaje(this.getListaLibros().get(i).toString());
          reservas = true;
        }
      }
    }
    if (!reservas) {
      Tools.mensaje("neg", "ning??n t??tulo tiene 0 reservas", "");
    }
    Tools.continuar();
  }

  public void mostrarUsuarios(){
    if(this.getListaUsuarios().size() > 0){
      Tools.mensaje("titulo", "mostrando todos los usuarios", "");
      for(int i = 0; i < this.getListaUsuarios().size(); i ++){
        System.out.println(this.getListaUsuarios().get(i));
        Tools.br();
      }
    } else {
      Tools.mensaje("neg", "esta biblioteca no tiene ning??n usuario", "");
    }
    Tools.continuar();
  }

  public void mostrarAdmin(){
    if(this.getListaAdmins().size() > 0){
      Tools.mensaje("titulo", "mostrando todos los administradores", "");
      for(int i = 0; i < this.getListaAdmins().size(); i ++){
        System.out.println(this.getListaAdmins().get(i));
        Tools.br();
      }
    } else {
      Tools.mensaje("neg", "esta biblioteca no tiene ning??n administrador", "");
    }
    Tools.continuar();
  }
}
