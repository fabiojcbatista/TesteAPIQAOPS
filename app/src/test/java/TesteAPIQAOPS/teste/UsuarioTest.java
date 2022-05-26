package TesteAPIQAOPS.teste;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Test;

import TesteAPIQAOPS.dominio.Usuario;

public class UsuarioTest extends BaseTest{

  private static final String LISTA_USUARIO_ENDPOINT = "/users";
  private static final String LISTA_USUARIO_ID_ENDPOINT = "/users/{userId}";
  private static final String CRIA_USUARIO_ENDPOINT = "/users";

   
    @Test
    public void testCriarUsuario() {
     // Usuario usuario = new Usuario("Fabio", "Testador de Software", "fabiojcbweb@gmail.com");
      Map<String,String> usuario = new HashMap<>();
      usuario.put("name", "Fabio");
      usuario.put("job", "Testador de Software");
      
      given()
        .body(usuario).
      when()
        .post(CRIA_USUARIO_ENDPOINT).
      then()
        .statusCode(HttpStatus.SC_CREATED)
        .body("name", is("Fabio"));
    }

    @Test
    public void testListarUsuarioPorId() {
     Usuario usuario = given()
        .pathParam("userId", 2).
      when()
        .get(LISTA_USUARIO_ID_ENDPOINT).
      then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .body().jsonPath().getObject("data",Usuario.class);

      assertTrue(usuario.getEmail().contains("@reqres.in"));
      assertTrue(usuario.getName().contains("Janet"));
      assertTrue(usuario.getLast_name().contains("Weaver"));
    }

    @Test
    public void testListaMetadadosDoUsuario() {
      int expectPage = 2;
      int extractItensPerPage = getExtractItensPerPage(expectPage);

      given()
        .params("page", expectPage).
      when()
        .get(LISTA_USUARIO_ENDPOINT).
      then()
        .statusCode(HttpStatus.SC_OK)
        .body(
          "page", is(expectPage),
          "data", is(notNullValue()),
          "data.size",is(extractItensPerPage),
          "data.findAll {it.avatar.startsWith('https://reqres.in/img/faces/')}.size()",is(6)
        );
    }

    private int getExtractItensPerPage(int page) {
      int extractItensPerPage = given().
        param("page", page).
      when()
        .get(LISTA_USUARIO_ENDPOINT).
      then()
        .statusCode(HttpStatus.SC_OK)
        .extract()
        .path("per_page");
      return extractItensPerPage;
    }

}
