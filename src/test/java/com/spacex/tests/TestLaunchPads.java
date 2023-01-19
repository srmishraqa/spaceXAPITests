package com.spacex.tests;

import com.spacex.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.spacex.base.TestBase.prop;

public class TestLaunchPads {
    RequestSpecification req;
    Response res;
    JsonPath jsonPath;

    @DataProvider(name = "TestData")
    public Object[][] testData() {
        Object[][] obj = new Object[][]{
                {"vafb_slc_4e"}, {"ksc_lc_39a"}, {"vafb_slc_3w"}, {"ccafs_slc_40"}
        };
        return obj;
    }

    @Test(dataProvider = "TestData")
    public void test(String param) {
        TestBase.init();
        RestAssured.baseURI = prop.getProperty("secondBaseUrl");
        req = RestAssured.given();
        res = req.request(Method.GET, prop.getProperty("flight") + param);
        Assert.assertEquals(res.getStatusCode(), 200);
        jsonPath = new JsonPath(res.asString());
        Assert.assertEquals(jsonPath.get("site_id"), param);
    }
}
