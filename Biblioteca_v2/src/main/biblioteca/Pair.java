package biblioteca;

import java.util.ArrayList;

public class Pair {
  private Admin admin;
  private boolean valid;
  private ArrayList<User> listaUsuarios;
  private ArrayList<Admin> listaAdmins;

  public Admin getAdmin() {
    return admin;
  }
  public void setAdmin(Admin admin) {
    this.admin = admin;
  }
  public boolean getValid() {
    return valid;
  }
  public void setValid(boolean valid) {
    this.valid = valid;
  }

  public ArrayList<User> getListaUsuarios() {
    return listaUsuarios;
  }

  public void setListaUsuarios(ArrayList<User> listaUsuarios) {
    this.listaUsuarios = listaUsuarios;
  }

  public ArrayList<Admin> getListaAdmins() {
    return listaAdmins;
  }

  public void setListaAdmins(ArrayList<Admin> listaAdmins) {
    this.listaAdmins = listaAdmins;
  }
}
