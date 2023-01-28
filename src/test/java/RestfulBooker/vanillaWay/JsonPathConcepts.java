package RestfulBooker.vanillaWay;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

public class JsonPathConcepts {
    Response res;
    JsonPath jsonPath;

    @Test // for normal JSON
    public void assertAndValidate() {
        res = RestAssured.given().log().all()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("booking/{id}")
                .pathParam("id", "2")
                .header("Content-Type", "application/json")
                .when().get();
        System.out.println("-------------- Line Separator ------------------------");
        System.out.println(res);

        jsonPath = new JsonPath(res.body().asPrettyString());
        // we can use . operator but we can use [] for arrays including index inside it
        System.out.println("checkout Date : " + jsonPath.getString("bookingdates.checkout"));
        System.out.println("first Name : " + jsonPath.getString("firstname"));

        // for Object type as returned
        Object firstname = jsonPath.get("firstname");
        System.out.println(firstname);
    }

    @Test
    // Read Data from JSON File
    public void read() throws IOException, ParseException, org.json.simple.parser.ParseException {

        // Create a Object a File Reader Class and Pass the path of the file in ctc
        FileReader fileReader = new FileReader(
                System.getProperty("user.dir") + "/src/main/resources/testData/TestData.json");

        // Create a New Object For JSONParser Class and then use Parse()
        // Pass the fileReader Object Reference in parse()
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(fileReader); // returns Object

        // JSON Path and pass the Object in string format
        JsonPath jsonPath = new JsonPath(obj.toString());
        Object ob = jsonPath.get("data.actorData.Actors[0].name");
        System.out.println(ob);

    }
}
