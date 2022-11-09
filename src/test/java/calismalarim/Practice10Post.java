package calismalarim;

import base_url.GMIBankBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.Assert;
import org.junit.Test;

import pojos.GMICountryDataPostPojo;
import utilities.Authentication;

import static io.restassured.RestAssured.given;
import static utilities.Authentication.generateToken;


public class Practice10Post extends GMIBankBaseUrl {
    //yeni bir ulke ekleyin
    @Test
    public void test10() {
        spec.pathParam("first", "tp-countries");

        GMICountryDataPostPojo countryPost = new GMICountryDataPostPojo("MuzCumhuriyeti");


        Response response = given().contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + generateToken())
                .spec(spec).when().body(countryPost)
                .post("/{first}");

        GMICountryDataPostPojo actualData=response.as(GMICountryDataPostPojo.class);

        Assert.assertEquals(countryPost.getName(), actualData.getName());


    }
}
