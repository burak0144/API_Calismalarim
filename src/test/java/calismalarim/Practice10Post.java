package calismalarim;

import base_url.GMIBankBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import pojos.GMICountryDataPojo;
import pojos.GMICountryPostPojo;
import pojos.GMICustomerPojo;
import utilities.Authentication;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;



public class Practice10Post extends GMIBankBaseUrl {
   /*
    https://www.gmibank.com/api/tp-countries adrresine yeeni bir ülke ekelyin
    */

    @Test
    public void test10() {
        spec.pathParam("first", "tp-countries");

        GMICountryPostPojo countryPost = new GMICountryPostPojo("Muz Cumhuriyeti");


        Response response = given().contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + Authentication.generateToken())
                .spec(spec)
                .when()
                .body(countryPost)
                .post("/{first}");

        response.prettyPrint();

        GMICountryPostPojo actualData =response.as(GMICountryPostPojo.class);
        System.out.println("actualData = " + actualData);

       //Doğrulama Yaptık
        Assert.assertEquals(countryPost.getName(), actualData.getName());
    } }