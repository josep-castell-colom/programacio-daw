package Biblioteca_v2;

public class Pair {
  private User user;
  private Admin admin;
  private boolean valid;

  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
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
}
