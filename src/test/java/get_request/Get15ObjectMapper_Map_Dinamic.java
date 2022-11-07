package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonplaceholderTestData;
import utils.ObjectMapperUtils;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper_Map_Dinamic extends JsonplaceholderBaseUrl {
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
    //ObjectMapper + Map yontemi dinamic
    //1.si jsonInString'i testDatasinin icinde dinamik yaptik
    //2.si new ObjectMapper().readValue(jsonInString,HashMap.class) utils paketinde dinamik yaptik
    //a)her defasinda object olusturulmayacak
    //b)her defasinda exception firlatmayacak

    @Test
    public void get15ObjectMapper_Map_Dinamic() {
        spec.pathParams("first","todos","second",198);
        String expectedDataString=new JsonplaceholderTestData().expectedDataJsonInString(10,"quis eius est sint explicabo",true);
        HashMap expectedData= ObjectMapperUtils.convertJsonToJava(expectedDataString,HashMap.class);

        Response response=given().spec(spec).when().get("/{first}/{second}");

        HashMap actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }
}
