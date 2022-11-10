package post_request;

import base_url.JsonplaceholderBaseUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper_Map extends JsonplaceholderBaseUrl {
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
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post05ObjectMapper_Map() throws IOException {
        spec.pathParam("first","todos");
        String jsonInString="{\n" +
                "                                    \"userId\": 55,\n" +
                "                                    \"title\": \"Tidy your room\",\n" +
                "                                    \"completed\": false,\n" +
                "                                    \"id\": 201\n" +
                "                                    }";
        //String ifade ObjectMapper'in readValue methoduyla HashMap'e cevrilecek
        HashMap expectedData=new ObjectMapper().readValue(jsonInString,HashMap.class);

        //Post oldugundan content type yazilacak
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");

        HashMap actualData=new ObjectMapper().readValue(response.asString(),HashMap.class);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("id"), actualData.get("id"));
    }
}
