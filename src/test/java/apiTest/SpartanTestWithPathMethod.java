package apiTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import testbase.sparta_TestBase;

import static io.restassured.RestAssured.*;

import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestWithPathMethod extends sparta_TestBase {

    @Test
    public void testingWithPath(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id","1")
                .when().get("/spartans/{id}");

        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json");

        System.out.println("response.body().path(\"id\").toString() = " + response.body().path("id").toString());
        System.out.println("response.body().path(\"name\").toString() = " + response.body().path("name").toString());
        System.out.println("response.body().path(\"gender\").toString() = " + response.body().path("gender").toString());
        System.out.println("response.body().path(\"phone\").toString() = " + response.body().path("phone").toString());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(id,1);
        assertEquals(name,"Meade");
        assertEquals(gender,"Male");
        assertEquals(phone,3584128232l);
    }
}
