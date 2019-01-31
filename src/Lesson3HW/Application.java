package Lesson3HW;

import java.util.*;

public class Application {
    public static void main(String[] args) {
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
        arrayInitial = null; //чистим память

        //вывод значений финального листа - можно еще чистку сделать
        for (Map.Entry<String,Integer> words:mapFinal.entrySet()){
            System.out.printf("Word %s has frequency %d\n", words.getKey(),words.getValue());
        }
    }

}
