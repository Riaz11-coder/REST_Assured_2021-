package apiTest;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import testbase.sparta_TestBase;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestWithQueryParams extends sparta_TestBase {

    @Test
    public void queryParams(){

        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("gender","Female");
        paramsMap.put("nameContains","J");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)
                .when().get("/spartans/search");

        assertEquals(response.statusCode(),200);

        assertEquals(response.contentType(),"application/json");

        assertTrue(response.body().asString().contains("Female"));

        assertFalse(response.body().asString().contains("Male"));

        assertTrue(response.body().asString().contains("Janette"));

        response.body().prettyPrint();

    }
}
