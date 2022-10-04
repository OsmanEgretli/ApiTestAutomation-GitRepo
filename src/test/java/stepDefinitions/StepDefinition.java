package stepDefinitions;

import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.ReUsableMethods;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinition extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    public static Response response;
    TestDataBuilder data = new TestDataBuilder();
    String body;
    public static String repoLinkExtension;
    @Given("Repository payload creation with {string} {string} {string}")
    public void repo_payload_creation_with(String Name, String Description, String Private) throws IOException {
        boolean PrivateBool = Boolean.parseBoolean(Private);
        body = new Gson().toJson(data.createRepoPayload(Name, Description, PrivateBool));
        resspec = new ResponseSpecBuilder().build();
    }
    //@Given("Repository payload creation with {string} {string} {string}")
    //public void repository_payload_creation_with(String string, String string2, String string3) {
    //    // Write code here that turns the phrase above into concrete actions
    //    throw new io.cucumber.java.PendingException();

    @When("User calls {string} request with {string}")
    public void user_calls_an_http_request_with(String resource, String authType) throws IOException {
        APIResources resourceAPI = APIResources.valueOf(resource);
        if (resource.equalsIgnoreCase("CreateRepoAPI")) {
            res = given().spec(requestSpecification(authType)).body(body);
            response = res.when().post(resourceAPI.getResource());
        } else if (resource.equalsIgnoreCase("GetRepoAPI")) {
            //Here, created Repository's Url is https://api.github.com/repos/{username}/{reponame}
            // so we get that info from "full_name" variable of created repository response and parsed into repoLinkExtension variable
            repoLinkExtension = ReUsableMethods.rawToJson(response.asString()).get("full_name");
            res = given().spec(requestSpecification(authType));
            response = res.when().get(resourceAPI.getResource() + repoLinkExtension);
        } else if (resource.equalsIgnoreCase("DeleteRepoAPI")) {
            if (repoLinkExtension == null) {
                repoLinkExtension = ReUsableMethods.rawToJson(response.asString()).get("full_name");
            }
            res = given().spec(requestSpecification(authType));
            response = res.when().delete(resourceAPI.getResource() + repoLinkExtension);
        }
    }

    @Then("Verify api call got success with {int} status")
    public void api_call_got_success_with(int Status) {
        assertEquals(response.getStatusCode(), Status);

    }

    @Then("Verify Response body's {string} variable's value is {string}")
    public void response_have_correct_value(String keyValue, String expectedValue) {
        String enteredValue = ReUsableMethods.rawToJson(response.asString()).get(keyValue);
        assertEquals(enteredValue, expectedValue);
    }


}