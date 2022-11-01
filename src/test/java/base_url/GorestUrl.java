package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.Before;

public class GorestUrl {
    protected RequestSpecification spec;
    @Before
    public void setup(){
        spec=new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public/v1").build();

    }
}
