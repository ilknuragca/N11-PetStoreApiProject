package stepdefinitions.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.api.PetStore;
import utilities.ConfigReader;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class PetStoreCrudFunction {

    RequestSpecification spec=new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("baseurl")).build();
    Response response;
    PetStore expectedData;

    @Given("kullanici Post request icin gerekli pathprams ayarlarini yapar")
    public void kullaniciPostRequestIcinGerekliPathpramsAyarlariniYapar(){
        spec.pathParams("first","v2","second","pet");
    }

    @When("kullanici expectedData'ları belirler")
    public void kullaniciExpectedDataLarıBelirler() {
        expectedData=new PetStore(123456,"Bobbby", Arrays.asList("p1"),"available");
    }

    @Then("kullanici POST request gönderir gelen responsu alir ve ekrana yazdirir")
    public void kullaniciPOSTRequestGönderirGelenResponsuAlirVeEkranaYazdirir() {
        response=given().spec(spec).
                contentType(ContentType.JSON).
                body(expectedData).
                when().
                post("/{first}/{second}");

        response.prettyPrint();
    }

    @Then("kullanici gelen veriyi\\(responsu) dogrular")
    public void kullaniciGelenVeriyiResponsuDogrular() {
        response.then().statusCode(200);

        //map ile de-serialization validation
        Map<String , Object> actualData = response.as(HashMap.class);

        assertEquals(expectedData.id , actualData.get("id"));
        assertEquals(expectedData.name , actualData.get("name"));
        assertEquals(expectedData.photoUrls , actualData.get("photoUrls"));
        assertEquals("data does not match",expectedData.photoUrls , actualData.get("photoUrls"));

        System.out.println("POST ile actualname= " +actualData.get("name"));
        System.out.println("POST ile expectedname= "+expectedData.name);
    }


    @Given("kullanici Get request icin gerekli pathprams ayarlarini yapar")
    public void kullaniciGetRequestIcinGerekliPathpramsAyarlariniYapar() {
        spec.pathParams("first","v2","second","pet","third",123456);

 }

    @Then("kullanici Get request gönderir gelen responsu alir ve ekrana yazdirir")
    public void kullaniciGetRequestGönderirGelenResponsuAlirVeEkranaYazdirir() {
        response=given().spec(spec).contentType(ContentType.JSON).
                 when().get("/{first}/{second}/{third}");

        response.prettyPrint();
    }

    @Then("kullanici gelen responsu dogrular")
    public void kullaniciGelenResponsuDogrular() {
        //Hemchrest ile validation
        response.then().statusCode(200).
                contentType(ContentType.JSON).
                body("id", equalTo(123456)).
                body("name",equalTo("Bobbby")).
                body("status",equalTo("available"));

    }


    @Given("kullanici Put request icin gerekli pathprams ayarlarini yapar")
    public void kullaniciPutRequestIcinGerekliPathpramsAyarlariniYapar() {
        spec.pathParams("first","v2","second","pet");
    }

    @When("kullanici Put icin expectedData'ları belirler")
    public void kullaniciPutIcinExpectedDataLarıBelirler() {
        expectedData=new PetStore(123456,"Jonny", Arrays.asList("j1"),"available");
    }

    @Then("kullanici Put request gönderir gelen responsu alir ve ekrana yazdirir")
    public void kullaniciPutRequestGönderirGelenResponsuAlirVeEkranaYazdirir() {
        response=given().spec(spec).contentType(ContentType.JSON).
                 body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();
    }

    @Then("kullanici Put icin gelen responsu dogrular")
    public void kullaniciPutIcinGelenResponsuDogrular() {
         PetStore actualData=response.as(PetStore.class);
         response.then().statusCode(200);

         assertEquals(expectedData.id,actualData.id);
         assertEquals(expectedData.name,actualData.name);
         assertEquals(expectedData.status,actualData.status);

        System.out.println("PUT ile actualname= " +actualData.name);
        System.out.println("PUT ile expectedname= "+expectedData.name);
    }

    @Given("kullanici Delete request icin gerekli pathprams ayarlarini yapar")
    public void kullaniciDeleteRequestIcinGerekliPathpramsAyarlariniYapar() {
        spec.pathParams("first","v2","second","pet","third",123456);
    }

    @Then("kullanici Delete request gönderir gelen responsu alir ve ekrana yazdirir")
    public void kullaniciDeleteRequestGönderirGelenResponsuAlirVeEkranaYazdirir() {
        response=given().spec(spec).
                when().delete("/{first}/{second}/{third}");

        response.prettyPrint();
    }

    @Then("kullanici Delete icin gelen responsu dogrular")
    public void kullaniciDeleteIcinGelenResponsuDogrular() {
        response.then().statusCode(200);
    }
}
