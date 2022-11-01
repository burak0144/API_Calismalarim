package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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


    }
}
