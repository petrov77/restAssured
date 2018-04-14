package RestServicesTests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestAssuredPostManCalls {
    private static RequestSpecification requestSpecification;
    static String baseUrl = "https://postman-echo.com/";
    private static Response response;

    public RestAssuredPostManCalls(){
        requestSpecification = given().contentType("application/json").baseUri(baseUrl);
    }
    protected void testGetCall() {
        response = given().spec(requestSpecification).get("time/now");
        response.prettyPrint();
    }

    protected void testPostCall() {
        PostmanParams postmanParams = new PostmanParams();
        postmanParams.data.put("slagam KEY", "VALUE slagam malko tuka taka az");
        postmanParams.storage = "slagam malko STRING";
        response = given().spec(requestSpecification).body(postmanParams).post("post");
        response.prettyPrint();
    }
}
