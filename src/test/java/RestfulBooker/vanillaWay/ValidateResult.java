package RestfulBooker.vanillaWay;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class ValidateResult {
    public static void main(String[] args) {
        ValidatableResponse response = RestAssured.given().log().all()
                .pathParam("bookingID", 2)
                .pathParam("path", "booking")
                .contentType(ContentType.JSON)
                .when().get("https://restful-booker.herokuapp.com/{path}/{bookingID}")
                .then().log().all();

        //String responseBody = response.extract().body().asString(); // as normal string
        String responseBody = response.extract().body().asPrettyString(); // in prettier format
        System.out.println("--------------------------------------");
        System.out.println(responseBody);
    }
}

