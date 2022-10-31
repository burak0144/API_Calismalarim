package getRequest;

import base_url.AutoBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
public class HW01 extends AutoBaseUrl {
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
    @Test
    public void get01() {
        spec.pathParams("first","productsList");
        Response response = given().spec(spec).when().get("{first}");
        response.prettyPrint();
        JsonPath json =response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        assertEquals(200,response.statusCode());
        assertEquals("text/html; charset=utf-8",response.contentType());
        assertEquals("HTTP/1.1 200 OK",response.getStatusLine());
        List<String>women = json.getList("products.category.usertype.findAll{it.usertype.isEqualsto('Women')}.usertype");
        List<String>men = json.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        List<String>kids = json.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");
        softAssert.assertEquals(12,women.size());
        softAssert.assertEquals(9,men.size());
        softAssert.assertEquals(13,kids.size());

    }
}