public class Application {
    public static void main(String[] args) {
        String[][] array = new String [3][2];

        //заполнение массива строками
        for (int i=0;i<array.length;i++)
            for (int j=0;j<array[i].length;j++)
                array[i][j] = String.valueOf((int)(Math.random()*100));


        //вызов метода по суммированию
        try {
            System.out.printf("Сумма элементов массива: %s", arraySum(array));
        }
        catch (MyArraySizeException e)
        {
            System.out.println("Переданный массив другой размерности");
        }
    }

    public static int arraySum (String[][] arrayIn) throws MyArraySizeException  {
        int[][] array = new int[4][4];
        int sum=0;
        //проверка, что массив меньше чем необходим по условию
        if (arrayIn.length<4 || arrayIn[0].length<4)
            throw new MyArraySizeException();

        //цикл заполнения преобразования и суммирования массива
        else {

            for (int i = 0; i < arrayIn.length; i++)
                for (int j = 0; j < arrayIn[i].length; j++) {
                    array[i][j] = Integer.parseInt(arrayIn[i][j]);
                    sum = array[i][j] + sum;
                }
        }


        return sum;
    }

}
