package calismalarim;

import base_url.GMIBankBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GMICountryDataPojo;
import pojos.GMICustomerPojo;
import pojos.GMIUsertDataPojo;
import utilities.Authentication;
import utils.ObjectMapperUtils;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.junit.Assert.assertEquals;

public class Practice09 extends GMIBankBaseUrl {
    /*
    {
        “id”: 114351,
        “firstName”: “Della”,
        “lastName”: “Heaney”,
        “middleInitial”: “Russell Homenick V”,
        “email”: “ricardo.larkin@yahoo.com”,
        “mobilePhoneNumber”: “123-456-7893”,
        “phoneNumber”: “213-456-7893”,
        “zipCode”: “53081”,
        “address”: “75164 McClure Stream”,
        “city”: “Gislasonburgh”,
        “ssn”: “823-25-7239”,
        “createDate”: “2021-12-05T23:00:00Z”,
        “zelleEnrolled”: true,
        “country”: {
            “id”: 3,
            “name”: “USA”,
            “states”: null
        },
        “state”: “New York43”,
        “user”: null,
        “accounts”: null
    },

     */

    @Test
    public void test09() {
        //Pojo ile cozum
        spec.pathParams("first","tp-customers","second",114351);

        GMICountryDataPojo countryDataPojo=new GMICountryDataPojo(3,"USA",null);
        GMICustomerPojo expectedData=new GMICustomerPojo("Della","Heaney","Russell Homenick V","ricardo.larkin@yahoo.com","123-456-7893","213-456-7893","53081","75164 McClure Stream","Gislasonburgh","823-25-7239","2021-12-05T23:00:00Z",true,countryDataPojo,"New York43",null,null);

        Response response=given().spec(spec).headers("Authorization","Bearer " + Authentication.generateToken()).when().get("/{first}/{second}");

       GMICustomerPojo actualData=response.as(GMICustomerPojo.class);

       assertEquals(expectedData.getFirstName(), actualData.getFirstName());
       assertEquals(expectedData.getLastName(), actualData.getLastName());
       assertEquals(expectedData.getMiddleInitial(), actualData.getMiddleInitial());
       assertEquals(expectedData.getEmail(), actualData.getEmail());
       assertEquals(expectedData.getMobilePhoneNumber(), actualData.getMobilePhoneNumber());
       assertEquals(expectedData.getPhoneNumber(), actualData.getPhoneNumber());
       assertEquals(expectedData.getZipCode(), actualData.getZipCode());
       assertEquals(expectedData.getAddress(), actualData.getAddress());
       assertEquals(expectedData.getCity(), actualData.getCity());
       assertEquals(expectedData.getSsn(), actualData.getSsn());
       assertEquals(expectedData.getCreateDate(), actualData.getCreateDate());
       assertEquals(expectedData.getZelleEnrolled(), actualData.getZelleEnrolled());

       assertEquals(countryDataPojo.getId(), actualData.getCountry().getId());
       assertEquals(countryDataPojo.getName(), actualData.getCountry().getName());
       assertEquals(countryDataPojo.getStates(), actualData.getCountry().getStates());

       assertEquals(expectedData.getState(), actualData.getState());
       assertEquals(expectedData.getUser(), actualData.getUser());

    }
/*
{
        “id”: 110452,
        “firstName”: “Jasmine”,
        “lastName”: “Stehr”,
        “middleInitial”: “V”,
        “email”: “marni.zboncak@yahoo.com”,
        “mobilePhoneNumber”: “463-609-2097”,
        “phoneNumber”: “1-112-497-0270”,
        “zipCode”: “16525",
        “address”: “14387 Al Ridge5343 Bert Burgs”,
        “city”: “Waltermouth”,
        “ssn”: “761-59-2911",
        “createDate”: “2021-11-28T21:00:00Z”,
        “zelleEnrolled”: false,
        “country”: {
            “id”: 3,
            “name”: “USA”,
            “states”: null
        },
        “state”: “California”,
        “user”: {
            “id”: 110016,
            “login”: “leopoldo.reinger”,
            “firstName”: “Jasmine”,
            “lastName”: “Stehr”,
            “email”: “marni.zboncak@yahoo.com”,
            “activated”: true,
            “langKey”: “en”,
            “imageUrl”: null,
            “resetDate”: null
        },
        “accounts”: null
    }
 */
    //ObjectMapper ile cozum
    @Test
    public void test10() {
        spec.pathParams("first","tp-customers","second",110452);
        GMICountryDataPojo countryDataPojo=new GMICountryDataPojo(3,"USA",null);
        GMIUsertDataPojo userDataPojo=new GMIUsertDataPojo(110016,"leopoldo.reinger","Jasmine","Stehr","marni.zboncak@yahoo.com",true,"en",null,null);
        GMICustomerPojo expectedData=new GMICustomerPojo("Jasmine","Stehr","V","marni.zboncak@yahoo.com","463-609-2097","1-112-497-0270","16525","14387 Al Ridge5343 Bert Burgs","Waltermouth","761-59-2911","2021-11-28T21:00:00Z",false,countryDataPojo,"California",null, Arrays.asList());

        Response response=given().spec(spec).headers("Authorization","Bearer " + Authentication.generateToken()).when().get("/{first}/{second}");
        GMICustomerPojo actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),GMICustomerPojo.class);

        assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        assertEquals(expectedData.getLastName(),actualData.getLastName());
        assertEquals(expectedData.getMiddleInitial(),actualData.getMiddleInitial());
        assertEquals(expectedData.getEmail(),actualData.getEmail());
        assertEquals(expectedData.getMobilePhoneNumber(),actualData.getMobilePhoneNumber());
        assertEquals(expectedData.getCity(),actualData.getCity());
        assertEquals(expectedData.getSsn(),actualData.getSsn());
        assertEquals(expectedData.getCreateDate(), actualData.getCreateDate());
        assertEquals(expectedData.getZelleEnrolled(), actualData.getZelleEnrolled());

        assertEquals(countryDataPojo.getId(), actualData.getCountry().getId());
        assertEquals(countryDataPojo.getName(), actualData.getCountry().getName());
        assertEquals(countryDataPojo.getStates(), actualData.getCountry().getStates());

        assertEquals(expectedData.getState(), actualData.getState());

        assertEquals(userDataPojo.getId(), actualData.getUser().getId());
        assertEquals(userDataPojo.getLogin(), actualData.getUser().getLogin());
        assertEquals(userDataPojo.getFirstName(), actualData.getUser().getFirstName());
        assertEquals(userDataPojo.getLastName(), actualData.getUser().getLastName());
        assertEquals(userDataPojo.getEmail(), actualData.getUser().getEmail());
        assertEquals(userDataPojo.getActivated(), actualData.getUser().getActivated());
        assertEquals(userDataPojo.getLangKey(), actualData.getUser().getLangKey());
        assertEquals(userDataPojo.getImageUrl(), actualData.getUser().getImageUrl());
        assertEquals(userDataPojo.getResetDate(), actualData.getUser().getResetDate());

        assertEquals(expectedData.getAccounts(), actualData.getAccounts());
    }
}
