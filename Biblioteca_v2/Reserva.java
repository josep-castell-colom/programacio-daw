package Biblioteca_v2;
import java.util.Date;

public class Reserva {
  private Libro libroReservado;
  private Date fechaReserva;

  public Reserva(){}

  public Reserva(Libro libroReservado, Date fechaReserva) {
    this.libroReservado = libroReservado;
    this.fechaReserva = fechaReserva;
  }

  public Reserva(Reserva reserva){
    this.libroReservado = reserva.getLibroReservado();
    this.fechaReserva = reserva.getFechaReserva();
  }

  public Libro getLibroReservado() {
    return libroReservado;
  }

  public void setLibroReservado(Libro libroReservado) {
    this.libroReservado = libroReservado;
  }

  public Date getFechaReserva() {
    return fechaReserva;
  }

  public void setFechaReserva(Date fechaReserva) {
    this.fechaReserva = fechaReserva;
  }
  
  @Override
  public String toString(){
    String info = 
    "Libro reservado:\n" + this.libroReservado + 
    "\nFecha de la reserva: " + this.fechaReserva;
    return info;
  }
  
}
