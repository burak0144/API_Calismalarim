package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

public class Get13_Pojo extends RestfulBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/19
        When
          I send GET Request to the URL
       Then
          Status code is 200
      And
          Response body is like:
                     {
                        "firstname": "Guoqiang",
                        "lastname": "Liu",
                        "totalprice": 111,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2018-01-01",
                            "checkout": "2019-01-01"
                        },
                        "additionalneeds": "Breakfast"
                      }
     */

    @Test
    public void get13_Pojo() {
        spec.pathParams("first","booking","second",19);
        Response response=
    }
}
