package calismalarim;

import base_url.AutoBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Get09 extends AutoBaseUrl {
    @Test
    public void name() {
        spec.pathParam("first","brandsList");
        Response response =given().spec(spec).when().get("/{first}");

        JsonPath jsonPath=response.jsonPath();

        List<String> brandNames =jsonPath.getList("brands.findAll{it.brand}.brand");
        System.out.println(brandNames);
        System.out.println("brandNames.size() = " + brandNames.size());

        List<String> polo=jsonPath.getList("brands.findAll{it.brand=='Polo'}.brand");
        System.out.println("polo size ="+polo.size());

        List<String> babyhug=jsonPath.getList("brands.findAll{it.brand=='Polo'}.brand");
        System.out.println("babyhug size ="+babyhug.size());
        List<String> hm=jsonPath.getList("brands.findAll{it.brand=='Polo'}.brand");
        System.out.println("hm size ="+hm.size());
        List<String> madame=jsonPath.getList("brands.findAll{it.brand=='Polo'}.brand");
        System.out.println("madame size ="+madame.size());



    }
}
