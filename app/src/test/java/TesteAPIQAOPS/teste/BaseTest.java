package TesteAPIQAOPS.teste;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public class BaseTest {
 @BeforeClass
 public static void setup() {
  RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  baseURI = "https://reqres.in";
  basePath = "/api";

  RestAssured.requestSpecification = new RequestSpecBuilder()
  .setContentType(ContentType.JSON)
  .build();

  RestAssured.responseSpecification = new ResponseSpecBuilder()
  .expectContentType(ContentType.JSON)
  .build();

 }

}
