package TesteAPIQAOPS;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AppTest {
    @BeforeClass
    public static void setup(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testListaMetadadosDoUsuario() {
        when()
          .get("https://reqres.in/api/users?page=2").
        then()
          .statusCode(HttpStatus.SC_OK)
          .body("data", is(notNullValue()));
    }

    @Test
    public void testCriarUsuario() {
        given()
          .contentType(ContentType.JSON)
          .body("{\"name\":\"Fabio\", \"job\":\"Testador de Software\"}").
        when()
          .post("https://reqres.in/api/users").
        then()
          .statusCode(HttpStatus.SC_CREATED)
          .body("name", is("Fabio"));
    }
}
