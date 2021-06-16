package apiTest;

import POJO.Spartan;
import Utility.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static POJO.Spartan.createRandomSpartanObject;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class spartanTest1 {

    static RequestSpecification validPostRequestSpec;
    static ResponseSpecification validPostResponseSpec ;
    static Spartan randomSp = createRandomSpartanObject();

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

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


    }

    @Test
    public void test(){


        given()
                .spec(validPostRequestSpec).

                when()

                .post("/spartans").
                then()
                .spec(validPostResponseSpec);

    }

}
