package get_request;

import base_url.DummyRestApiBaseUrl;

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
     public void get19() {
         spec.pathParams("first","employees","second",1);
         Dum
     }
}
