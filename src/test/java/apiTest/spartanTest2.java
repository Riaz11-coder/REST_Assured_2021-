package apiTest;

import POJO.Spartan;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;
import testbase.sparta_TestBase;

import static POJO.Spartan.createRandomSpartanObject;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class spartanTest2 extends sparta_TestBase {

    static RequestSpecification validPostRequestSpec;
    static ResponseSpecification validPostResponseSpec ;
    static Spartan randomSp = createRandomSpartanObject();

   @Test
   public void testSparta(){


       validPostRequestSpec =  given()
               .auth().basic("admin","admin")
               .accept(ContentType.JSON)
               .contentType(ContentType.JSON)
               .body(randomSp)
               .log().all();

       ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder();
       validPostResponseSpec = resSpecBuilder.expectStatusCode(201)

               .expectHeader("date",is(notNullValue(String.class)))
               .expectBody("data.name",is(randomSp.getName()))
               .expectBody("data.gender",is(randomSp.getGender()))
               .expectBody("data.phone",is(randomSp.getPhone()))
               .expectBody("data.id",is(notNullValue())).build();

       given()
               .spec(validPostRequestSpec).

               when()

               .post("/spartans").
               then()
               .spec(validPostResponseSpec);

   }
}
