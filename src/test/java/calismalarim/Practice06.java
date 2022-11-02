package calismalarim;

import io.restassured.response.Response;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.given;
import static utilities.Authentication.generateToken;

public class Practice06 {
    @Test
    public void test06() {
        String url="https://www.gmibank.com/api/tp-customers/114351";

        Response response=given().headers("Authorization","Bearer"+generateToken()).when().when().get(url);

        response.prettyPrint();
    }
}
