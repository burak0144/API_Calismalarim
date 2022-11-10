package post_request;

import base_url.JsonplaceholderBaseUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.Test;
import pojos.JsonPlaceholderPojo;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06ObjectMapper_Pojo extends JsonplaceholderBaseUrl {
/*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
               }
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like  {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post06ObjectMapper_Pojo() throws IOException {
        spec.pathParam("first","todos");
        JsonPlaceholderPojo expectedData=new JsonPlaceholderPojo(55,"Tidy your room",false);

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");

        JsonPlaceholderPojo actualData=new ObjectMapper().readValue(response.asString(),JsonPlaceholderPojo.class);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());
    }
}
