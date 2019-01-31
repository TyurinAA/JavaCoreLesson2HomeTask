package Lesson2HW;

public class Application {
    public static void main(String[] args) {
        String[][] array = new String [4][4];//здесь менять размерность для тестирования

        //заполнение массива строками
        for (int i=0;i<array.length;i++)
            for (int j=0;j<array[i].length;j++)
                array[i][j] = String.valueOf((int)(Math.random()*100)); //заполнение делается для проверки



          //array[1][1]="abc"; //здесь проверка неправильного элемента

        //вызов метода по суммированию
        try {
            System.out.printf("Сумма элементов массива: %s", arraySum(array));
        }
        catch (MyArraySizeException e)
        {
            System.out.println("Переданный массив другой размерности");
        }
        catch (MyArrayDataException e)
        {
            System.out.printf("ошибка элемента в строке %s, столбце %s",e.getRowIndex(),e.getColumnIndex());
        }
    }

    // метод для суммы, не имеет смысла делать отдельный класс
    public static int arraySum (String[][] arrayIn) throws MyArraySizeException,MyArrayDataException  {
        int[][] array = new int[4][4];
        int sum=0;

        //проверка, что массив меньше чем необходим по условию
        if (arrayIn.length<4 || arrayIn[0].length<4)
            throw new MyArraySizeException();

        //цикл заполнения преобразования и суммирования массива
        else
            for (int i = 0; i < arrayIn.length; i++)
                for (int j = 0; j < arrayIn[i].length; j++)
                    try
                    {
                        array[i][j] = Integer.parseInt(arrayIn[i][j]);
                        sum = array[i][j] + sum;
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        throw new MyArraySizeException();
                    }
                    catch (Exception e){
                        throw new MyArrayDataException(i,j);
                    }

        return sum;
    }

}
