package calismalarim;

import base_url.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Practice03 extends ReqresBaseUrl {
    @Test
    public void get03() {
        /* Matchers ile dataları doğrulayınız
            "id": 5,
            "email": "charles.morris@reqres.in",
            "first_name": "Charles",
            "last_name": "Morris",
            "avatar": "https://reqres.in/img/faces/5-image.jpg"
     */
        spec.pathParam("first","5");
        Response response=given().spec(spec).when().get("/{first}");
        //response.prettyPrint();
        //Matcher dedigi icin body kullanacagiz
        response.then().body("data.id",equalTo(5),
                "data.email",equalTo("charles.morris@reqres.in"),
                "data.first_name",equalTo("Charles"),
                "data.last_name",equalTo("Morris"),
                "data.avatar",equalTo("https://reqres.in/img/faces/5-image.jpg"));

    }
}
