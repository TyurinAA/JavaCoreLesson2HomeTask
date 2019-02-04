package Lesson3HW;

import java.util.*;

public class Application {
    public static void main(String[] args) {

        PhoneBook phoneBook;
        phoneBook = new PhoneBook();

        // проверок на корректность аргументов не будет, не имеют смысла
        phoneBook.add(1, "a");
        phoneBook.add(2, "b");
        phoneBook.add(1, "c");
        phoneBook.add(3, "b");
        //phoneBook.add(12345, "a");
        //phoneBook.add(12345, "a");
        //phoneBook.add(12345, "a");
        phoneBook.getPhoneByName("b");
        task1();
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
        System.out.println("Уникальные эелементы и число их повторений: " + mapFinal.entrySet());

        //вывод значений финального листа
    //    for (Map.Entry<String,Integer> words:mapFinal.entrySet()){
    //        System.out.printf("Word %s has frequency %d\n", words.getKey(),words.getValue());
    //    }
    }


}
