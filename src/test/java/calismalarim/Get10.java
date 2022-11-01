package calismalarim;

import base_url.AutoBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends AutoBaseUrl {
    @Test
    public void name() {
       // https://automationexercise.com/api/getUserDetailByEmail
        spec.pathParam("first","getUserDetailByEmail");
        Response response=given().when().spec(spec).get("/{first}");

        JsonPath jsonPath=new JsonPath(response.asString());
        assertEquals(400,jsonPath.getInt("responseCode"));
    }
}
