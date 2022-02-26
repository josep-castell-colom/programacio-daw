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
    Tools.mensaje("titulo", "añadir libro", "");
    System.out.println("Introduce ISBN");
    String isbn = Tools.prompt();
    System.out.println("Introduce título");
    String titulo = Tools.prompt();
    System.out.println("Introduce autor");
    String autor = Tools.prompt();
    System.out.println("Introduce editorial");
    String editorial = Tools.prompt();
    boolean ok = false;
    String copias;
    do{
      System.out.println("Introduce número de copias");
      copias = Tools.prompt();
      if(Tools.comprobarNum(copias) && copias.length()>0){
        int numCopias = Integer.parseInt(copias);
        if(numCopias < 1){
          Tools.mensaje("alert", "el número de copias no puede ser inferior a 1", "continuar");
        } else {
          Libro libro = new Libro(isbn, titulo, autor, editorial, numCopias);
          lista.add(libro);
          ok = true;
          Tools.mensaje("pos", "libro añadido:\n" + libro, "continuar");
        }
      } else {
        Tools.mensaje("alert", "por favor, introduce un valor numérico", "continuar");
      }
    }while(!ok);
  }

  // ELIMINAR
  public static void eliminarLibro(ArrayList<Libro> lista){
    Tools.mensaje("titulo", "eliminar libro", "");
    int posicion = buscarIsbn(lista);
    if(posicion != -1  && lista.get(posicion).getNumCopiasDisponibles() == lista.get(posicion).getNumCopias()){
      System.out.println("Está a punto de eliminar el libro " + lista.get(posicion).getTitulo());
      if(Tools.confirmar()){
        lista.remove(lista.get(posicion));
        Tools.mensaje("pos", "libro eliminado", "");
      } else {
        Tools.mensaje("neg", "operación cancelada", "");
      }
      Tools.continuar();
    } else if (posicion != -1  && lista.get(posicion).getNumCopiasDisponibles() != lista.get(posicion).getNumCopias()){
      Tools.mensaje("neg", "el libro no se puede eliminar porque tiene reservas", "continuar");
    }
  }

  // BUSCAR ISBN
  public static int buscarIsbn(ArrayList<Libro> lista){
    Tools.mensaje("titulo", "buscar libro por isbn", "");
    int posicion = -1;
    String isbn = new String();
    System.out.println("Introduce el ISBN que quiere buscar >");
    isbn = input.nextLine();
    boolean encontrado = false;
    for(int i = 0; i < lista.size(); i ++){
      if(isbn.equals(lista.get(i).getIsbn())){
        posicion = i;
        Tools.mensaje("pos", "libro encontrado:\n\n" + lista.get(i), "");
        i = lista.size();
        encontrado = true;
      }
    }
    if(!encontrado){
      Tools.mensaje("neg", "libro no encontrado", "");
    }
    Tools.continuar();
    return posicion;
  }

  // BUSCAR TITULO
  public static void buscarTitulo(ArrayList<Libro> lista){
    Tools.mensaje("titulo", "buscar libro por título", "");
    String titulo = new String();
    System.out.println("Introduce el título que quiere buscar >");
    titulo = input.nextLine();
    for(int i = 0; i < lista.size(); i ++){
      if(titulo.equals(lista.get(i).getTitulo())){
        Tools.mensaje("pos", "Libro encontrado:\n\n" + lista.get(i), "");
        Tools.br();
        i = lista.size();
      } else if(!titulo.equals(lista.get(i).getTitulo()) && i == lista.size() - 1){
        Tools.mensaje("neg", "lo sentimos, el libro no está entre nuestros títulos");
      }
    }
    Tools.continuar();
  }
}
