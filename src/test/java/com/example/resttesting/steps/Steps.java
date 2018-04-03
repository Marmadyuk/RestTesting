package com.example.resttesting.steps;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static org.hamcrest.Matchers.equalTo;

import com.example.resttesting.CustomerProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Assert;
import org.junit.Rule;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Steps {

  @Rule
  ObjectMapper mapper = new ObjectMapper();
  private RequestSpecification request = RestAssured.given();
  private Response response;

  @When("^Server is receiving request to createCustomer$")
  public void serverIsReceivingRequestToCreateCustomer(DataTable customerParameters) {
    request.baseUri("http://localhost:8080/rest/api");
    List<List<String>> data = customerParameters.raw();


    CustomerProperties customerProperties = new CustomerProperties(
        StringUtils.isNotBlank(data.get(1).get(3)) ? Integer.parseInt(data.get(1).get(3)) : null,
        Boolean.parseBoolean(data.get(1).get(4)),
        StringUtils.isNotBlank(data.get(1).get(5)) ? LocalDate.parse(data.get(1).get(5), ISO_DATE) : null);

    com.example.resttesting.Customer customer = new com.example.resttesting.Customer(
        StringUtils.isNotBlank(data.get(1).get(0)) ? Integer.parseInt(data.get(1).get(0)) : null,
        data.get(1).get(1),
        data.get(1).get(2),
        customerProperties);

    Gson gson = new GsonBuilder().serializeNulls().create();
    String value = gson.toJson(customer);

    request.body(value);
    request.header("Content-Type", "application/json");

    Response response = request.post("/customer");

  }


  @Then("^service is returning response with code (\\d+) and status message \"([^\"]*)\"$")
  public void serviceIsReturningResponseWithCodeAndStatusMessage(int arg1, String arg2) {
    Response response = request.post("/customer");
    int statusCode = response.getStatusCode();
    Assert.assertEquals(arg1, statusCode);
//        String successCode = response.jsonPath().get("status");
//        Assert.assertEquals("Correct Success code was returned", arg2, successCode);
  }


  @When("a user retrieves the user by id (\\d+)")
  public void aUserRetrievesTheUserById(Integer id) {
    request.baseUri("http://localhost:8080/rest/api");
    Response response = request.get("/customer/" + id);

    request = given();


  }

  @Then("^the status code is for customer with id (\\d+) returned the status (\\d+)$")
  public void verify_status_code(int id, int arg) {
    request.baseUri("http://localhost:8080/rest/api");
    response = request.when().get("/customer/" + id);

    int statusCode = response.getStatusCode();
    Assert.assertEquals(arg, statusCode);

  }

  @And("^returned full information of the customer with id (\\d+)$")
  public void returnedFullInformationOfTheCustomerWithId(int id) {
    request.baseUri("http://localhost:8080/rest/api");
    response = request.when().get("/customer/" + id);

    int statusCode = response.getStatusCode();
    response.prettyPrint();
  }


  public class Customer {
    public int id;
    public String firstName;
    public String lastName;
    public Object age;
    public String dateOfBirth;
    Object isActive;


    public Customer(int id, String firstName, String lastName, int age, boolean active, String dateOfBirth) {
      id = id;
      firstName = firstName;
      lastName = lastName;
      age = age;
      isActive = active;
      dateOfBirth = dateOfBirth;
    }


  }

  class JsonBuilder {
    final com.google.gson.JsonObject json = new com.google.gson.JsonObject();

    String toJson() {
      return json.toString();
    }

    JsonBuilder add(String key, String value) {
      json.addProperty(key, value);
      return this;
    }

    JsonBuilder add(String key, JsonBuilder value) {
      json.add(key, value.json);
      return this;
    }
  }
}
