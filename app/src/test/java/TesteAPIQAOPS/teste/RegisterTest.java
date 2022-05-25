package TesteAPIQAOPS.teste;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.apache.http.HttpStatus;
import org.junit.Test;

import TesteAPIQAOPS.dominio.Usuario;
import io.restassured.http.ContentType;

public class RegisterTest extends BaseTest{
 
 @Test
 public void testNaoRegistrarSemPassword() {
  Usuario usuario = new Usuario();
  usuario.setEmail("f123@gmail.com");
  given()
    .contentType(ContentType.JSON)
    .body(usuario).when()
    .post("/register").then()
    .statusCode(HttpStatus.SC_BAD_REQUEST)
    .body("error", is("Missing password"));
 }

 @Test
 public void testRegistrar() {
  Usuario user = new Usuario("Luiz", "f123@gmail.co", "123");

  given()
    .contentType(ContentType.JSON)
    .body(user).when()
    .post("/register").then()
    .statusCode(HttpStatus.SC_OK);
 }

}
