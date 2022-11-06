package delete_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonplaceholderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01() {
        spec.pathParams("first","todos","second",198);
        Map expectedData=new HashMap();
        Response response=given().spec(spec).when().delete("/{first}/{second}");

        Map actualData = response.as(Map.class);
        assertEquals(0,actualData.size());
        assertTrue(actualData.isEmpty());
        assertEquals(expectedData,actualData);

    }
}
