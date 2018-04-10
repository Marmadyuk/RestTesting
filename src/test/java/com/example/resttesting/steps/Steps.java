package com.example.resttesting.steps;

import static org.junit.Assert.assertEquals;

import com.example.resttesting.Customer;
import com.example.resttesting.CustomerProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Rule;

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
        data.get(1).get(5));

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

  @When("^Server is receiving request to createCustomer with ([^\"]*),([^\"]*),([^\"]*),([^\"]*),([^\"]*) and ([^\"]*)$")
  public void serverIsReceivingRequestToCreateCustomerOutline(String id,String lastName,String firstName,String age,String isActive, String dateOfBirth) {
    request.baseUri("http://localhost:8080/rest/api");

    CustomerProperties customerProperties = new CustomerProperties(
        StringUtils.isNotBlank(age) ? Integer.parseInt(age) : null,
        Boolean.parseBoolean(isActive),
        dateOfBirth);

    com.example.resttesting.Customer customer = new com.example.resttesting.Customer(
        StringUtils.isNotBlank(id) ? Integer.parseInt(id) : null,
        lastName,
        firstName,
        customerProperties);

    Gson gson = new GsonBuilder().serializeNulls().create();
    String value = gson.toJson(customer);

    request.body(value);
    request.header("Content-Type", "application/json");

    Response response = request.post("/customer");
  }
  @Then("^service is returning response with code ([^\"]*) and Id ([^\"]*) status message ([^\"]*)$")
  public void serviceIsReturningResponseWithCodeAndStatusMessage(int arg1, String arg2,String message) {
    request.baseUri("http://localhost:8080/rest/api");
    Response response = request.post("/customer");
    int statusCode = response.getStatusCode();
    assertEquals(arg1, statusCode);
    String successCode = new JsonPath(response.getBody().asString()).get("status");
    Integer id = new JsonPath(response.getBody().asString()).get("id");

    System.out.println(successCode);
    Assert.assertEquals("Correct Success code was returned", message, successCode);
  }


  @Then("^service is returning response with code (\\d+) and status message \"([^\"]*)\"$")
  public void serviceIsReturningResponseWithCodeAndStatusMessage(int arg1, String arg2) {
    request.baseUri("http://localhost:8080/rest/api");
    Response response = request.post("/customer");
    int statusCode = response.getStatusCode();
    assertEquals(arg1, statusCode);
    String successCode = new JsonPath(response.getBody().asString()).get("status");
    Integer id = new JsonPath(response.getBody().asString()).get("id");

    System.out.println(successCode);
    Assert.assertEquals("Correct Success code was returned", arg2, successCode);
  }


  @When("a user retrieves the customer by id (\\d+)")
  public void aUserRetrievesTheUserById(Integer id) {
    request.baseUri("http://localhost:8080/rest/api");
    Response response = request.get("/customer/" + id);
  }

  @Then("^the status code is for customer with id (\\d+) returned the status (\\d+) and status message \"([^\"]*)\"$")
  public void verify_status_code(int id, int arg, String arg2) {
    request.baseUri("http://localhost:8080/rest/api");
    response = request.when().get("/customer/" + id);

    int statusCode = response.getStatusCode();
    assertEquals(arg, statusCode);
    String successCode = new JsonPath(response.getBody().asString()).get("status");
    System.out.println(successCode);
    Assert.assertEquals("Correct Success code was returned", StringUtils.isNotBlank(arg2) ? arg2 : null, StringUtils.isNotBlank(successCode) ? successCode : null);
  }

  @And("^returned full information of the customer with id (\\d+) and args$")
  public void returnedFullInformationOfTheCustomerWithId(int id, DataTable customerParameters) {
    List<List<String>> data = customerParameters.raw();

    request.baseUri("http://localhost:8080/rest/api");
    response = request.when().get("/customer/" + id);

    response.prettyPrint();
    Customer customer = new Gson().fromJson(response.getBody().asString(), Customer.class);
    CustomerProperties customerProperties = new Gson().fromJson(response.getBody().asString(), CustomerProperties.class);


    assertEquals(customer.id.toString(), data.get(1).get(0));
    assertEquals(customer.first_name, data.get(1).get(1));
    assertEquals(customer.last_name, data.get(1).get(2));
    assertEquals(customer.getProperties().getAge().toString(), data.get(1).get(3));
    assertEquals(customer.getProperties().isActive(), Boolean.parseBoolean(data.get(1).get(4)));
    assertEquals(customer.getProperties().getDateOfBirth(), data.get(1).get(5));
  }


  @Then("^service is returning response with code (\\d+) and Id (\\d+) status message \"([^\"]*)\"$")
  public void serviceIsReturningResponseWithCodeAndIdStatusMessage(int arg0, Integer arg1, String arg2) {
    request.baseUri("http://localhost:8080/rest/api");
    Response response = request.post("/customer");
    int statusCode = response.getStatusCode();
    assertEquals(arg0, statusCode);
    String successCode = new JsonPath(response.getBody().asString()).get("status");
    Integer id = new JsonPath(response.getBody().asString()).get("id");
    Assert.assertEquals("Correct Success code was returned", arg1, id);
    System.out.println(successCode);
    Assert.assertEquals("Correct Success code was returned", arg2, successCode);
  }



}
