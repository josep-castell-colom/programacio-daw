package Biblioteca_v2;

import java.util.ArrayList;

public class Pair {
  private Admin admin;
  private boolean valid;
  private ArrayList<User> listaUsers;
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
  public ArrayList<User> getListaUsers() {
    return this.listaUsers;
  }
  public void setListaUsers(ArrayList<User> listaUsers) {
    this.listaUsers = listaUsers;
  }
  public ArrayList<Admin> getListaAdmins() {
    return this.listaAdmins;
  }
  public void setListaAdmins(ArrayList<Admin> listaAdmins) {
    this.listaAdmins = listaAdmins;
  }

  
}
