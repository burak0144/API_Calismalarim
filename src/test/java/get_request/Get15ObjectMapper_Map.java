package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.JsonplaceholderTestData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper_Map extends JsonplaceholderBaseUrl {
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
    //ObjectMapper + Map yontemi
    @Test
    public void get15ObjectMapper_Map() throws IOException {
        spec.pathParams("first","todos","second",198);

        String jsonInString="{\n" +
                "    \"userId\": 10,\n" +
                "    \"title\": \"quis eius est sint explicabo\",\n" +
                "    \"completed\": true\n" +
                "  }";
        //36.satir ile 46.satir dinamik olmadigindan dinamik hale getirilecektir
        HashMap expectedData =new ObjectMapper().readValue(jsonInString,HashMap.class);//string ifade HashMap'e cevrildi

        Response response=given().spec(spec).when().get("/{first}/{second}");

        HashMap actualData=new ObjectMapper().readValue(response.asString(),HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));

        //2.yol dinamik

    }
}
