package RestfulBooker.vanillaWay;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PassingHeaders {
    @Test//using header()
    public void test1() {
        RestAssured.given().log().all()
                .baseUri("https://localhost:8080").header("Content-Type", "application/json")
                .header("testHeader", "firstValue", "secondValue", "thirdValue")
                .when().get()
                .then().log().all();
    }

    @Test//using headers()
    public void test2() {
        RestAssured.given().log().all()
                .baseUri("https://localhost:8080").
                headers("Content-Type", "application/json",
                        "testHeader", "firstValue",
                        "testHeaderSecond", "secondValue",
                        "testHeaderSecond", "thirdValue")
                .when().get()
                .then().log().all();
    }

    @Test//using Map<>
    public void test3() {
        Map<String, String> headerMap = new LinkedHashMap<>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("testHeader", "firstValue");
        headerMap.put("testHeaderSecond", "secondValue");
        headerMap.put("testHeaderThird", "thirdValue");
        RestAssured.given().log().all()
                .baseUri("https://localhost:8080")
                .headers(headerMap)
                .when().get()
                .then().log().all();
    }
}
