package TesteAPIQAOPS.teste;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import TesteAPIQAOPS.dominio.Usuario;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;

public class RegisterTest extends BaseTest{

private static final String REGISTER_USUARIO = "/register";
private static final String LOGIN_USUARIO = "/login";

@BeforeClass
public static void setupRegister() {
 RestAssured.responseSpecification = new ResponseSpecBuilder()
 .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
 .build();
}
 
 @Test
 public void testNaoRegistrarSemPassword() {
  Usuario usuario = new Usuario();
  usuario.setEmail("f123@gmail.com");
  given()
    .body(usuario).
  when()
    .post(REGISTER_USUARIO).
  then()
    .body("error", is("Missing password"));
 }

 @Test
 public void testLogin() {
  Usuario usuario = new Usuario();
  usuario.setEmail("f123@gmail.com");
  given()
    .body(usuario).
  when()
    .post(LOGIN_USUARIO).
  then()
    .body("error", is("Missing password"));
 }
}
