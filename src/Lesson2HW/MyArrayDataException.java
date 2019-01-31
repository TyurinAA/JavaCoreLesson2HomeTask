package Lesson2HW;

public class MyArrayDataException extends IllegalArgumentException {
    private int rowIndex, columnIndex;

    public MyArrayDataException(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}
