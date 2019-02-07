package Lesson5HW;

public class App {
    static final int SIZE = 100000;
    static final int HALF = SIZE/ 2;




    public static void main(String[] args) throws InterruptedException {
        System.out.println("Homework for lesson 5 started");
        float[] arrInitial = new float[SIZE];
        for (int i=0;i<arrInitial.length;i++){
            arrInitial[i]=1;
        }
        float[] arr1 = new float[SIZE];
        arr1 = arrInitial.clone();
        arrayRefactoring(arr1);
        arrayRefactoringThreads(arrInitial);

    }

    // method 1 without threads
    public static void arrayRefactoring(float[] arr){
        long start = System.currentTimeMillis();
        arr = arrayModifier(arr);
        long end = System.currentTimeMillis();
        System.out.printf("array refactoring lasts %f seconds\n",((float)(end - start))/1000);
    }

    //method 2 with threads
    public static void arrayRefactoringThreads(float[] arr) throws InterruptedException {
        long start = System.currentTimeMillis();
        ThreadForArray threadForArray1 = new ThreadForArray();
        ThreadForArray threadForArray2 = new ThreadForArray();
        float[] arrH1 = new float[HALF];
        float[] arrH2 = new float[HALF];
        System.arraycopy(arr,0,arrH1,0, HALF);
        System.arraycopy(arr,HALF-1,arrH2,0, HALF);
        threadForArray1.setArray(arrH1);
        threadForArray2.setArray(arrH2);
        threadForArray1.start();
        threadForArray2.start();
        threadForArray1.join();
        threadForArray2.join();
        arrH1 = threadForArray1.getArray();
        arrH2 = threadForArray1.getArray();
        System.arraycopy(arrH1,0,arr,0, HALF);
        System.arraycopy(arrH2,0,arr,HALF-1,HALF);
        long end = System.currentTimeMillis();
        System.out.printf("array refactoring lasts %f seconds\n",((float)(end - start))/1000);
    }


    // method for array modification
    public static float[] arrayModifier(float[] arr){
        for (int i=0;i<arr.length;i++){
            arr[i]=(float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return arr;
    }

    // thread class
    public static class ThreadForArray extends Thread{

        private float[] array = new float[HALF];

        @Override
        public void run() {
           this.array = arrayModifier(array);
        }

        public void setArray (float[] array){
            this.array = array;
        }

        public float[] getArray() {
            return this.array;
        }
    }
}
