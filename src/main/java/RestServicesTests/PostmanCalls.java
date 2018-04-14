package RestServicesTests;

import groovy.json.internal.Exceptions;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PostmanCalls {

    public static void main(String[] args) throws Exception {

        String baseUrl = "http://api.luftdaten.info";
        String endpointUser = "/v1/sensor/6191/";
        Response r = RestAssured.given().get(baseUrl + endpointUser);
        r.prettyPrint();

        ArrayList<ArrayList> spisuk = r.jsonPath().get("sensordatavalues");

        int i = 0;
        for (ArrayList a : spisuk) {
            Map map = (Map) a.get(i);
            String value = map.get("value").toString();
            String valueType = map.get("value_type").toString();
            System.out.println("Stoinostta na prahovite cahstici " + valueType + ": " + value);
            i++;

        }

//        ArrayList spisuk2 = spisuk.get(0);
//        Map map = (Map) spisuk.get(0).get(0);
//        map.get("name");
//        map.get("manufacturer")
//        map.get("country");


        RestAssuredPostManCalls restAssuredPostManCalls = new RestAssuredPostManCalls();

        restAssuredPostManCalls.testGetCall();
        restAssuredPostManCalls.testPostCall();

        OrdinaryPostmanCalls.executePostRequest(RestAssuredPostManCalls.baseUrl + "post");
        OrdinaryPostmanCalls.sendGet(RestAssuredPostManCalls.baseUrl + "time/now");
    }
}
