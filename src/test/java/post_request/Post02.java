package post_request;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingdatesPojo;
import test_data.RestfulTestData;
import test_data.RestfulTestData02;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends RestfulBaseUrl {
     /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "Agustin",
                                               "lastname": "Parchment",
                                               "totalprice": 111,
                                               "bookingdates": {
                                                   "checkin": "2018-01-01",
                                                   "checkout": "2019-01-01"
                                                   },
                                               "additionalneeds":Breakfast

                                           }
                                        }
*/

    @Test
    public void post02() {
        spec.pathParam("first","booking");
        //put post ve patch yaparken once expected'i belirleyip request'e body eklememiz gerekir
        //post yaparken butun datalar girilmesi gerektiginden RestfulTestData02 olusturduk(RestfulTestData deposited yok)
        RestfulTestData02 obj= new RestfulTestData02();
        Map<String,String> expectedBookingDatas=obj.expectedDataBookingdatesMethod("2021-09-09","2021-09-21");
        Map<String,Object> expectedDataAll=obj.expectedDataAllMethod("Agustin","Parchment",111,true,expectedBookingDatas);
        System.out.println("expectedDataAll = " + expectedDataAll);
        //post'ta contentType eklenmek zorunda
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedDataAll).when().post("/{first}");

        Map<String,Object>actualData=response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(expectedDataAll.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
       assertEquals(expectedDataAll.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
       assertEquals(expectedDataAll.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
       assertEquals(expectedDataAll.get("additionalneeds"),((Map)actualData.get("booking")).get("additionalneeds"));

       assertEquals(expectedBookingDatas.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
       assertEquals(expectedBookingDatas.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));

    }
}
