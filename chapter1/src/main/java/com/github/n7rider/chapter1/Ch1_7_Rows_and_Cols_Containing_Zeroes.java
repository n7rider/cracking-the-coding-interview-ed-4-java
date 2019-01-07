package com.github.n7rider.chapter1;

public class Ch1_7_Rows_and_Cols_Containing_Zeroes {

    /**
     * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
     * column is set to 0
     **/

    private static void setRowColToZeroIfOneElementIsZero(int[][] matrix) {

        boolean[] rowZeroingInformation = new boolean[matrix.length];
        boolean[] colZeroingInformation = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int[] currentRow = matrix[i];
            for (int j = 0; j < currentRow.length; j++) {
                if (matrix[i][j] == 0) {
                    rowZeroingInformation[i] = true;
                    colZeroingInformation[j] = true;
                }
            }
        }

        // A single loop can avoid two iterations but both row and col arrays have to be checked every time to make sure we don't go out of trouble. So sticking
        for (int i = 0; i < rowZeroingInformation.length; i++) {
            if (rowZeroingInformation[i]) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < colZeroingInformation.length; i++) {
            if (colZeroingInformation[i]) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }

    private static void printMatrix(int[][] matrix) {

        // Skipping checking for nulls and blanks

        for (int i = 0; i < matrix.length; i++) {
            int[] currentRow = matrix[i];
            StringBuffer currentRowAsString = new StringBuffer("");
            for (int j = 0; j < currentRow.length; j++) {
                currentRowAsString = currentRowAsString.append(matrix[i][j] + " ");
            }
            System.out.println(currentRowAsString);
        }
    }

    public static void main(String[] args) {

        int[][] matrix = new int[4][5];

        matrix[0] = new int[]{12, 23, 5, 0, 78};
        matrix[1] = new int[]{12, 33, 0, 0, 28};
        matrix[2] = new int[]{22, 53, 15, 10, 68};
        matrix[3] = new int[]{42, 63, 45, 20, 98};

        printMatrix(matrix);
        System.out.println();

        setRowColToZeroIfOneElementIsZero(matrix);
        printMatrix(matrix);
    }
}