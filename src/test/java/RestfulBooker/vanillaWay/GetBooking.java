package RestfulBooker.vanillaWay;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetBooking {
    public static void main(String[] args) {
        RestAssured.given().log().all()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("booking/{id}")
                .pathParam("id", 1)
                .contentType(ContentType.JSON)
                .when().get()
                .then().log().all()
                .statusCode(200);

        //using Parametrization using named parameter
        RestAssured.given().log().all()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("{basePath}/{id}")
                .pathParam("id", 2)
                .pathParam("basePath","booking")
                .contentType(ContentType.JSON)
                .when().get()
                .then().log().all()
                .statusCode(200);

        //using un named parameter
        //Based on index it will set
        RestAssured.given().log().all()
                .when().get("https://restful-booker.herokuapp.com/{basePath}/{id}","booking",2)
                .then().log().all().statusCode(200);
    }
}
