package TesteAPIQAOPS;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.apache.http.HttpStatus;
import org.junit.Test;

import io.restassured.http.ContentType;

public class AppTest {
    @Test
    public void testListaMetadadosDoUsuario() {

        when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("data", is(notNullValue()));
        // .body("page", is(2))
        // .body("per_page", is(6))
        // .body("data[0].email", is("michael.lawson@reqres.in"))
        // .body("data[0].first_name", is("Michael"))

    }

    @Test
    public void testCriarUsuario() {
        given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"Fabio\", \"job\":\"Testador de Software\"}").when()
                .post("https://reqres.in/api/users").then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", is("Fabio"));

    }
}
