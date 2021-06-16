package apiTest;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import testbase.sparta_TestBase;

import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestWithPathParameters extends sparta_TestBase {


    @Test
    public void PathTest1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id",1)
                .when().get("/spartans/{id}");


        assertEquals(response.statusCode(),200);


        assertEquals(response.contentType(),"application/json");


        assertTrue(response.body().asString().contains("Meade"));

        response.body().prettyPrint();

    }

    @Test
    public void negativePathParamTest(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id",500)
                .when().get("/spartans/{id}");

        assertEquals(response.statusCode(),404);

        assertEquals(response.contentType(),"application/json");

        assertFalse(response.body().asString().contains("Spartan not Found"));

        response.body().prettyPrint();
    }


}
