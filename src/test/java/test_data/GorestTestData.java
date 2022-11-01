package test_data;

import java.util.HashMap;
import java.util.Map;

public class GorestTestData {
    public Map<String, String> expectedDataMethod(String email,String gender,String status) {
        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("data.email",email);
        expectedData.put("data.gender",gender);
        expectedData.put("data.status",status);

        return expectedData;
    }
    public Map<String, Object> expectedAllMethod(String meta,Map<String,String> data) {
        Map<String,Object> expectedAll=new HashMap<>();
        expectedAll.put("meta",meta);
        expectedAll.put("data",data);

        return expectedAll;
    }
}