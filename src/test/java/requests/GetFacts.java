package requests;

import baseURL.CatFacts;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.JsonToJava;

import java.util.HashMap;

public class GetFacts extends CatFacts  {

    @Test
    public void getFacts() {
        PropertyConfigurator.configure("log4j.properties");

        Logger logger = Logger.getLogger(GetFacts.class);

        logger.info("Test  started");
        specification.pathParams("path1","facts","path2","max_length=1&limit=1");

        String expectedData = "{ \"current_page\": 1,\n" +
                "    \"data\": [\n" +
                "        \n" +
                "    ],\n" +
                "    \"first_page_url\": \"https://catfact.ninja/facts?page=1\",\n" +
                "    \"from\": null,\n" +
                "    \"last_page\": 1,\n" +
                "    \"last_page_url\": \"https://catfact.ninja/facts?page=1\"}" ;

        HashMap expectedMap = JsonToJava.convertJsonToJavaObject(expectedData, HashMap.class);
        logger.info("Response result printed");
        Response response = RestAssured.given().spec(specification).when().get("/{path1}?{path2}");
        response.prettyPrint();

        HashMap actualMap = JsonToJava.convertJsonToJavaObject(response.asString(),HashMap.class);

        logger.info("Assertions have been made");
        Assert.assertEquals(expectedMap.get("current_page"), actualMap.get("current_page"));
        Assert.assertEquals(expectedMap.get("data"), actualMap.get("data"));
        Assert.assertEquals(expectedMap.get("first_page_url"), actualMap.get("first_page_url"));
        Assert.assertEquals(expectedMap.get("last_page_url"), actualMap.get("last_page_url"));
        logger.info("Test finished");

    }
}
