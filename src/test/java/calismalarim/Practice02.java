package calismalarim;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Practice02 {
    @Test
    public void test01() {
        String url="https://reqres.in/api/users";
        Response response=given().when().get(url);


    }
}
