package post_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceholderPojo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03Pojo extends JsonplaceholderBaseUrl {
     /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post03Pojo() {
        //id yazilmadiginda UnrecognizedPropertyException hatayi veriyor bu yuzden pojo classlara
        //@JsonIgnoreProperties(ignoreUnknown = true) eklememiz gerekiyor
        spec.pathParam("first","todos");

        JsonPlaceholderPojo expectedData = new JsonPlaceholderPojo(55,"Tidy your room",false);

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");

        JsonPlaceholderPojo actualData=response.as(JsonPlaceholderPojo.class);

        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
    }
}
