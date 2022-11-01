package calismalarim;

import base_url.AutoBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
public class HW01 extends AutoBaseUrl {
    @Test
    public void test01() {
        /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be "text/html; charset=utf-8"
    And
        Status Line should be HTTP/1.1 200 OK
    And
         There must be 12 Women, 9 Men, 13 Kids usertype in products
      */

        spec.pathParam("first","productsList");
        Response response=given().spec(spec).when().get("/{first}");

        JsonPath json=response.jsonPath();
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(200,response.statusCode());
        softAssert.assertEquals("text/html; charset=utf-8",response.contentType());
        softAssert.assertEquals("HTTP/1.1 200 OK",response.getStatusLine());

        List<String> women=json.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        //products altindaki category altindaki usertype altindaki usertype'i Women'a esit olan tum usertype'lari getir
        List<String> men=json.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        List<String> kids=json.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");

        softAssert.assertEquals(women.size(),12);
        softAssert.assertEquals(men.size(),9);
        softAssert.assertEquals(kids.size(),13);

        softAssert.assertAll();

        //System.out.println(women.stream().filter(t -> (t.equals( "Women"))).count());  //12
       softAssert.assertEquals(women.stream().filter(t -> (t.equals( "Women"))).count(),12);
       softAssert.assertEquals(men.stream().filter(t -> (t.equals( "Men"))).count(),9);
       softAssert.assertEquals(kids.stream().filter(t -> (t.equals( "Kids"))).count(),13);
    }

}