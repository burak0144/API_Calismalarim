package get_request;

import base_url.GorestUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;


public class Get11 extends GorestUrl {
  /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Fr. Kumar Johar", "Lakshmi Banerjee" are among the users
        And
            The female users are less than or equals to male users
     */

    @Test
    public void get11() {
        spec.pathParam("first","users");
        Response response=given().spec(spec).when().get("/{first}");

        response.then().statusCode(200).body("meta.pagination.limit",equalTo(10),
               "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data",hasSize(10),
                "data.status",hasItem("active"),
                "data.name",hasItems("Meena Pillai","Tara Nambeesan DO"));
        //1.yol list'e atarak karsilastirma yapilabilir
        List<String> genders=response.jsonPath().getList("data.gender");
        int female=0;
        for (String w:genders
             ) {
          if (w.equalsIgnoreCase("female")) {
                female++;
            }
        }
        assertTrue(female<=genders.size()-female);

        //2.yol Kadın ve erkek sayılarını Groovy ile bulalım.
        List<String> female2=response.jsonPath().getList("data.findAll{it.gender=='female'}.gender");
        List<String>male2=response.jsonPath().getList("data.findAll{it.gender=='male'}.gender");
        assertTrue(female2.size()<=male2.size());
    }

}
