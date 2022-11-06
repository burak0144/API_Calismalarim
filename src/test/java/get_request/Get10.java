package get_request;

import base_url.GorestUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.GorestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get10 extends GorestUrl {
    @Test
    public void get10() {
        /*
   Given
       https://gorest.co.in/public/v1/users/2986
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
    {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Kanaka Jain",
        "email": "kanaka_jain@stark.net",
        "gender": "male",
        "status": "active"
    }
}
*/
        spec.pathParams("first","users","second",2986);
        Response response=given().spec(spec).when().get("/{first}/{second}");
        //1-body ile  equalTo kullanilacak
        response.then().statusCode(200).body("meta",equalTo(null),
                "data.id",equalTo(2986),
                "data.name",equalTo("Kanaka Jain"),
                "data.email",equalTo("kanaka_jain@stark.net"),
                "data.gender",equalTo("male"),
                "data.status",equalTo("active"));

        //2-json ile softassert kullanilacak
        JsonPath json=response.jsonPath();
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertEquals(json.getString("meta"),null);
        softAssert.assertEquals(json.getInt("data.id"),2986);
        softAssert.assertEquals(json.getString("data.email"),"kanaka_jain@stark.net");
        softAssert.assertEquals(json.getString("data.gender"),"male");
        softAssert.assertEquals(json.getString("data.status"),"active");
         softAssert.assertAll();

         //3-map ile expected ve actual map olusturulacak
        //Once expected map'i olusturalim
        Map<String,String> expectedData=new HashMap<>();//inner map
        //Request'te id oldugu icin eklemedik
        expectedData.put("data.email","kanaka_jain@stark.net");
        expectedData.put("data.gender","male");
        expectedData.put("data.status","active");

        Map<String,Object> expectedAll=new HashMap<>();  //outer map
        expectedAll.put("meta",null);
        expectedAll.put("data",expectedData);  //inner eklendi

        //Sonra actual map'i olusturalim
        HashMap actualMap=response.as(HashMap.class); //response'dan alinip map'e cevrildi

        //karsilastiralim
        assertEquals(expectedAll.get("meta"), actualMap.get("meta"));
        assertEquals(expectedData.get("data.email"),((Map)actualMap.get("data")).get("email"));
        assertEquals(expectedData.get("data.gender"),((Map)actualMap.get("data")).get("gender"));
        assertEquals(expectedData.get("data.status"),((Map)actualMap.get("data")).get("status"));

        //4-map ile dinamik test  Test data classi kullanilarak parametre gonderilecek
        GorestTestData obj = new GorestTestData();
        Map<String, String> expectedData2 = obj.expectedDataMethod("kanaka_jain@stark.net","male","active");
        Map<String, Object> expectedAll2 = obj.expectedAllMethod(null,expectedData2);
        Map<String, Object> actualMap2 = response.as(HashMap.class); //actual  map
         //karsilastiralim
        assertEquals(expectedAll2.get("meta"), actualMap2.get("meta"));
        assertEquals(expectedData2.get("data.email"),((Map)actualMap2.get("data")).get("email"));
        assertEquals(expectedData2.get("data.gender"),((Map)actualMap2.get("data")).get("gender"));
        assertEquals(expectedData2.get("data.status"),((Map)actualMap2.get("data")).get("status"));


    }
}
