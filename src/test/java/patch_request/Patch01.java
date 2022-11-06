package patch_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonplaceholderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonplaceholderBaseUrl {
       /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
     2) {
             "title": "Wash the dishes"
           }
    When
I send PATCH Request to the Url
 Then
      Status code is 200
      And response body is like   {
                   "userId": 10,
                   "title": "quis eius est sint explicabo",
                   "completed": true,
                   "id": 198
                   }
 */

    @Test
    public void patch01() {
        spec.pathParams("first","todos","second",198);
        JsonplaceholderTestData obj= new JsonplaceholderTestData();
        //patch'de parcali gonderebilmek icin diger parametreler null olmasi lazim bunun icinde
        //testdatasinda if!= null sarti ekleyerek null olmayan parcali parametre eklemis olduk
        Map<String,Object> expectedData=obj.expectedDataMethod(null,"quis eius est sint explicabo",null);

        Response response=given().spec(spec).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();
        Map actualData=response.as(Map.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("title"), actualData.get("title"));
    }

}
