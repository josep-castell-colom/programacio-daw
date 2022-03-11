package Biblioteca_v2;
import java.util.ArrayList;

public class Biblioteca {
  private String nombre;
  private ArrayList<Libro> listaLibros;
  private ArrayList<Persona> listaPersonas;

  // CONSTRUCTORES
  public Biblioteca(){
    this.listaLibros = new ArrayList<Libro>();
    this.listaPersonas = new ArrayList<Persona>();
  }

  public Biblioteca(String nombre){
    this.nombre = nombre;
    this. listaLibros = new ArrayList<Libro>();
    this.listaPersonas = new ArrayList<Persona>();
  }

  public Biblioteca(Biblioteca biblioteca){
    this.nombre = biblioteca.getNombre();
    this.listaLibros = biblioteca.getListaLibros();
    this.listaPersonas = biblioteca.getListaPersonas();
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

  public ArrayList<Persona> getListaPersonas(){
    return listaPersonas;
  }

  public void setListaPersonas(ArrayList<Persona> lista){
    this.listaPersonas = lista;
  }

  @Override
  public String toString(){
    return
    "NOMBRE: " + this.nombre +
    "\nLista libros: " + this.listaLibros +
    "\nLista de personas: " + this.listaPersonas;
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
      Tools.mensaje("neg", "esta biblioteca no dispone de ningún libro", "continuar");
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
        Tools.mensaje("neg", "lo sentimos, no hay ningún libro disponible","");
      }
    } else {
      Tools.mensaje("neg", "esta biblioteca no dispone de ningún libro","");
    }
    Tools.continuar();
  }

  public void mostrarLibrosReservados(){
    Tools.mensaje("titulo", "todos los libros reservados", "");
    boolean hayLibros = false;
    boolean hayUsuarios = false;
    if(this.getListaPersonas().size() > 0){
      ArrayList<User> listaUsuarios = new ArrayList<User>();
      for(int i = 0; i < this.getListaPersonas().size(); i ++){
        if(Tools.checkType(this.getListaPersonas().get(i), User.class)){
          listaUsuarios.add((User)this.getListaPersonas().get(i));
          hayUsuarios = true;
        }
      }
      if(listaUsuarios.size() > 0){
        for(int i = 0; i < listaUsuarios.size(); i ++){
          if(listaUsuarios.get(i).getListaReservas().size() > 0){
            hayLibros = true;
            System.out.println("[+] Mostrando libros reservados por " + listaUsuarios.get(i).getNombre());
            for(int j = 0; j < listaUsuarios.get(i).getListaReservas().size(); j ++){
              Tools.br();
              System.out.println(listaUsuarios.get(i).getListaReservas().get(j).getLibroReservado());
              Tools.br();
            }
          }
        }
      }
    }
    if(!hayLibros){
      Tools.mensaje("neg", "ningún usuario ha reservado ningún libro", "");
    }
    if(!hayUsuarios) {
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
      Tools.mensaje("neg", "ningún título tiene 0 reservas", "");
    }
    Tools.continuar();
  }

  public void mostrarUsuarios(){
    if(this.getListaPersonas().size() > 0){
      boolean found = false;
      Tools.mensaje("titulo", "mostrando todos los usuarios", "");
      for(int i = 0; i < this.getListaPersonas().size(); i ++){
        if(Tools.checkType(this.getListaPersonas().get(i), User.class)){
          found = true;
          System.out.println(this.getListaPersonas().get(i));
          Tools.br();
        }
      }
      if(!found) {
        Tools.mensaje("neg", "esta biblioteca no tiene ningún usuario", "");
      } 
    }
    Tools.continuar();
  }

  public void mostrarAdmin(){
    if(this.getListaPersonas().size() > 0){
      boolean found = false;
      Tools.mensaje("titulo", "mostrando todos los administradores", "");
      for(int i = 0; i < this.getListaPersonas().size(); i ++){
        if(Tools.checkType(this.getListaPersonas().get(i), Admin.class)){
          System.out.println(this.getListaPersonas().get(i));
          Tools.br();
          found = true;
        }
      }
      if(!found) {
        Tools.mensaje("neg", "esta biblioteca no tiene ningún administrador", "");
      }
    } 
    Tools.continuar();
  }
}
