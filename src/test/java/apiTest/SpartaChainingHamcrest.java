package apiTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import testbase.sparta_TestBase;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartaChainingHamcrest extends sparta_TestBase {


    @Test
    public void spartanHamcrestTest(){

         given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/spartans/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id", Matchers.equalTo(15),
                        "name",Matchers.equalTo("Meta"),"gender",Matchers.equalTo("Female"),
                        "phone",Matchers.equalTo(1938695106));





    }
}
