package TesteAPIQAOPS.teste;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.apache.http.HttpStatus;
import org.junit.Test;

import TesteAPIQAOPS.dominio.Usuario;

public class RegisterTest extends BaseTest{

private static final String REGISTER_USUARIO = "/register";
 
 @Test
 public void testNaoRegistrarSemPassword() {
  Usuario usuario = new Usuario();
  usuario.setEmail("f123@gmail.com");
  given()
    .body(usuario).
  when()
    .post(REGISTER_USUARIO).
  then()
    .statusCode(HttpStatus.SC_BAD_REQUEST)
    .body("error", is("Missing password"));
 }

 @Test
 public void testRegistrar() {
  Usuario user = new Usuario("Luiz", "f123@gmail.co", "123");

  given()
    .body(user).
  when()
    .post(REGISTER_USUARIO).
  then()
    .statusCode(HttpStatus.SC_OK);
 }

}
