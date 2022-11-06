package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.JsonplaceholderTestData;
import test_data.RestfulTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get09 extends RestfulBaseUrl {
    @Test
    public void get09() {
        /*
      Given
          https://restful-booker.herokuapp.com/booking/91
      When
          I send GET Request to the url
      Then
          Response body should be like that;
           {
          "firstname": "Sally",
          "lastname": "Brown",
          "totalprice": 111,
          "depositpaid": true,
          "bookingdates": {
              "checkin": "2013-02-23",
              "checkout": "2014-10-23"
          },
          "additionalneeds": "Breakfast"
          }
   */
        spec.pathParams("first","booking","second",91);
        Response response=given().spec(spec).when().get("/{first}/{second}");
        //`1.yol body ile
        response.then().body("firstname",equalTo("Howard"),
                "lastname",equalTo("Liu"),
                "totalprice",equalTo(111),
                "depositpaid",equalTo(true),
                "bookingdates.checkin",equalTo("2018-01-01"),
                "bookingdates.checkout",equalTo("2019-01-01"),
                "additionalneeds",equalTo("Breakfast"));

        //2.yol jsonPath ile
        JsonPath json=response.jsonPath();
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertEquals(json.getString("firstname"),"Howard");
        softAssert.assertEquals(json.getString("lastname"),"Liu");
        softAssert.assertEquals(json.getInt("totalprice"),111);
        softAssert.assertEquals(json.getBoolean("depositpaid"),true);
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2018-01-01");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2019-01-01");
        softAssert.assertEquals(json.getString("additionalneeds"),"Breakfast");

        softAssert.assertAll();

        //3.yol map ile
        Map<String,String> expectedDataBookingdates=new HashMap<>();  //once inner map yapildi
        expectedDataBookingdates.put("checkin","2018-01-01");
        expectedDataBookingdates.put("checkout","2019-01-01");

        Map<String,Object> expectedDataAll=new HashMap<>(); //outer map yapildi
        expectedDataAll.put("firstname","Howard");
        expectedDataAll.put("lastname","Liu");
        expectedDataAll.put("totalprice",111);
        expectedDataAll.put("bookingdates",expectedDataBookingdates);//ilk map eklendi
        expectedDataAll.put("additionalneeds","Breakfast");

        System.out.println(expectedDataAll);

        Map<String,Object> actualData=response.as(HashMap.class); //response da bulunan json lar javaya cevrilerek actualdatalar olsturuldu
        //karsilastirma yapalim simdi
        assertEquals(expectedDataAll.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedDataAll.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedDataAll.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedDataBookingdates.get("checkin"), ((Map)(actualData.get("bookingdates"))).get("checkin"));
        assertEquals(expectedDataBookingdates.get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));
        //yukarda map icinde map oldugundan dolayi get methoduyla map icindeki checkin ve checkout cagrildi
        assertEquals(expectedDataAll.get("additionalneeds"),actualData.get("additionalneeds"));

        //4.yol map ile dinamik
        RestfulTestData obj = new RestfulTestData();
        Map<String, String> expectedDataBookingdates2=obj.expectedDataBookingdatesMethod("2018-01-01","2019-01-01");//inner map
        Map<String,Object>expectedDataAll2=obj.expectedDataAllMethod("Howard","Liu",111,expectedDataBookingdates2,"Breakfast");//outer map
        //dinamik yontemde testdatalarini diger classtan alarak yaptik,yukardaki satirdaki expectedDataBookingdates2'yi inner mapte bi ust satirda olusturdugumuzu yazdik
        Map<String,Object> actualData2=response.as(HashMap.class);
        //karsilastirma yapalim
        assertEquals(expectedDataAll2.get("firstname"),actualData2.get("firstname"));
        assertEquals(expectedDataAll2.get("lastname"),actualData2.get("lastname"));
        assertEquals(expectedDataAll2.get("totalprice"),actualData2.get("totalprice"));
        assertEquals(expectedDataBookingdates2.get("checkin"),((Map)actualData2.get("bookingdates")).get("checkin"));
        assertEquals(expectedDataBookingdates2.get("checkout"),((Map)actualData2.get("bookingdates")).get("checkout"));
        assertEquals(expectedDataAll2.get("additionalneeds"),actualData2.get("additionalneeds"));




    }
}
