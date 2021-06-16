package apiTest;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import testbase.sparta_TestBase;

public class SpartanJsonPathTest extends sparta_TestBase {

    @Test
    public void spartanJsonTest(){

       Response response = given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/spartans/{id}");

       assertEquals(response.statusCode(),200);

              JsonPath json =  response.body().jsonPath();


        int id = json.getInt("id");
        String name = json.getString("name");
        String gender = json.getString("gender");
        Long phone = json.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(id,15);
        assertEquals(name,"Meta");
        assertEquals(gender,"Female");
        assertEquals(phone,1938695106l);

        System.out.println(json.get().toString());

    }
}
