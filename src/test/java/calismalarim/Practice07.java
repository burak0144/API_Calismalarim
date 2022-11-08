package calismalarim;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.Authentication.generateToken;

public class Practice07 extends GMIBankBaseUrl {
    /*
    http://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayın
   “firstName”: “Melva”,
   “lastName”: “Bernhard”,
   “email”: “chas.kuhlman@yahoo.com”
   “zipCode”: “40207"
   “country.name”: “San Jose”
   “user.login”: “delilah.metz”
     */

    @Test
    public void test07() {
        spec.pathParams("first","tp-customers","second",110472);

        Response response = given().spec(spec).headers("Authorization","Bearer " + Authentication.generateToken()).when().get("/{first}/{second}");
        response.prettyPrint();

        response.then().assertThat().body("firstName",equalTo("Melva"),
                "lastName",equalTo("Bernhard"),"email",equalTo("chas.kuhlman@yahoo.com"),
                "zipCode",equalTo("40207"),
                "country.name",equalTo("San Jose"),
                "user.login",equalTo("delilah.metz"));

        JsonPath json=response.jsonPath();
        Assert.assertEquals("Melva",json.get("firstName"));
        Assert.assertEquals("Bernhard",json.get("lastName"));
        Assert.assertEquals("chas.kuhlman@yahoo.com",json.get("email"));
        Assert.assertEquals("40207",json.get("zipCode"));
        Assert.assertEquals("San Jose",json.get("country.name"));
        Assert.assertEquals("delilah.metz",json.get("user.login"));
    }
}
