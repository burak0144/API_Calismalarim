package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingdatesPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get17 extends RestfulBaseUrl {
    /*
        Given
               https://restful-booker.herokuapp.com/booking/22
        When
            I send GET Request to the URL
      Then
            Status code is 200
                  {
                    "firstname": "Guoqiang",
                    "lastname": "Morante Briones",
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
    public void get17() {
        spec.pathParams("first","booking","second",22);
        BookingdatesPojo bookingdatesPojo=new BookingdatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData= new BookingPojo("Howard","Sevilla",111,true,bookingdatesPojo,"Breakfast");

        Response response=given().spec(spec).when().get("/{first}/{second}");

        BookingPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(),BookingPojo.class);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

        assertEquals(bookingdatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingdatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());
    }
}
