package get_request;

import base_url.GorestUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14_Pojo extends GorestUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                   "id": 2508,
                   "name": "Dhananjay Chopra",
                   "email": "chopra_dhananjay@graham-bode.info",
                   "gender": "female",
                   "status": "active"
               }
          }
    */

    @Test
    public void get14Pojo() {
        spec.pathParams("first","users","second",2508);
        Response response=given().spec(spec).when().get("/{first}/{second}");

        GoRestDataPojo goRestDataPojo=new GoRestDataPojo(2508,"Dhananjay Chopra","chopra_dhananjay@graham-bode.info","female","active");
        GoRestPojo expected=new GoRestPojo(null,goRestDataPojo);

        GoRestPojo actual=response.as(GoRestPojo.class);
        assertEquals(200,response.statusCode());
        assertEquals(expected.getMeta(),actual.getMeta());
        assertEquals(goRestDataPojo.getId(),actual.getData().getId());
        assertEquals(goRestDataPojo.getName(),actual.getData().getName());
        assertEquals(goRestDataPojo.getEmail(),actual.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(),actual.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(),actual.getData().getStatus());
    }
}
