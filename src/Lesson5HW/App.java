package Lesson5HW;

public class App {
    static final int SIZE = 10000000;
    static final int HALF = SIZE/ 2;




    public static void main(String[] args) {
        System.out.println("Homework for lesson 5 started");
        arrayRefactoring();
    }

    public static void arrayRefactoring(){
        float[] arr = new float[SIZE];

        for (int i=0;i<arr.length;i++){
            arr[i]=1;
        }

        long start = System.currentTimeMillis();

        for (int i=0;i<arr.length;i++){
            arr[i]=(float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long end = System.currentTimeMillis();

        System.out.printf("array refactoring lasts %f seconds\n",((float)(end - start))/1000);
    }
}
