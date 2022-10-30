package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonplaceholderBaseUrl {
    @Test
    public void Get04(){
        /*
        Given
            https://jsonplaceholder.typicode.com/todos
        When
          I send a GET request to the Url
       And
           Accept type is "application/json"
       Then
           HTTP Status Code should be 200
       And
           Response format should be "application/json"
       And
           There should be 200 todos
       And
           "quis eius est sint explicabo" should be one of the todos title
       And
           2, 7, and 9 should be among the userIds
     */
        spec.pathParam("first","todos");
        //  I send a GET request to the Url
       Response response = given().when().accept(ContentType.JSON).spec(spec).get("/{first}");
    //response.prettyPrint();

    //  Accept type is "application/json"     Then den once oldugu icin yukarda accept ile yapmamiz gerekecek


        //           HTTP Status Code should be 200
     response.then().statusCode(200).

        //           Response format should be "application/json"
        contentType(ContentType.JSON).

        //           There should be 200 todos
        body("id",hasSize(200), //id yazilan yere herhangi bir string ifade de olabilir

        //           "quis eius est sint explicabo" should be one of the todos title
        "title",hasItem("quis eius est sint explicabo"),

        //           2, 7, and 9 should be among the userIds
             "userId",hasItems(2,7,9));
    }
}
