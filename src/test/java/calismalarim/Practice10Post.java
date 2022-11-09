package calismalarim;

import base_url.GMIBankBaseUrl;
import org.junit.Test;

public class Practice10Post extends GMIBankBaseUrl {
    @Test
    public void test10() {
        spec.pathParams("first","tp-customers","second",114351);
    }
}
