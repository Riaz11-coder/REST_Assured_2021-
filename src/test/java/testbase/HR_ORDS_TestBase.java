package testbase;

import Utility.ConfigurationReader;
import Utility.DB_Utility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class HR_ORDS_TestBase {

    @BeforeAll
    public static void setUp(){

        baseURI = ConfigurationReader.getProperty("ords.baseURL");
        basePath = ConfigurationReader.getProperty("ords.basePath");
        // create DB Connection here
        DB_Utility.createConnection(ConfigurationReader.getProperty("hr.database.url"),
                ConfigurationReader.getProperty("hr.database.username"),
                ConfigurationReader.getProperty("hr.database.password")
        );

    }

    @AfterAll
    public static void tearDown(){
        reset();
        // destroy DB Connection here
        DB_Utility.destroy();
    }



}

