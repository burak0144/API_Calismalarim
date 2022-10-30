package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get03 extends JsonplaceholderBaseUrl {
    @Test
    public void get03() {
         /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
      And
          Response format should be "application/json"
      And
          "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
      And
          "completed" is false
      And
          "userId" is 2
     */
        spec.pathParams("first","todos","second",23);
       // User send GET Request to the URL
        Response response =given().spec(spec).when().get("/{first}/{second}");
      //  response.prettyPrint();

        // HTTP Status Code should be 200
        response.then().assertThat().statusCode(200);
       // response.then().statusCode(200);
       // assertEquals(200,response.statusCode());

       // Response format should be "application/json"
        response.then().contentType(ContentType.JSON);
        //response.then().contentType("application/json");

        //"title" is "et itaque necessitatibus maxime molestiae qui quas velit",
        response.then().body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"));
       // assertTrue(response.asString().contains("et itaque necessitatibus maxime molestiae qui quas velit"));

        //"completed" is false
        response.then().body("completed",equalTo(false));

        // "userId" is 2
        response.then().body("userId",equalTo(2));

        //hepsini birarada yapacak olursak hardassert
        response.then().statusCode(200).contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).
                body("userId",equalTo(2));

        //softassert ile yapacak olursak 1 body kullanarak yapabiliriz
        response.then().statusCode(200).contentType(ContentType.JSON)
                .body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed",equalTo(false),
                        "userId",equalTo(2));
        //ayni body icersinde test yapildiginda softassert olurve test devam eder,sadee body icersinde gecerlidir
    }
}
