package apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import testbase.sparta_TestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpartanTests extends sparta_TestBase {


    @Test
    public void viewSpartanTest1(){

        Response response = RestAssured.get("/spartans");

        System.out.println(response.statusCode());

        response.body().prettyPrint();
    }

    @Test
    public void viewSpartanTest2(){

        Response response = RestAssured.get("/spartans");

        System.out.println(response.statusCode());
        assertEquals(response.statusCode(),200);

        System.out.println(response.body().prettyPrint());
        assertTrue(response.body().asString().contains("Allen"));
    }

    @Test
    public void viewSpartanTest3(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/spartans");

        System.out.println(response.statusCode());
       assertEquals(response.statusCode(),200);

        System.out.println(response.contentType());
        assertEquals(response.contentType(),"application/json");
    }

}
