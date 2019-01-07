package com.github.n7rider.chapter1;

public class Ch1_6_RotateElementsInMatrixInline {

    /**
     * Given an image represented by an NxN matrix, where each pixel in the image is 4
     * bytes, write a method to rotate the image by 90 degrees Can you do this in place?
     */

    private static void rotateMatrix90DegreesClockwise(MatrixElement[][] inputMatrix, boolean bytesOrientation_Horizontal) {
        // Going by the edges cyclically to do it inline. We go from (0,0) to (n/2, n/2)
        for (int i = 0; i < inputMatrix.length / 2; i++) {
            int numberOfBlocksInCurrentEdge = inputMatrix.length - i * 2; // For 6*6 matrix, goes decreasing 6, 4, 2 etc. Loses count on both sides of the matrix. Like peeling an onion.
            for (int j = 0; j < numberOfBlocksInCurrentEdge - 1; j++) { // -1 denotes we don;t want to rotate the last element in the current row because it got already rotated with the first element of the current row
                MatrixElement temp = new MatrixElement(inputMatrix[i][j + i].getData());

                // inputMatrix.length - 1 => denotes last element in the 0-based matrix
                // inputMatrix.length - 1 - i => denotes items that don't move for a entire rectangle of rotation
                // inputMatrix.length - 1 - i - j => denotes items that don't move for a single rotation

                inputMatrix[i][j + i] = inputMatrix[inputMatrix.length - 1 - j - i][i]; // top left takes bottom left
                inputMatrix[inputMatrix.length - 1 - j - i][i] = inputMatrix[inputMatrix.length - 1 - i][inputMatrix.length - 1 - j - i]; // bottom left takes bottom right
                inputMatrix[inputMatrix.length - 1 - i][inputMatrix.length - 1 - j - i] = inputMatrix[j + i][inputMatrix.length - 1 - i]; // bottom right takes top right
                inputMatrix[j + i][inputMatrix.length - 1 - i] = temp; // top right takes top left
            }

        }

    }

    /**
     * Same as mine but makes things clean by taking out the matrixLength - 1 -i into a variable.
     * Probably, visualizing the entire flow earlier can help spot this even while constructing the logic.
     *
     * @param inputMatrix
     * @param bytesOrientation_Horizontal
     */
    private static void rotateMatrix90DegreesClockwise_Inspired(MatrixElement[][] inputMatrix, boolean bytesOrientation_Horizontal) {
        // Going by the edges cyclically to do it inline. We go from (0,0) to (n/2, n/2)
        for (int i = 0; i < inputMatrix.length / 2; i++) {
            int numberOfBlocksInCurrentEdge = inputMatrix.length - i * 2; // For 6*6 matrix, goes decreasing 6, 4, 2 etc. Loses count on both sides of the matrix. Like peeling an onion.
            int positionForThisRectangle = inputMatrix.length - 1 - i;

            for (int j = 0; j < numberOfBlocksInCurrentEdge - 1; j++) { // -1 denotes we don;t want to rotate the last element in the current row because it got already rotated with the first element of the current row
                MatrixElement temp = new MatrixElement(inputMatrix[i][j + i].getData());

                // inputMatrix.length - 1 => denotes last element in the 0-based matrix
                // inputMatrix.length - 1 - i => denotes items that don't move for a entire rectangle of rotation
                // inputMatrix.length - 1 - i - j => denotes items that don't move for a single rotation

                inputMatrix[i][j + i] = inputMatrix[positionForThisRectangle - j][i]; // top left takes bottom left

                inputMatrix[positionForThisRectangle - j][i] = inputMatrix[positionForThisRectangle][positionForThisRectangle - j]; // bottom left takes bottom right
                inputMatrix[positionForThisRectangle][positionForThisRectangle - j] = inputMatrix[j + i][positionForThisRectangle]; // bottom right takes top right
                inputMatrix[j + i][positionForThisRectangle] = temp; // top right takes top left
            }

        }

    }


    public static void main(String[] args) {

        MatrixElement[][] inputMatrix = new MatrixElement[5][5];
        boolean bytesOrientation_Horizontal = true; // the 4*1 data bytes need to be rotated to 1*4 bytes after rotation

        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix.length; j++) {
                byte[] dataByte = (i + "|" + j + "|").getBytes();
                inputMatrix[i][j] = new MatrixElement(dataByte);
            }
        }

        printMatrix(inputMatrix, bytesOrientation_Horizontal, 4);
        rotateMatrix90DegreesClockwise_Inspired(inputMatrix, bytesOrientation_Horizontal);
        bytesOrientation_Horizontal = false;

        printMatrix(inputMatrix, bytesOrientation_Horizontal, 4);

    }


    private static void printMatrix(MatrixElement[][] inputMatrix, boolean horizontal, int elementLength) {
        for (int i = 0; i < inputMatrix.length; i++) {

            if (!horizontal) {
                for (int j = 0; j < elementLength; j++) {
                    for (int k = 0; k < inputMatrix.length; k++) {
                        System.out.print(inputMatrix[i][k].getData()[j]);
                        System.out.print("\t");
                    }

                    System.out.println();
                }
            } else {
                for (int j = 0; j < inputMatrix.length; j++) {
                    System.out.print(inputMatrix[i][j].toString_Horizontal());
                    System.out.print("\t");

                }

            }

            System.out.println();
        }
    }

    static class MatrixElement {

        byte[] data;

        public MatrixElement(byte[] dataByte) {
            this.data = dataByte;
        }

        public byte[] getData() {
            return data;
        }

        public String toString_Horizontal() {

            // Skipping -1 at the end
            StringBuffer output = new StringBuffer(data.length * 2);
            for (byte currentByte : data) {
                output = output.append(currentByte).append(" ");
            }

            return output.toString();
        }


    }


}
