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
    ArrayList<Libro> listaPrueba = new ArrayList<Libro>();
    Libro libro = new Libro("libro", "titulo", "autor", "editorial", 5);
    listaPrueba.add(libro);
    Libro libro2 = new Libro("libro2", "titulo2", "autor2", "editorial2", 5);
    listaPrueba.add(libro2);
    Libro libro3 = new Libro("libro3", "titulo3", "autor3", "editorial3", 5);
    listaPrueba.add(libro3);
    Libro libro4 = new Libro("libro4", "titulo4", "autor4", "editorial4", 5, 0);
    listaPrueba.add(libro4);
    currentBiblioteca.setListaLibros(listaPrueba);
    User usuario1 = new User("Toni", "Mateu", "Martorell", "toni", "hola");
    currentBiblioteca.getListaUsuarios().add(usuario1);
    User usuario2 = new User("Maria", "Amengual", "Bestard", "maria", "hola");
    currentBiblioteca.getListaUsuarios().add(usuario2);
    currentUser = usuario1;
    Admin admin1 = new Admin("Josep", "Castell", "Colom", "josep", "hola");
    currentBiblioteca.getListaAdmins().add(admin1);
    currentAdmin = admin1;
    Tools.mainMenu(currentBiblioteca);
  }
}
