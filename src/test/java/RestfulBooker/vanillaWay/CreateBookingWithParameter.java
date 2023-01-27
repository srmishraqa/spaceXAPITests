package RestfulBooker.vanillaWay;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateBookingWithParameter {

    public static void main(String[] args) {

        /*

        // 1. Build Request (1st set Base URL and then path)

        // 1.1 Create Request with Base URI + PATH
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification = requestSpecification.log().all(); // for logging
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking");

        // 1.2 Send Body Along with the request
        requestSpecification.body("{\n" +
                "    \"firstname\" : \"Jason\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 89,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2023-12-31\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}");

        // 1.3 Send Header along with the request and body
        //requestSpecification.contentType("application/json");
        requestSpecification.contentType(ContentType.JSON);

        // 2. Hit Request
        Response response = requestSpecification.post();

        // 3. Validate Result
        ValidatableResponse validatableResponse = response.then().log().all();
        // we can't validate Response object that's why we used then() which returned ValidatableResponse Object
        validatableResponse.statusCode(200);

         */

        //--------------------------------------------------------------------------------------------------------------
        //Shorter Version Of Code
        RestAssured.
                given().
                log().
                all().
                baseUri("https://restful-booker.herokuapp.com/").
                basePath("booking").
                contentType(ContentType.JSON).
                body("{\n" +
                        "    \"firstname\" : \"Matt\",\n" +
                        "    \"lastname\" : \"Huggins\",\n" +
                        "    \"totalprice\" : 99,\n" +
                        "    \"depositpaid\" : false,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2023-12-31\",\n" +
                        "        \"checkout\" : \"2024-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}").
                when().post().
                then().log().
                all().
                statusCode(200);
    }

}
