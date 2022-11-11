package get_request;

import base_url.DummyRestApiBaseUrl;
import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiPojo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get18 extends DummyRestApiBaseUrl {
    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */
    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees

    When
      User sends Get request

   Them
       i) Status code is 200
   And
       ii) There are 24 employees
   And
      iii) "Tiger Nixon" and "Garrett Winters" are among the employees
    And
       iv) The greatest age is 66
   And
        v) The name of the lowest age is "Tatyana Fitzpatrick"
   And
       vi) Total salary of all employees is 6,644,770
     */

    @Test
    public void get18() {
        spec.pathParams("first","employees");
        Response response=given().spec(spec).when().get("/{first}");
        //i) Status code is 200
        assertEquals(200,response.getStatusCode());
        //ii) There are 24 employees
        List<Integer> numberOfEmployees=response.jsonPath().getList("data.findAll{it.id}.id");
        System.out.println("numberOfEmployees = " + numberOfEmployees.size());
        assertEquals(24,numberOfEmployees.size());
        //iii) "Tiger Nixon" and "Garrett Winters" are among the employees
        List<String> namesOfEmployees=response.jsonPath().getList("data.findAll{it.employee_name}.employee_name");
        System.out.println("namesOfEmployees = " + namesOfEmployees);
        assertTrue(namesOfEmployees.contains("Tiger Nixon"));
        assertTrue(namesOfEmployees.contains("Garrett Winters"));
        //2.yol ilk 3 soru
        response.then().statusCode(200).body("data",hasSize(24),
                "data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));

        //iv) The greatest age is 66
        List<Integer> ages=response.jsonPath().getList("data.employee_age");
        System.out.println("ages = " + ages);
        Collections.sort(ages);
        System.out.println("Sirali ages :"+ages);
        assertEquals((Integer)66,ages.get(ages.size()-1));
        //2.yol
        List<Integer> age=ages.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        assertEquals((Integer) 66,age.get(0));

        //v) The name of the lowest age is "Tatyana Fitzpatrick"
        String nameOfLowestAge=response.jsonPath().getString("data.findAll{it.employee_age=="+ages.get(0)+"}.employee_name");
        System.out.println("nameOfLowestAge = " + nameOfLowestAge);
        assertEquals("[Tatyana Fitzpatrick]",nameOfLowestAge);
        // vi) Total salary of all employees is 6,644,770
        int totalSalary=0;
        List<Integer> salaries=response.jsonPath().getList("data.employee_salary");
        System.out.println(salaries+" ");
        for (int i = 0; i <salaries.size() ; i++) {
            totalSalary+=salaries.get(i);
        }
        System.out.println("totalSalary = " + totalSalary);
        assertEquals(6644770,totalSalary);

        //2.yol
        int totalSalary2=salaries.stream().reduce(Math::addExact).get();
        assertEquals(6644770,totalSalary2);

        //3.yol
        int sum3=salaries.stream().reduce(Integer::sum).get();
        assertEquals(6644770,sum3);
    }
}
