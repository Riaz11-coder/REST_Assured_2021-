package testbase;

import Utility.ConfigurationReader;
import Utility.DB_Utility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class sparta_TestBase {
    @BeforeAll
    public static void setUp(){

        baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        basePath = ConfigurationReader.getProperty("spartan1.base_path");
        // create DB Connection here
        DB_Utility.createConnection(ConfigurationReader.getProperty("spartan1.database.url"),
                ConfigurationReader.getProperty("spartan1.database.username"),
                ConfigurationReader.getProperty("spartan1.database.password")
        );

    }

    @AfterAll
    public static void tearDown(){
        reset();
        // destroy DB Connection here
        DB_Utility.destroy();
    }


}
