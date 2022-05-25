package TesteAPIQAOPS.teste;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import TesteAPIQAOPS.dominio.Usuario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RegisterTest {
 @BeforeClass
 public static void setup() {
  RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  baseURI = "https://reqres.in";
  basePath = "/api";
 }

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
