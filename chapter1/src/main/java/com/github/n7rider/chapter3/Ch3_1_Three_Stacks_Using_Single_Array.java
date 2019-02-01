package com.github.n7rider.chapter3;

import static org.junit.Assert.*;

/*
3 1 Describe how you could use a single array to implement three stacks
 */
public class Ch3_1_Three_Stacks_Using_Single_Array {

    public static void main(String[] args) {

        MultiStacksHolder stacks3and7 = new MultiStacksHolder(7, 3);
        assertTrue(stacks3and7.push(1, 1));
        assertTrue(stacks3and7.push(0, 0));
        assertTrue(stacks3and7.push(2, 2));
        assertTrue(stacks3and7.push(1, 4));
        assertTrue(stacks3and7.push(0, 3));
        assertTrue(stacks3and7.push(0, 6));
        assertTrue(stacks3and7.push(2, 5));

        assertEquals(5, stacks3and7.pop(2));
        assertEquals(4, stacks3and7.pop(1));
        assertEquals(6, stacks3and7.pop(0));

        assertTrue(stacks3and7.push(2, 12));
        assertFalse(stacks3and7.push(2, 14));

    }

    static class MultiStacksHolder {

        int arraySize;
        int numberOfStacks;
        int[] array;
        int[] stackIndexArray;

        public MultiStacksHolder(int size, int numberOfStacks) {
            this.arraySize = size;
            this.numberOfStacks = numberOfStacks;
            array = new int[size];
            stackIndexArray = new int[numberOfStacks];
            for (int i = 0; i < numberOfStacks; i++) {
                stackIndexArray[i] = i;
            }
        }

        boolean push(int stackNumber, int value) {
            if (stackIndexArray[stackNumber] < arraySize) {
                array[stackIndexArray[stackNumber]] = value;
                stackIndexArray[stackNumber] += numberOfStacks;
                return true;
            }
            return false;
        }

        int pop(int stackNumber) {
            int positionOfElement = stackIndexArray[stackNumber] - numberOfStacks;
            if (positionOfElement >= 0) {
                stackIndexArray[stackNumber] = positionOfElement;
                return array[positionOfElement];
            } else {
                // Or throw an exception
                return -100;
            }
        }

    }


}
