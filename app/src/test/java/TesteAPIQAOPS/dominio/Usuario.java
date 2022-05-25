package TesteAPIQAOPS.dominio;

public class Usuario {

 private String name;
 private String job;
 private String email;
 private String password;

 public Usuario(String name, String job, String email) {
  this.name = name;
  this.job = job;
  this.email = email;
 }

 public Usuario() {
 }

 public String getName() {
  return name;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public String getJob() {
  return job;
 }
}
