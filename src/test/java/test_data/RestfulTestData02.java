package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfulTestData02 {
    public Map<String, String> expectedDataBookingdatesMethod(String checkin,String checkout){
        Map<String,String> expectedDataBookingdates=new HashMap<>();  //once inner map yapildi
        expectedDataBookingdates.put("checkin",checkin);
        expectedDataBookingdates.put("checkout",checkout);

        return expectedDataBookingdates;


    }
    public Map<String,Object>expectedDataAllMethod(String firstname,String lastname,Integer totalprice,Boolean depositpaid,Map<String,String> bookingdates){
        Map<String,Object> expectedDataAll=new HashMap<>();
        expectedDataAll.put("firstname",firstname);
        expectedDataAll.put("lastname",lastname);
        expectedDataAll.put("totalprice",totalprice);
        expectedDataAll.put("depositpaid",depositpaid);
        expectedDataAll.put("bookingdates",bookingdates);


        return expectedDataAll;
    }
}
