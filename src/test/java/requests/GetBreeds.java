package requests;

import baseURL.CatFacts;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.JsonToJava;

import java.util.HashMap;

public class GetBreeds extends CatFacts {

    @Test
    public void getBreeds() {
        PropertyConfigurator.configure("log4j.properties");

        Logger logger = Logger.getLogger(GetBreeds.class);

        logger.info("Test  started");
        specification.pathParams("path1","breeds","path2","limit=1");

        String expectedData = "{ \"current_page\": 1,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"breed\": \"Abyssinian\",\n" +
                "            \"country\": \"Ethiopia\",\n" +
                "            \"origin\": \"Natural/Standard\",\n" +
                "            \"coat\": \"Short\",\n" +
                "            \"pattern\": \"Ticked\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"first_page_url\": \"https://catfact.ninja/breeds?page=1\",\n" +
                "    \"from\": 1,\n" +
                "    \"last_page\": 98,\n" +
                "    \"last_page_url\": \"https://catfact.ninja/breeds?page=98\"}";


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
