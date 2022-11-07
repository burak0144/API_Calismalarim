package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceholderPojo;
import test_data.JsonplaceholderTestData;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get16ObjectMapper_Pojo_Dinamic extends JsonplaceholderBaseUrl {
    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */
    //ObjectMapper + Pojo =En iyi Dinamik yontem

    @Test
    public void get16ObjectMapper_Pojo_Dinamic() {
        spec.pathParams("first","todos","second",198);
        JsonPlaceholderPojo expectedData=new JsonPlaceholderPojo(10,"quis eius est sint explicabo",true);
        Response response=given().spec(spec).when().get("/{first}/{second}");
        JsonPlaceholderPojo actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),JsonPlaceholderPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());
    }
}
