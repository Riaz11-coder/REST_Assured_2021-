package apiTest;

import POJO.Spartan2;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import testbase.sparta_TestBase;
import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.given;

public class SpartaPOJOTest extends sparta_TestBase {

    @Test
    public void testingSpartaPOJO(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/spartans/{id}");

        Spartan2 sp = response.body().as(Spartan2.class);

        assertEquals(sp.getId(),15);
        assertEquals(sp.getName(),"Meta");
        assertEquals(sp.getGender(),"Female");
        assertEquals(sp.getPhone(),new Long (1938695106l));


    }

    @Test
    public void spartanGSONTest(){

        Gson gson = new Gson();

        String spartan = " {\"id\": 15,\n" +
                "        \"name\": \"Meta\",\n" +
                "        \"gender\": \"Female\",\n" +
                "        \"phone\": 1938695106}";

        // Converting from Json using Gson to POJO Object
        Spartan2 spartan2 = gson.fromJson(spartan,Spartan2.class);

        System.out.println(spartan2.toString());


        // Converting to Json from using Gson from POJO Object
        Spartan2 newSpartanOne = new Spartan2(101,"Rey","Female",9174596485l);

        String jsonbody = gson.toJson(newSpartanOne);

        System.out.println(jsonbody);

    }
}
