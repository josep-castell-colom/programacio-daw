package Biblioteca_v2;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  public static Scanner input = new Scanner(System.in);
  private static Biblioteca currentBiblioteca;
  private static Admin currentAdmin;
  private static User currentUser;
  private static boolean on = true;

  public static Biblioteca getCurrentBiblioteca() {
    return currentBiblioteca;
  }

  public static void setCurrentBiblioteca(Biblioteca biblioteca) {
    currentBiblioteca = biblioteca;
  }

  public static Admin getCurrentAdmin() {
    return currentAdmin;
  }

  public static void setCurrentAdmin(Admin admin) {
    currentAdmin = admin;
  }

  public static User getCurrentUser() {
    return currentUser;
  }

  public static void setCurrentUser(User user) {
    currentUser = user;
  }

  public static boolean getOn(){
    return on;
  }

  public static void setOn(boolean bool){
    on = bool;
  }

  public static void main(String[] args) {
    currentBiblioteca = new Biblioteca("Biblioteca Virtual del CIFP FBMoll");
    ArrayList<Libro> libreria = new ArrayList<Libro>();
    Libro libro = new Libro("libro", "titulo", "autor", "editorial", 5);
    libreria.add(libro);
    Libro libro2 = new Libro("libro2", "titulo2", "autor2", "editorial2", 5);
    libreria.add(libro2);
    Libro libro3 = new Libro("libro3", "titulo3", "autor3", "editorial3", 5);
    libreria.add(libro3);
    Libro libro4 = new Libro("libro4", "titulo4", "autor4", "editorial4", 5, 0);
    libreria.add(libro4);
    Libro libro5 = new Libro("libro5", "titulo5", "autor5", "editorial5", 5);
    libreria.add(libro5);
    ArrayList<Libro> libreriaUsuario = new ArrayList<Libro>();
    libreriaUsuario.add(libro);
    libreriaUsuario.add(libro2);
    libreriaUsuario.add(libro3);
    libreriaUsuario.add(libro4);
    libreriaUsuario.add(libro5);
    currentBiblioteca.setListaLibros(libreria);
    User usuario1 = new User("Toni", "Mateu", "Martorell", "toni", "hola");
    currentBiblioteca.getListaUsuarios().add(usuario1);
    User usuario2 = new User("Maria", "Amengual", "Bestard", "maria", "hola");
    usuario2.setLibrosReservados(libreriaUsuario);
    currentBiblioteca.getListaUsuarios().add(usuario2);
    currentUser = usuario1;
    Admin admin1 = new Admin("Josep", "Castell", "Colom", "josep", "hola");
    currentBiblioteca.getListaAdmins().add(admin1);
    currentAdmin = admin1;
    Tools.mainMenu(currentBiblioteca);
  }
}
