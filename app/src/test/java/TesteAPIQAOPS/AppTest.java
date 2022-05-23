package TesteAPIQAOPS;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.apache.http.HttpStatus;
import org.junit.Test;

public class AppTest {
    @Test
    public void testListaMetadadosDoUsuario() {

        when()
                .get("https://reqres.in/api/users?page=2")
               .then()
                .statusCode(HttpStatus.SC_OK)
                .body("data", is(notNullValue()))
                // .body("page", is(2))
                // .body("per_page", is(6))
                // .body("data[0].email", is("michael.lawson@reqres.in"))
                // .body("data[0].first_name", is("Michael"))
                .statusCode(200);
    }
}
