package Biblioteca_v2;
import java.util.Scanner;
import java.util.ArrayList;

public class Libro {
  public static Scanner input = new Scanner(System.in);
  private String isbn;
  private String titulo;
  private String autor;
  private String editorial;
  private int numCopias;
  private int numCopiasDisponibles;

  // CONSTRUCTORES
  public Libro(){}

  public Libro(String isbn, String titulo, String autor, String editorial, int numCopias, int numCopiasDisponibles){
    this.isbn = isbn;
    this.titulo = titulo;
    this.autor = autor;
    this.editorial = editorial;
    this.numCopias = numCopias;
    this.numCopiasDisponibles = numCopiasDisponibles;
  }

  public Libro(String isbn, String titulo, String autor, String editorial, int numCopias){
    this.isbn = isbn;
    this.titulo = titulo;
    this.autor = autor;
    this.editorial = editorial;
    this.numCopias = numCopias;
    this.numCopiasDisponibles = numCopias;
  }

  public Libro(Libro libro){
    this.isbn = libro.getIsbn();
    this.titulo = libro.getTitulo();
    this.autor = libro.getAutor();
    this.editorial = libro.getEditorial();
    this.numCopias = libro.getNumCopias();
    this.numCopiasDisponibles = libro.getNumCopiasDisponibles();
  }

  // GETTERS Y SETTERS
  public String getIsbn(){
    return isbn;
  }

  public void setIsbn(String isbn){
    this.isbn = isbn;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public String getEditorial() {
    return editorial;
  }

  public void setEditorial(String editorial) {
    this.editorial = editorial;
  }

  public int getNumCopias() {
    return numCopias;
  }

  public void setNumCopias(int numCopias) {
    this.numCopias = numCopias;
  }

  public int getNumCopiasDisponibles() {
    return numCopiasDisponibles;
  }

  public void setNumCopiasDisponibles(int numCopiasDisponibles) {
    this.numCopiasDisponibles = numCopiasDisponibles;
  }

  @Override
  public String toString(){
    return 
    "ISBN:\t\t\t" + this.isbn +
    "\nTítulo:\t\t\t" + this.titulo +
    "\nAutor:\t\t\t" + this.autor +
    "\nEditorial:\t\t" + this.editorial +
    "\nNúmero de copias:\t" + this.numCopias +
    "\nCopias disponibles:\t" + this.numCopiasDisponibles;
  }

  // MÉTODOS
  // AÑADIR
  public static void añadirLibro(ArrayList<Libro> lista){
    Tools.br();
    System.out.println("\t\tAÑADIR LIBRO");
    Tools.br();
    System.out.println("Introduce ISBN>");
    String isbn = input.nextLine();
    System.out.println("Introduce título>");
    String titulo = input.nextLine();
    System.out.println("Introduce autor>");
    String autor = input.nextLine();
    System.out.println("Introduce editorial>");
    String editorial = input.nextLine();
    boolean ok = false;
    String copias;
    do{
      System.out.println("Introduce número de copias>");
      copias = input.nextLine();
      if(Tools.comprobarNum(copias) && copias.length()>0){
        int numCopias = Integer.parseInt(copias);
        if(numCopias < 1){
          System.out.println("[-] El número de copias no puede ser inferior a 1");
        } else {
          Libro libro = new Libro(isbn, titulo, autor, editorial, numCopias);
          lista.add(libro);
          ok = true;
          Tools.br();
          System.out.println("\n[+] Libro añadido\n");
          System.out.println(libro + "\n");
          Tools.br();
          Tools.continuar();
        }
      } else {
        Tools.br();
        System.out.println("\n[!] Por favor, introduce un valor numérico\n");
        Tools.br();
      }
    }while(!ok);
  }

  // ELIMINAR
  public static void eliminarLibro(ArrayList<Libro> lista){
    Tools.br();
    System.out.println("\t\tELIMINAR LIBRO");
    Tools.br();
    int posicion = buscarIsbn(lista);
    if(posicion != -1  && lista.get(posicion).getNumCopiasDisponibles() == lista.get(posicion).getNumCopias()){
      System.out.println("Está a punto de eliminar el libro " + lista.get(posicion).getTitulo());
      if(Tools.confirmar()){
        lista.remove(lista.get(posicion));
        Tools.br();
        System.out.println("\n[+] Libro eliminado");
      } else {
        Tools.br();
        System.out.println("[-] Operación cancelada");
      }
      Tools.continuar();
    } else if (posicion != -1  && lista.get(posicion).getNumCopiasDisponibles() != lista.get(posicion).getNumCopias()){
      Tools.br();
      System.out.println("[-] El libro no se puede eliminar porque tiene reservas");
      Tools.br();
      Tools.continuar();
    }
  }

  // BUSCAR ISBN
  public static int buscarIsbn(ArrayList<Libro> lista){
    Tools.br();
    System.out.println("\t\tBUSCAR LIBRO POR ISBN");
    Tools.br();
    int posicion = -1;
    String isbn = new String();
    System.out.println("Introduce el ISBN que quiere buscar >");
    isbn = input.nextLine();
    for(int i = 0; i < lista.size(); i ++){
      if(isbn.equals(lista.get(i).getIsbn())){
        posicion = i;
        Tools.br();
        System.out.println("\n[+] Libro encontrado:\n\n" + lista.get(i) + "\n");
        Tools.br();
        i = lista.size();
        Tools.continuar();
      }
    }
    return posicion;
  }

  // BUSCAR TITULO
  public static void buscarTitulo(ArrayList<Libro> lista){
    Tools.br();
    System.out.println("\t\tBUSCAR LIBRO POR TÍTULO");
    Tools.br();
    String titulo = new String();
    System.out.println("Introduce el título que quiere buscar >");
    titulo = input.nextLine();
    for(int i = 0; i < lista.size(); i ++){
      if(titulo.equals(lista.get(i).getTitulo())){
        Tools.br();
        System.out.println("\n[+] Libro encontrado:\n\n" + lista.get(i) + "\n");
        Tools.br();
        i = lista.size();
      } else if(!titulo.equals(lista.get(i).getTitulo()) && i == lista.size() - 1){
        Tools.br();
        System.out.println("\n[-] Lo sentimos, el libro no está entre nuestros títulos\n");
        Tools.br();
      }
    }
    Tools.continuar();
  }
}
