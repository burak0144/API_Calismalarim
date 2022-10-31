package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get06 extends RestfulBaseUrl {
    @Test
    public void get06(){
        /*
        Given
            https://restful-booker.herokuapp.com/booking/2325
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
         {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-10-27",
        "checkout": "2022-11-07"
    },
    "additionalneeds": "None"
}
     */

        spec.pathParams("first","booking","second","2325");
        Response response=given().spec(spec).when().get("/{first}/{second}");

        //body icerisindekileri sadece dogrulama yapmak icin asagidaki gibi yapabiliriz
        response.then().statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Jim"),
                        "lastname",equalTo("Brown"),
                        "totalprice",equalTo(111),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout",equalTo("2019-01-01"),
                        "additionalneeds",equalTo("Breakfast"));

        //JsonPath ile body icerisinde istedigimiz islemi yapabiliriz,Bunun icin JsonPath ile body icine girilir
       JsonPath json=response.jsonPath();
        SoftAssert softAssert=new SoftAssert();

        //yukardaki gibi yapamayiz cunku bize boolean donderiyor

        softAssert.assertEquals("Jim",json.getString("firstname"));
        softAssert.assertEquals("Brown",json.getString("lastname"));
        softAssert.assertEquals(111,json.getInt("totalprice"));
        softAssert.assertEquals("2018-01-01",json.getString("bookingdates.checkin"));
        softAssert.assertEquals("2019-01-01",json.getString("bookingdates.checkout"));
        softAssert.assertEquals("Breakfast",json.getString("additionalneeds"));

        softAssert.assertAll();


    }
}
