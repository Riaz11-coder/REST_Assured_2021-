package apiTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import testbase.sparta_TestBase;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SpartanDeserialization extends sparta_TestBase {

    @Test
    public void testJsonCollections(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/spartans/{id}");

        Map<String,Object> spartanMap = response.body().as(Map.class);

        System.out.println(spartanMap.get("id"));
        System.out.println(spartanMap.get("name"));

        assertEquals(spartanMap.get("id"),15);
        assertEquals(spartanMap.get("name"),"Meta");

    }

    @Test
    public void testAllSpartaCollections(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans");

        List<Map<String,Object>> allSparta = response.body().as(List.class);

        System.out.println("allSparta.get(0) = " + allSparta.get(0));

        Map<String,Object> firstSpartan = allSparta.get(0);

        System.out.println("firstSpartan = " + firstSpartan);

        int count = 1;

        for (Map<String,Object> listOfSpartans :allSparta) {

            System.out.println(+count+" "+ listOfSpartans);

            count++;
        }

        assertEquals(allSparta.get(0),firstSpartan);


    }


}
