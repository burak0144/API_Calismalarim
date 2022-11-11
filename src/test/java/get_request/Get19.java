package get_request;

import base_url.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get19 extends DummyRestApiBaseUrl {
     /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */
     @Test
     public void get19() {
         spec.pathParams("first","employee","second",1);
         DummyRestApiDataPojo dummyRestApiDataPojo=new DummyRestApiDataPojo("Tiger Nixon",320800,61,"");
         DummyRestApiPojo expectedData=new DummyRestApiPojo("success",dummyRestApiDataPojo,"Successfully! Record has been fetched.");

         Response response=given().spec(spec).when().get("/{first}/{second}");

         DummyRestApiPojo actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiPojo.class);

        // assertEquals(200,response.getStatusCode());
         assertEquals(dummyRestApiDataPojo.getEmployee_name(),actualData.getData().getEmployee_name());
         assertEquals(dummyRestApiDataPojo.getEmployee_salary(),actualData.getData().getEmployee_salary());
         assertEquals(dummyRestApiDataPojo.getEmployee_age(),actualData.getData().getEmployee_age());
         assertEquals(dummyRestApiDataPojo.getProfile_image(),actualData.getData().getProfile_image());
         assertEquals(expectedData.getStatus(),actualData.getStatus());
         assertEquals(expectedData.getMessage(),actualData.getMessage());
     }
}
