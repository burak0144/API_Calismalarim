package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get05 extends RestfulBaseUrl {
    @Test
    public void get05() {
        /*
        Given
            https://restful-booker.herokuapp.com/booking

        When
            User sends get request to the URL
        Then
            Status code is 200
      And
         Among the data there should be someone whose firstname is "James" and lastname is "Brown"
     */
        spec.pathParams("first","booking").queryParams("firstname","James","lastname","Brown");
        //querry params ile sorgulanacak ifadeler ekleniyor
        Response response=given().spec(spec).when().get("/{first}");

        // Status code is 200
        response.then().statusCode(200);
        //assertEquals(200,response.statusCode());

        //Among the data there should be someone whose firstname is "James" and lastname is "Brown"
        assertTrue(response.asString().contains("bookingid"));

    }
}
