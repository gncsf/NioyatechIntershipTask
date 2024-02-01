package requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;
import baseURL.CatFacts;
import utilities.JsonToJava;

import java.util.HashMap;

public class GetFact extends CatFacts  {

    @Test
    public void getFacts() {
        PropertyConfigurator.configure("log4j.properties");

        Logger logger = Logger.getLogger(GetFact.class);

        logger.info("Test  started");
        specification.pathParams("path1","fact","path2","max_length=30");

        logger.info("Response result printed");
        Response response = RestAssured.given().spec(specification).when().get("/{path1}?{path2}");
        response.prettyPrint();

        HashMap actualMap = JsonToJava.convertJsonToJavaObject(response.asString(),HashMap.class);
        int actualLength = (int) actualMap.get("length");

        logger.info("Assertions have been made");
        Assert.assertTrue(actualLength <= 30, "Length is not less than 30");
        logger.info("Test finished");

    }
}
