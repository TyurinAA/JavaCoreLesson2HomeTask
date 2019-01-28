public class Application {
    public static void main(String[] args) {
        String[][] array = new String [4][4];

        //заполнение массива строками
        for (int i=0;i<4;i++)
            for (int j=0;j<4;j++)
                array[i][j] = String.valueOf(Math.random()*100);
            //вызов метода по суммированию

        System.out.printf("Сумма элементов массива: %s", Integer.toString(arraySum(array)));


    }

    public static int arraySum (String[][] arrayIn) throws ArrayStoreException {
        int[][] array = new int[4][4];
        int sum=0;

        //цикл заполнения преобразования и суммирования массива
        for (int i=0;i<4;i++)
            for (int j=0;j<4;j++)
            {
                array[i][j] = Integer.parseInt(arrayIn[i][j]);
                sum=array[i][j] +sum;
            }
        return sum;
    }

}
