package com.spacex.tests;

import com.google.gson.JsonObject;
import com.spacex.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestLatest extends TestBase {

    RequestSpecification httpRequest;
    Response response;
    JsonPath jsonPath;

    @BeforeMethod
    public void Setup() {
        TestBase.init();
        RestAssured.baseURI = prop.getProperty("baseUrl");
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, prop.getProperty("latest"));
    }

    @Test
    public void validateStatusCode() {
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(prop.getProperty("success")), "Response code is not matching as expected");

    }

    @Test
    public void validateStatusLine() {
        Assert.assertEquals(response.getStatusLine(), prop.getProperty("successStatusLine"));
    }

    @Test
    public void validateResponseTime() {
        Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS) < Long.parseLong(prop.getProperty("expectedTime")));
    }

    @Test
    public void validateResponseHeaders() {
        Assert.assertEquals(response.getHeader("Content-Type"), prop.getProperty("contentType"));
        Assert.assertEquals(response.getHeader("Connection"), prop.getProperty("connection"));
        Assert.assertEquals(response.getHeader("Server"), prop.getProperty("server"));
        Assert.assertEquals(response.getHeader("X-Content-Type-Options"), prop.getProperty("contentEncodingX-Content-Type-Options"));
        Assert.assertEquals(response.getHeader("Cache-Control"), prop.getProperty("cacheControl"));
    }

    @Test
    public void validateResponseBody() {
        jsonPath = new JsonPath(response.asString());
        Assert.assertEquals(jsonPath.get("links.patch.small"), prop.getProperty("small"));
        Assert.assertEquals(jsonPath.get("links.patch.large"), prop.getProperty("large"));
        Assert.assertEquals(jsonPath.get("links.reddit.media"), prop.getProperty("media"));
        Assert.assertEquals(jsonPath.get("links.webcast"), prop.getProperty("webCast"));
        Assert.assertEquals(jsonPath.get("fairings.ships[0]"), prop.getProperty("ships"));
        Assert.assertEquals(jsonPath.get("details"), prop.getProperty("details"));
        Assert.assertEquals(jsonPath.get("launchpad"), prop.getProperty("launchpad"));
        Assert.assertEquals(jsonPath.get("name"), prop.getProperty("name"));
        Assert.assertEquals(jsonPath.get("cores[0].core"), prop.getProperty("core"));
        Assert.assertEquals(jsonPath.get("cores[0].landpad"), prop.getProperty("landpad"));
        Assert.assertEquals(jsonPath.get("id"), prop.getProperty("id"));
    }
}
