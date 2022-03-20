package Biblioteca_v2;

public class CustomException extends Exception {
  private int codigoError;

  public CustomException(int codigoError){
    super();
    this.codigoError = codigoError;
  }
  
  @Override
  public String getMessage(){
    String mensaje = "";
    switch(codigoError){
      case 1:
        mensaje = "usuario/bibliotecario no encontrado";
        break;
      case 2:
        mensaje = "ningún usuario/bibliotecario en la biblioteca";
        break;
    }return mensaje;
  }
}
