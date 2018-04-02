package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;


public class Steps {
    RequestSpecification request = RestAssured.given();

    @When("^Server is receiving request to createCustomer$")
    public void serverIsReceivingRequestToCreateCustomer(DataTable arg1) throws Throwable {
        request.baseUri("http://localhost:8080/rest/api");

        String value = new JsonBuilder()
                .add("id", "1")
                .add("first_name", "John")
                .add("last_name", "Smith")
//                .add("age", "25")
//                .add("address", new JsonBuilder()
//                        .add("streetAddress", "21 2nd Street")
//                        .add("city", "New York")
//                        .add("state", "NY")
//                        .add("postalCode", "10021"))
                .toJson();

        request.body(value);
        request.header("Content-Type", "application/json");

        Response response = request.post("/customer");

    }





    @Then("^service is returning response with code (\\d+) and status message \"([^\"]*)\"$")
    public void serviceIsReturningResponseWithCodeAndStatusMessage(int arg1, String arg2) throws Throwable {
        Response response = request.post("/customer");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(arg1, statusCode);
        String successCode = response.jsonPath().get("status");
        Assert.assertEquals("Correct Success code was returned", arg2, successCode);
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