package TesteAPIQAOPS.teste;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import TesteAPIQAOPS.dominio.Usuario;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public class UsuarioAlternativoTest{
  private static final String BASE_URI = "https://reqres.in";
  private static final String BASE_PATH = "/api";

  private static final String LISTA_USUARIO_ENDPOINT = "/users";
  private static final String LISTA_USUARIO_ID_ENDPOINT = "/users/{userId}";
  private static final String CRIA_USUARIO_ENDPOINT = "/users";

  @BeforeClass
  public static void setup() {
   RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }
   
    @Test
    public void testCriarUsuario() {
      String uri = getUri(CRIA_USUARIO_ENDPOINT);

      Map<String, String> user = new HashMap<>();
      user.put("name", "rafael");
      user.put("job", "eng test");

      given().
          contentType(ContentType.JSON).
          body(user).
      when().
          post(uri).
      then().
          contentType(ContentType.JSON).
          statusCode(HttpStatus.SC_CREATED).
          body("name", is("rafael"));
  }

    @Test
    public void testListaUsuario() {
        String uri = getUri(LISTA_USUARIO_ENDPOINT);

        given().
            param("page","2").
        when().
            get(uri).
        then().
            contentType(ContentType.JSON).
            statusCode(HttpStatus.SC_OK).
            body("page", is(2)).
            body("data", is(notNullValue()));
    }

    @Test
    public void testListarUsuarioPorId() {
    String uri = getUri(LISTA_USUARIO_ID_ENDPOINT);
    Usuario usuario = given()
        .pathParam("userId", 2).
      when()
        .get(uri).
      then()
        .contentType(ContentType.JSON)
        .statusCode(HttpStatus.SC_OK).
      extract()
        .body().jsonPath().getObject("data",Usuario.class);

      assertTrue(usuario.getEmail().contains("@reqres.in"));
      assertTrue(usuario.getName().contains("Janet"));
      assertTrue(usuario.getLast_name().contains("Weaver"));
    }

    @Test
    public void testListaMetadadosDoUsuario() {
      String uri = getUri(LISTA_USUARIO_ENDPOINT);
      int expectPage = 2;
      int extractItensPerPage = getExtractItensPerPage(expectPage);

      given()
        .params("page", expectPage).
      when()
        .get(uri).
      then()
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON)
        .body(
          "page", is(expectPage),
          "data", is(notNullValue()),
          "data.size",is(extractItensPerPage),
          "data.findAll {it.avatar.startsWith('https://reqres.in/img/faces/')}.size()",is(6)
        );
    }

    private int getExtractItensPerPage(int page) {
      String uri = getUri(LISTA_USUARIO_ENDPOINT);
      
      return given().
                  param("page", page).
              when().
                  get(uri).
              then().
                  contentType(ContentType.JSON).
                  statusCode(HttpStatus.SC_OK).
              extract().
                  path("per_page");
    }

    private String getUri(String endpoint) {
      return BASE_URI + BASE_PATH + endpoint;
  }

}
