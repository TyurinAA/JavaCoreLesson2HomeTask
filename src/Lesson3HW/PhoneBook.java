package Lesson3HW;

import java.util.HashMap;
import java.util.Map;

public class PhoneBook {

    private Map<Integer,String> records = new HashMap<>();


    public void add (Integer phone, String sName){
        if (records.containsKey(phone)) {
            System.out.printf("The number %d belong to family %s but now its changed to family %s\n", phone,records.get(phone),sName);
            records.put(phone,sName);
        }
        else {
            records.put(phone,sName);
        }

    }

}
