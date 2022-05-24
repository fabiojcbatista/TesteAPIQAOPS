package TesteAPIQAOPS.dominio;

public class Usuario {

 private String name;
 private String job;

 public Usuario(String name, String job) {
  this.name = name;
  this.job = job;
 }

 public Usuario() {
 }

 public String getName() {
  return name;
 }

 public String getJob() {
  return job;
 }
}
