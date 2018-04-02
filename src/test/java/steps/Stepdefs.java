package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import groovy.json.JsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.runner.Request;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

/**
 * Created on 02.04.2018.
 */
public class Stepdefs {
    RequestSpecification request = RestAssured.given();


    @Rule
    private List<Customer> customers;

//    RequestSpecification request;


    @Given("^mocked environment$")
    public void mockedEnvironment() {


//        request.given()
//                .contentType(ContentType.JSON)
//                .baseUri("http://localhost:8080/rest/api");
//                .basePath("/customer");

    }

    @When("^Server is receiving request to createCustomer$")
    public void serverIsReceivingRequestToCreateCustomer(DataTable arg1) throws Throwable {
//        RestAssured.basePath=";
        request.baseUri("http://localhost:8080/rest/api");

//        for (Customer customer : customers) {
//            List<Customer> tempCustomer = new ArrayList<>();
//            tempCustomer.add(customer);


//        JSONObject requestParams = new JSONObject();
//        requestParams.put("id", 1);
//        requestParams.put("firstName", "first"/*Customer.firstName*/);
//        requestParams.put("lastName", "last" /*customer.lastName*/);
//        requestParams.put("age", 1/*customer.age*/);
////            requestParams.put("Password", "password1");
////            requestParams.put("Email",  "someuser@gmail.com");

            String value = new JsonBuilder()
                    .add("id", "" + customer.id + "")
                    .add("first_name", "" + customer.firstName + "")
                    .add("last_name", "" + customer.lastName + "")

                    .add("properties", new JsonBuilder()
                            .add("age", "" + customer.age + "")
                            .add("active", "" + customer.isActive + "")
                            .add("date_of_birth", "" + customer.dateOfBirth + ""))
                    .toJson();
            request.header("Content-Type", "application/json");

            request.body(value);
        System.out.println("zzzzzzz: " + request);
    }


//        }



    @Then("^service is returning response with code (\\d+) and status message \"([^\"]*)\"$")
    public void serviceIsReturningResponseWithCodeAndStatusMessage(int arg1, String arg2) throws Throwable {
        Response response = request.post("/customer");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(201, statusCode);
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals("Correct Success code was returned", "OPERATION_SUCCESS", successCode);
    }

    @Given("^customers are available$")
    public void customersAreAvailable(List<Customer> customers) throws Throwable {
        this.customers = customers;
        for (Customer customer : customers) {
            String value = new JsonBuilder()
                    .add("id", "" + customer.id + "")
                    .add("first_name", "" + customer.firstName + "")
                    .add("last_name", "" + customer.lastName + "")

                    .add("properties", new JsonBuilder()
                            .add("age", "" + customer.age + "")
                            .add("active", "" + customer.isActive + "")
                            .add("date_of_birth", "" + customer.dateOfBirth + ""))
                    .toJson();
            request.header("Content-Type", "application/json");

            request.body(value);
            Response response = request.post("/customer");


        }
    }


    //    @Given("^a default customer with id (\\d+)$")
//    public void aCustomerExistsWithId(Integer id) throws Throwable {
//        mockServerClient
//                .when(HttpRequest.request("/rest/api/customer/" + id).withMethod("GET"))
//                .respond(HttpResponse.response().withBody(mapper.writeValueAsString(mockGetCorrect(id))).withStatusCode(200));
//
//        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
//
//        request.baseUri("http://localhost:8080/rest/api");
//
//
//
//            String value = new JsonBuilder()
//                    .add("id", ""+table.get(0).get("Parameter")+"")
//                    .add("first_name", ""+table.get(1).get("Parameter")+"")
//                    .add("last_name", ""+table.get(2).get("Parameter")+"")
//
//                    .add("properties", new JsonBuilder()
//                            .add("age", ""+table.get(3).get("Parameter")+"")
//                            .add("active", ""+table.get(4).get("Parameter")+"")
//                            .add("date_of_birth", ""+table.get(5).get("Parameter")+""))
//                    .toJson();
//            request.header("Content-Type", "application/json");
//
//            request.body(value);
//        Response response = request.post("/customer");
//
//
//    }
    @When("^a user retrieves the user by id (\\d+)$")
    public void aUserRetrievesTheUserById(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the status code is (\\d+)$")
    public void theStatusCodeIs(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^response customer includes$")
    public void responseCustomerIncludes(DataTable arg1) throws Throwable {
        request.baseUri("http://localhost:8080/rest/api");

    }

    @Then("^response customer properties includes$")
    public void responseCustomerPropertiesIncludes(DataTable arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        throw new PendingException();
    }


    public class Customer {
        public int id;
        public String firstName;
        public String lastName;
        public Object age;
        public Object isActive;
        public String dateOfBirth;


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
        public final com.google.gson.JsonObject json = new com.google.gson.JsonObject();

        public String toJson() {
            return json.toString();
        }

        public JsonBuilder add(String key, String value) {
            json.addProperty(key, value);
            return this;
        }

        public JsonBuilder add(String key, JsonBuilder value) {
            json.add(key, value.json);
            return this;
        }
    }
}
