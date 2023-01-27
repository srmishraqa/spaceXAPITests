package RestfulBooker.vanillaWay;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

// Put call will have the same payload as post
// So Update the current payload with new payload if the resource is found
// Or else resource not found
public class PUTCall {
    static String token;
    static Response response;
    static ValidatableResponse validatableResponse;
    static JsonPath jsonPath;

    public static void main(String[] args) {

        // this block will generate the token
        response = RestAssured.given()
                .log().all()
                .pathParam("path", "auth")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .when().post("https://restful-booker.herokuapp.com/{path}");

        response.then().log().all();
        jsonPath = new JsonPath(response.asString());
        token = jsonPath.getString("token");
        System.out.println(token);

        // Call For POST

        response = RestAssured.given()
                .log().all()
                .pathParam("path", "booking")
                .pathParam("bookingID", 2)
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body("{\n" +
                        "    \"firstname\" : \"Matt\",\n" +
                        "    \"lastname\" : \"Huggins\",\n" +
                        "    \"totalprice\" : 99,\n" +
                        "    \"depositpaid\" : false,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2023-12-31\",\n" +
                        "        \"checkout\" : \"2024-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .when().put("https://restful-booker.herokuapp.com/{path}/{bookingID}");

        response.then().log().all().statusCode(200);
        System.out.println(response.asPrettyString());
    }
}

