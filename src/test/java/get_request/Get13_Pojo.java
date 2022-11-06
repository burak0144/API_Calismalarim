package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingdatesPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13_Pojo extends RestfulBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/20
        When
          I send GET Request to the URL
       Then
          Status code is 200
      And
          Response body is like:
                     {
                        "firstname": "Sally",
                        "lastname": "Brown",
                        "totalprice": 111,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2013-02-23",
                            "checkout": "2014-10-23"
                        },
                        "additionalneeds": "Breakfast"
                      }
     */

    @Test
    public void get13_Pojo() {
        spec.pathParams("first","booking","second",20);

        Response response=given().spec(spec).when().get("/{first}/{second}");
        //Dinamik Map ile yaparken test_data'dan parametre gondererek alirken burda parametre ile construct
        //sayesinde expected data olusturulur
        BookingdatesPojo bookingdatesPojo=new BookingdatesPojo("2013-02-23","2014-10-23");
        BookingPojo expectedData=new BookingPojo("Sally","Brown",111,true,bookingdatesPojo,"Breakfast");
        //Dinamik Map ile HashMap kullanilirken burada Pojo class'imizi kullanarak actualData olusturduk
        BookingPojo actualData=response.as(BookingPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());
        //1.yol
        assertEquals(bookingdatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingdatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());
        //2.yol
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());

    }
}
