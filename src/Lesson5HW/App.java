package Lesson5HW;

public class App {
    static final int SIZE = 10000000;
    static final int HALF = SIZE/ 2;




    public static void main(String[] args) throws InterruptedException {
        System.out.println("Homework for lesson 5 started");
        float[] arrInitial = new float[SIZE];
        for (int i=0;i<arrInitial.length;i++){
            arrInitial[i]=1;
        }
        float[] arr1 = arrInitial.clone();
        arrayRefactoring(arr1);
        arrayRefactoringThreads(arrInitial);

    }

    // method 1 without threads
    public static void arrayRefactoring(float[] arr){
        long start = System.currentTimeMillis();
        arr = arrayModifier(arr,0);
        long end = System.currentTimeMillis();
        System.out.printf("array refactoring lasts %f seconds\n",((float)(end - start))/1000);
    }

    //method 2 with threads
    public static void arrayRefactoringThreads(float[] arr) throws InterruptedException {
        long start = System.currentTimeMillis();

        float[] arrH1 = new float[HALF];
        float[] arrH2 = new float[HALF];
        System.arraycopy(arr,0,arrH1,0, HALF);
        System.arraycopy(arr,HALF,arrH2,0, HALF);
        ThreadForArray threadForArray1 = new ThreadForArray(arrH1 , 0);
        ThreadForArray threadForArray2 = new ThreadForArray(arrH2, HALF);
        threadForArray1.start();
        threadForArray2.start();
        threadForArray1.join();
        threadForArray2.join();
        arrH1 = threadForArray1.getArray();
        arrH2 = threadForArray2.getArray();
        System.arraycopy(arrH1,0,arr,0, HALF);
        System.arraycopy(arrH2,0,arr,HALF,HALF);
        long end = System.currentTimeMillis();
        System.out.printf("array refactoring lasts %f seconds\n",((float)(end - start))/1000);
    }


    // method for array modification
    public static float[] arrayModifier(float[] arr, int index){
        for (int i=index;i<arr.length+index;i++){
            arr[i-index]=(float)(arr[i-index] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return arr;
    }

    // thread class
    public static class ThreadForArray extends Thread{

        private float[] array;
        private int index;

        public ThreadForArray(float[] array, int index) {
            this.array = array;
            this.index = index;
        }

        @Override
        public void run() {
           this.array = arrayModifier(array,index);

        }

        public float[] getArray() {
            return this.array;
        }
    }
}
