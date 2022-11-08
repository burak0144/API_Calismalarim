package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import utilities.Authentication;

public class GMIBankBaseUrl {
    protected RequestSpecification spec;
    @Before
    public void setup(){
        spec=new RequestSpecBuilder().setBaseUri("http://www.gmibank.com/api/").build();
    }
}
