package com.spacex.tests;

import com.spacex.base.TestBase;
import com.spacex.util.ExcelConnector;

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

    // Raw Method to create data provider
    @DataProvider(name = "TestData")
    public Object[][] testData() {
        Object[][] obj = new Object[][] {
                { "vafb_slc_4e" }, { "ksc_lc_39a" }, { "vafb_slc_3w" }, { "ccafs_slc_40" }
        };
        return obj;
    }

    // Test raw data provider using a test block
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

    // Run Data Driven Tests using data provider which returns data from excel
    @Test(dataProvider = "excelData")
    public void testThroughDataProviderMethod(String flightID) {
        TestBase.init();
        RestAssured.baseURI = prop.getProperty("secondBaseUrl");
        req = RestAssured.given();
        res = req.request(Method.GET, prop.getProperty("flight") + flightID);
        Assert.assertEquals(res.getStatusCode(), 200);
        jsonPath = new JsonPath(res.asString());
        System.out.println(res.getBody().asPrettyString());
        Assert.assertEquals(jsonPath.get("site_id"), flightID);
    }

    // Read data from Excel and return it as data provider
    @DataProvider(name = "excelData")
    public Object[][] getData() {
        ExcelConnector excelConnector = new ExcelConnector(System.getProperty("user.dir")
                + "/src/main/resources/testData/spacex_test_data.xlsx");
        return excelConnector.getExcelData(System.getProperty("user.dir")
                + "/src/main/resources/testData/spacex_test_data.xlsx", "flightData");
    }
}
