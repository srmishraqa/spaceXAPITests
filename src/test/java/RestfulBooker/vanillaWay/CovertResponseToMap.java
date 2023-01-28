package RestfulBooker.vanillaWay;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CovertResponseToMap {

    @Test
    public void convert() {
        Response response = RestAssured.given().log().all()
                .baseUri("https://restful-booker.herokuapp.com/").basePath("booking/2")
                .contentType(ContentType.JSON)
                .when().get();
        Map jsonResponseAsMap = response.as(Map.class); // converted to map

        // to print all the key and value
        jsonResponseAsMap.keySet().forEach(key -> System.out.println(key));
        jsonResponseAsMap.keySet().forEach(key -> System.out.println(jsonResponseAsMap.get(key)));

        // Nested Response
        Map<String, String> datesMap = (Map<String, String>) jsonResponseAsMap.get("bookingdates");
        System.out.println(datesMap.get("checkin"));
        System.out.println(datesMap.get("checkout"));

    }

}
