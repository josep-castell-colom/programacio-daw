# Library management system exercise

Exercice for programming topic in the 1st year of Web App Development course from CIFP Francesc de Borja Moll.

The exercice is made in Java to aquire the OOP concepts.

It consists in create several classes such as Book, User, Admin, Library... Each class has its own methods which are called from the main menu. The menu also includes an admin menu that needs a basic authentication to log in.

The exercise includes abstract classes and different relationships between classes.

```mermaid
classDiagram
class Persona{
  <<abstract>>
  - String nombre
  - String apellido1
  - String apellido2
  - int edad
  solicitarDatos()
  buscarPersona()
  eliminarPersona()
  validarAdmin()
}
class User{
  - String tel
  - String direccion
  - String codigoPostal
  - String email
  - ArrayList<Reserva> listaReservas
  solicitarDatos()
}
class Admin{
  - String puesto
  - String NIF
  - String contraseña
  solicitarDatos()
  reservarLibro()
  devolverLibro()
}
class Biblioteca{
  - String nombre
  - ArrayList<Libro> listaLibros
  - ArrayList<Persona> listaPersonas
  mostrarLibros()
  mostrarDisponibles()
  mostrarLibrosReservados()
  mostrarSinReservas()
  mostrarUsuarios()
  mostrarAdmins()
}
class Libro{
  - String ISBN
  - String titulo
  - String autor
  - String editorial
  - int numCopias
  - int numCopiasDisponibles
  añadirLibro()
  eliminarLibro()
  buscarIsbn()
  buscarTitulo()
}
class Reserva{
  - Libro libroReservado
  - Date fechaReserva
}
Persona <|-- User
Persona <|-- Admin
Biblioteca *-- Libro
Biblioteca *-- Admin
Biblioteca *-- User
User o-- Reserva
Reserva *-- Libro
```
