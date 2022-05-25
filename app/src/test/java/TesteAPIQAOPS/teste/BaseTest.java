package TesteAPIQAOPS.teste;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class BaseTest {
 @BeforeClass
 public static void setup() {
  RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  baseURI = "https://reqres.in";
  basePath = "/api";
 }

}
