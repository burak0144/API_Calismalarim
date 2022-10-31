package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends JsonplaceholderBaseUrl {
    @Test
    public void get07(){

    /*
        Given
              https://jsonplaceholder.typicode.com/todos
      When
          I send GET Request to the URL == > URL'e Get Request gonderin
      Then
          1)Status code is 200 == > Status kodu 200 olmali
          2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
            Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
          3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
            Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
          4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
            Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
            basliginin "delectus aut autem" icerdigini dogrulayin
     */
        spec.pathParam("first","todos");
        Response response=given().spec(spec).when().get("/{first}");
        response.then().assertThat().statusCode(200);
        ///assertEquals(200,response.statusCode());
        JsonPath json=response.jsonPath();

       // 2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
        // Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
        //body icinde islem yapacagimizda jsonPath ve Grovy Language kullanilir
        List<Integer> ids =json.getList("findAll{it.id>190}.id");
        System.out.println("Id'si 190 dan Buyuk Olanlar = " + ids);
        //assertEquals("Id 190 dan buyuk olan eslesmedi",ids.size(),equalTo(10));// <10> actual bu sekilde oldugu icin
                //asagidaki gibi yapmamiz lazim
        assertEquals("Id 190 dan buyuk olan eslesmedi",10,ids.size());

        // 3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
        //Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
        List<Integer> userIds=json.getList("findAll{it.id<5}.userId");
        System.out.println("Id 5'ten kucuk olan userIds ="+userIds);
        assertEquals("Id'si 5 den kucuk olan User Id ler 4 tane Degil",4,userIds.size());

       //4)Print all titles whose ids are less than 5 ==> id si 5 den kucuk olan tum basliklari yazdirin
        List<String> titles=json.getList("findAll{it.id<5}.title");
        System.out.println("Id 5'ten kucuk olan titles ="+titles);
        //Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
        //            basliginin "delectus aut autem" icerdigini dogrulayin
        assertTrue("titles arasinda delectus aut autem bulunmuyor",titles.contains("delectus aut autem"));
        titles.stream().anyMatch(t->t.equals("delectus aut autem"));

    }
}
