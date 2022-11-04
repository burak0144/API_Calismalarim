package get_request;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import static utilities.Authentication.generateToken;

public class Get12 {
    //https://www.gmibank.com/api/tp-customers/42697
    // {
    //    "id": 42697,
    //    "firstName": "Ali",
    //    "lastName": "Deckow",
    //    "middleInitial": "Leroy Hoeger Sipes",
    //    "email": "com.github.javafaker.Name@7c011174@gmail.com",
    //    "mobilePhoneNumber": "115-471-7051",
    //    "phoneNumber": "876-394-6968",
    //    "zipCode": "67321",
    //    "address": "I live in St louis MO",
    //    "city": "St Louis",
    //    "ssn": "473-22-1798",
    //    "createDate": "0211-09-09T05:50:36Z",
    //    "zelleEnrolled": false,
    //    "country": null,
    //    "state": "",
    //    "user": null,
    //    "accounts": []
    //}
    //     */

    @Test
    public void name() {

        String url = "https://www.gmibank.com/api/tp-customers/42697";

        Response response = given().headers("Authorization", "Bearer " + generateToken()).when().get(url);

        response.then().assertThat().body("id", equalTo(42697),
                "firstName",equalTo("Ali"),
                "lastName",equalTo("Deckow"),
              "accounts",equalTo(Arrays.asList()));


        JsonPath json=new JsonPath(response.asString());

        SoftAssert softAssert= new SoftAssert();
        softAssert.assertEquals(42697,json.getInt("id"));
        softAssert.assertEquals("Deckow",json.getString("lastName"));
        softAssert.assertEquals("[]",json.getString("accounts"));

        softAssert.assertAll();


    }
}
