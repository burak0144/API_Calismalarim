package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.JsonplaceholderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonplaceholderBaseUrl {
    //De-Serialization: Json datayı Java objesine çevirme
    //Serialization: Java objesini Json formatına çevirme.
    //De-Serialization: Iki türlü yapacağız.
    //Gson: Google tarafından üretilmiştir.
    //Object Mapper: Daha popüler...

      /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    @Test
    public void get08() {
        spec.pathParams("first","todos","second",2);
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.then().statusCode(200).header("Via",equalTo("1.1 vegur")).header("Server",equalTo("cloudflare"));

        //1.yol
        response.then().statusCode(200).header("Via",equalTo("1.1 vegur")).header("Server",equalTo("cloudflare")).
                body("userId",equalTo(1),
                        "title",equalTo("quis ut nam facilis et officia qui"),
                        "completed",equalTo(false));

       //2.yol
        JsonPath json =response.jsonPath();
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertEquals(json.getInt("userId"),1);
        softAssert.assertEquals(json.getString("title"),"quis ut nam facilis et officia qui");
        softAssert.assertEquals(json.getBoolean("completed"),false);

         softAssert.assertAll();

         //3.yol
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        System.out.println("expected data ="+expectedData);

        Map<String,Object> actualData=response.as(HashMap.class);//De-Serialization
        System.out.println("actual data ="+actualData);
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));

        //3.yol dinamik yontem(66 -70 satirdaki gibi manul ekleme yapmadik parametre gonderip methodu kullandik)
        JsonplaceholderTestData obj=new JsonplaceholderTestData();
        Map<String,Object> expectedData2=obj.expectedDataMethod(1,"quis ut nam facilis et officia qui",false);
        assertEquals(expectedData2.get("userId"),actualData.get("userId"));
        assertEquals(expectedData2.get("title"),actualData.get("title"));
        assertEquals(expectedData2.get("completed"),actualData.get("completed"));
    }
}
