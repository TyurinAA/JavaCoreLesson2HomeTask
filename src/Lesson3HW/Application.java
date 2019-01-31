package Lesson3HW;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<String> arrayInitial = new ArrayList<>();
        List<UniqueWord> arrayFinal = new ArrayList<>();


        //заполнение финального массива уникальными словами и количеством повторений
        for (String word:arrayInitial){
            if (arrayFinal.indexOf(word)==-1){
                UniqueWord finalWord = new UniqueWord(word, Collections.frequency(arrayInitial,word));
                arrayFinal.add(finalWord);
            }
        }
        arrayInitial = null; //чистим память

        //вывод значений финального листа - можно еще чистку сделать
        for (UniqueWord word:arrayFinal){
            System.out.printf("Word %s has frequency %d/n", word.getWord(),word.getCount());
        }
    }

}
