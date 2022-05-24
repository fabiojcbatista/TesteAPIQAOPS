package TesteAPIQAOPS.teste;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UsuarioTest {
    @BeforeClass
    public static void setup(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "https://reqres.in";
        basePath = "/api";
    }

    @Test
    public void testListaMetadadosDoUsuario() {
        given()
          .params("page", 3).
        when()
          .get("/users").
        then()
          .statusCode(HttpStatus.SC_OK)
          .body("page", is(3))
          .body("data", is(notNullValue()));
    }

    @Test
    public void testCriarUsuario() {
        given()
          .contentType(ContentType.JSON)
          .body("{\"name\":\"Fabio\", \"job\":\"Testador de Software\"}").
        when()
          .post("/users").
        then()
          .statusCode(HttpStatus.SC_CREATED)
          .body("name", is("Fabio"));
    }
}
