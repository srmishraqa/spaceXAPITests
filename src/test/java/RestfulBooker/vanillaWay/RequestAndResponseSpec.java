package RestfulBooker.vanillaWay;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RequestAndResponseSpec {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    ValidatableResponse validatableResponse;

    @BeforeClass
    public void pre_req() {
        // Both the request Spec and Response spec in One block
        // This is called Request Spec and common code can ke kept here to reduce redundancy and can act as a pre-requisite
        requestSpecification = RestAssured.given().log().all()
                .baseUri("https://restful-booker.herokuapp.com/").basePath("booking/{id}")
                .pathParam("id", "2")
                .header("Content-Type", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=");

        // This is called Response Spec and common code can ke kept here to reduce redundancy and can act as a post-requisite
        responseSpecification = RestAssured.expect();
        responseSpecification.statusCode(200)
                .contentType(ContentType.JSON)
                .time(Matchers.lessThan(5000L));
    }

//    @BeforeClass
//    public void post_req_validation() {
//        // This is called Response Spec and common code can ke kept here to reduce redundancy and can act as a post-requisite
//        responseSpecification = RestAssured.expect();
//        responseSpecification.statusCode(200)
//                .contentType(ContentType.JSON)
//                .time(Matchers.lessThan(5000L));
//
//    }

    @Test
    public void getCall() {
        // Here it will get merged
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().get()
                .then().log().all();
        System.out.println("-----------------------");
        System.out.println(validatableResponse.extract().body().asPrettyString());
        // Here post-req Can be merged
        validatableResponse.spec(responseSpecification);
    }

    @Test
    public void testOnlyTwo() {
        Response r = RestAssured.given().spec(requestSpecification)
                .when().get();

        r.then().log().all()
                .spec(responseSpecification);

        // Time Operation always happens on Response Object (till when and excluded then)
        System.out.println(r.time()); // time in milliseconds by default and return type is Long
        System.out.println(r.timeIn(TimeUnit.SECONDS)); // time based on TimeUnit and return type is Long
        System.out.println(r.getTime()); // time in milliseconds by default and return type is Long
        System.out.println(r.getTimeIn(TimeUnit.SECONDS)); // time based on TimeUnit and return type is Long

        // Time Assertions
        r.then().time(Matchers.lessThan(2500L));
        r.then().time(Matchers.greaterThan(1000L));

        // Time Assertion in ranges
        r.then().time(Matchers.both(
                        Matchers.greaterThan(1500L))
                .and(
                        Matchers.lessThan(5000L)));
    }

    @Test
    public void putCall() {
        validatableResponse = RestAssured.given().spec(requestSpecification)
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
                .when().put()
                .then().log().all();
        System.out.println("-----------------------");
        System.out.println(validatableResponse.extract().body().asPrettyString());
        validatableResponse.spec(responseSpecification);
    }

}
