package models;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

    public static JsonPath rawToJson(String response) {


        JsonPath parsedResponse = new JsonPath(response);
        return parsedResponse;
    }
}
