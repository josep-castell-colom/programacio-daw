package Biblioteca_v2;
import java.util.ArrayList;
import java.util.Date;
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
    Date date = new Date();
    currentBiblioteca = new Biblioteca("Biblioteca Virtual del CIFP FBMoll");
    ArrayList<Libro> libreria = new ArrayList<Libro>();
    Libro libro = new Libro("libro", "titulo", "autor", "editorial", 5, 4);
    libreria.add(libro);
    Libro libro2 = new Libro("libro2", "titulo2", "autor2", "editorial2", 5, 4);
    libreria.add(libro2);
    Libro libro3 = new Libro("libro3", "titulo3", "autor3", "editorial3", 7, 6);
    libreria.add(libro3);
    Libro libro4 = new Libro("libro4", "titulo4", "autor4", "editorial4", 1, 0);
    libreria.add(libro4);
    Libro libro5 = new Libro("libro5", "titulo5", "autor5", "editorial5", 4, 3);
    libreria.add(libro5);
    ArrayList<Reserva> listaReservas = new ArrayList<Reserva>();
    Reserva reserva1 = new Reserva(libro, date);
    listaReservas.add(reserva1);
    Reserva reserva2 = new Reserva(libro2, date);
    listaReservas.add(reserva2);
    Reserva reserva3 = new Reserva(libro3, date);
    listaReservas.add(reserva3);
    Reserva reserva4 = new Reserva(libro4, date);
    listaReservas.add(reserva4);
    Reserva reserva5 = new Reserva(libro5, date);
    listaReservas.add(reserva5);
    currentBiblioteca.setListaLibros(libreria);
    User usuario1 = new User("Toni", "Mateu", "Martorell", "34", "666111222", "Calle Patata 23", "07156", "toni@gmail.com");
    currentBiblioteca.getListaPersonas().add(usuario1);
    User usuario2 = new User("Maria", "Amengual", "Bestard", "25", "666222111", "Calle Coliflor 15", "07456", "mariapatata@hotmail.com");
    usuario2.setListaReservas(listaReservas);
    currentBiblioteca.getListaPersonas().add(usuario2);
    currentUser = usuario1;
    Admin admin1 = new Admin("Josep", "Castell", "Colom", "22", "Jefe Máximo", "josep", "hola");
    currentBiblioteca.getListaPersonas().add(admin1);
    currentAdmin = admin1;
    Tools.mainMenu(currentBiblioteca);
    
  }
}
