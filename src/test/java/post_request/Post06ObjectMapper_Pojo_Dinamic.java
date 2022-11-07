package post_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceholderPojo;
import test_data.JsonplaceholderTestData;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06ObjectMapper_Pojo_Dinamic extends JsonplaceholderBaseUrl {
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
    public void post06ObjectMapper_Pojo_Dinamic() {
        spec.pathParam("first","todos");
        String expectedJsonInString=new JsonplaceholderTestData().expectedDataJsonInString(55,"Tidy your room",false);
        JsonPlaceholderPojo expectedData= ObjectMapperUtils.convertJsonToJava(expectedJsonInString,JsonPlaceholderPojo.class);
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        JsonPlaceholderPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(),JsonPlaceholderPojo.class);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());
    }
}
