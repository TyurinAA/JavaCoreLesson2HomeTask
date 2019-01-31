package Lesson3HW;

import java.util.*;

public class Application {
    public static void main(String[] args) {

        PhoneBook phoneBook;
        phoneBook = new PhoneBook();
        phoneBook.add(1, "a");
        phoneBook.add(2, "b");
        phoneBook.add(1, "c");
        phoneBook.add(3, "b");
        //phoneBook.add(12345, "a");
        //phoneBook.add(12345, "a");
        //phoneBook.add(12345, "a");
        task1();

       // int phone = phoneBook.getPhonebyname("a"); // пока не понятно
       // System.out.printf("Phone %d belongs to ") // пока не понятно
    }

    public static void task1(){
        List<String> arrayInitial = new ArrayList<>();
        Map<String,Integer> mapFinal = new HashMap<>();

        arrayInitial.add("aa");
        arrayInitial.add("aa");
        arrayInitial.add("aa");
        arrayInitial.add("cc");
        arrayInitial.add("aa");
        arrayInitial.add("aa");
        arrayInitial.add("bb");
        arrayInitial.add("bb");

        //заполнение финального массива уникальными словами и количеством повторений
        for (String word:arrayInitial){
            if (!mapFinal.containsKey(word)){
                mapFinal.put(word,Collections.frequency(arrayInitial,word));
            }
        }
        arrayInitial = null; //чистим память - вообще стоит это делать?

        //вывод значений финального листа - можно еще чистку сделать
        for (Map.Entry<String,Integer> words:mapFinal.entrySet()){
            System.out.printf("Word %s has frequency %d\n", words.getKey(),words.getValue());
        }
    }


}
