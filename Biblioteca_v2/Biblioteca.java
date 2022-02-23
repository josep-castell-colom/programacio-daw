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
      Tools.br();
      System.out.println("[+] Mostrando todos los libros:");
      Tools.br();
      for(int i = 0; i < this.getListaLibros().size(); i ++){
        System.out.println(this.getListaLibros().get(i));
        Tools.br();
      }
    } else {
      Tools.br();
      System.out.println("[-] Esta biblioteca no dispone de ningún libro");
      Tools.br();
    }
    Tools.continuar();
  }

  public void mostrarDisponibles(){
    if(this.getListaLibros().size() > 0){
      boolean found = false;
      for(int i = 0; i < this.getListaLibros().size(); i ++){
        if(this.getListaLibros().get(i).getNumCopiasDisponibles() > 0){
          found = true;
          Tools.br();
          System.out.println("[+] Mostrando los libros disponibles: ");
          Tools.br();
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
        Tools.br();
        System.out.println("[-] Lo sentimos, no hay ningún libro disponible");
        Tools.br();
      }
    } else {
      Tools.br();
      System.out.println("[-] Esta biblioteca no dispone de ningún libro");
      Tools.br();
    }
    Tools.continuar();
  }

  public void mostrarLibrosReservados(){
    Tools.br();
    System.out.println("\t\tTODOS LOS LIBROS RESERVADOS");
    Tools.br();
    if(this.getListaUsuarios().size() > 0){
      for(int i = 0; i < this.getListaUsuarios().size(); i ++){
        System.out.println("[+] Mostrando libros reservados por " + this.getListaUsuarios().get(i).getNombre());
        for(int j = 0; j < this.getListaUsuarios().get(i).getLibrosReservados().size(); j ++){
          Tools.br();
          System.out.println(this.getListaUsuarios().get(i).getLibrosReservados().get(j));
          Tools.br();
        }
    }
    }
    Tools.br();
  }

  public void mostrarUsuarios(){
    if(this.getListaUsuarios().size() > 0){
      Tools.br();
      System.out.println("\t\tMOSTRANDO TODOS LOS USUARIOS");
      Tools.br();
      for(int i = 0; i < this.getListaUsuarios().size(); i ++){
        System.out.println(this.getListaUsuarios().get(i));
        Tools.br();
      }
    } else {
      Tools.br();
      System.out.println("\n[-] Esta biblioteca no tiene ningún usuario\n");
      Tools.br();
    }
    Tools.continuar();
  }

  public void mostrarAdmin(){
    if(this.getListaAdmins().size() > 0){
      Tools.br();
      System.out.println("\t\tMOSTRANDO TODOS LOS ADMINISTRADORES");
      Tools.br();
      for(int i = 0; i < this.getListaAdmins().size(); i ++){
        System.out.println(this.getListaAdmins().get(i));
        Tools.br();
      }
    } else {
      Tools.br();
      System.out.println("\n[-] Esta biblioteca no tiene ningún administrador\n");
      Tools.br();
    }
    Tools.continuar();
  }
}
