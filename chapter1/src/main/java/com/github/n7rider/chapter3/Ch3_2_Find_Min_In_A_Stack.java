package com.github.n7rider.chapter3;

import static org.junit.Assert.*;

/*
3 2 How would you design a stack which, in addition to push and pop, also has a function
min which returns the minimum element? Push, pop and min should all operate in
O(1) time

 */
public class Ch3_2_Find_Min_In_A_Stack {

    public static void main(String[] args) {

        Stack_EquisizedMinArray stack = new Stack_EquisizedMinArray(5);

        assertEquals(-100, stack.pop());

        assertTrue(stack.push(5));
        assertTrue(stack.push(10));
        assertTrue(stack.push(3));
        assertTrue(stack.push(12));
        assertTrue(stack.push(-2));

        assertFalse(stack.push(-101));

        assertEquals(-2, stack.min());
        assertEquals(-2, stack.pop());
        assertEquals(3, stack.min());
        assertEquals(12, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(5, stack.min());

        Stack_OndemandMinArray stack_advanced = new Stack_OndemandMinArray(5);

        assertEquals(-100, stack_advanced.pop());

        assertTrue(stack_advanced.push(5));
        assertTrue(stack_advanced.push(10));
        assertTrue(stack_advanced.push(3));
        assertTrue(stack_advanced.push(12));
        assertTrue(stack_advanced.push(-2));

        assertFalse(stack_advanced.push(-101));

        assertEquals(-2, stack_advanced.min());
        assertEquals(-2, stack_advanced.pop());
        assertEquals(3, stack_advanced.min());
        assertEquals(12, stack_advanced.pop());
        assertEquals(3, stack_advanced.pop());
        assertEquals(5, stack_advanced.min());
    }

// Alternative is to store minimum once in an array and return the same as long as the element doesn't get popped

    static class Stack_EquisizedMinArray {

// LinkedList is the best in-terms of on-demand storage. Using arrays for simplicity

        int[] objectStack;
        int[] minimumAtTheMomentHolder;
        int size;

        int currentPosition = 0;


        Stack_EquisizedMinArray(int size) {
            this.size = size;
            this.objectStack = new int[size];
            this.minimumAtTheMomentHolder = new int[size];
        }

        public boolean push(int input) {

            if (currentPosition < objectStack.length) {
                objectStack[currentPosition] = input;
                int minimumRightNow = 0;

                if (currentPosition == 0) {

                    minimumRightNow = input;
                } else {
                    int lastValueInMinStack = objectStack[currentPosition - 1];
                    minimumRightNow = (input < lastValueInMinStack ? input : lastValueInMinStack);
                }

                minimumAtTheMomentHolder[currentPosition] = minimumRightNow;

                currentPosition++;
                return true;
            }

            return false;
        }

        public int pop() {
            if (currentPosition > 0) {
                int valueToReturn = objectStack[currentPosition - 1];
                currentPosition--;
                return valueToReturn;
            }
            return -100;
        }

        public int min() {
            if (currentPosition > 0) {
                return minimumAtTheMomentHolder[currentPosition - 1];
            }

            return Integer.MAX_VALUE;
        }
    }

    static class Stack_OndemandMinArray {

        // LinkedList is the best in-terms of on-demand storage. Using arrays for simplicity
        int[] objectStack;
        // We don't necessarily need a 'n' spaced array, so an on-demand LinkedList is better. Using arrays for simplicity.
        int[] minimumAtTheMomentHolder;
        int size;

        int currentPosition = 0;
        int currentPositionOfMinStack = 0;

        Stack_OndemandMinArray(int size) {
            this.size = size;
            this.objectStack = new int[size];
            this.minimumAtTheMomentHolder = new int[size];
        }

        public boolean push(int input) {
            if (currentPosition < objectStack.length) {
                objectStack[currentPosition] = input;

                if (currentPositionOfMinStack == 0) {
                    minimumAtTheMomentHolder[0] = input;
                    currentPositionOfMinStack++;
                } else {
                    /* Equals is important, else duplicates shouldn't be inserted. If duplicates are inserted, to avoid popping the entire occurrence of the number from the min stack, we need to push in all instances of the same number, thus bringing in the equals sign
                     */

                    if (input <= minimumAtTheMomentHolder[currentPositionOfMinStack - 1]) {
                        minimumAtTheMomentHolder[currentPositionOfMinStack] = input;
                        currentPositionOfMinStack++;
                    }
                }

                currentPosition++;
                return true;
            }
            return false;
        }

        public int pop() {
            if (currentPosition > 0) {
                currentPosition--;

                int valueToReturn = objectStack[currentPosition];
                if (valueToReturn == minimumAtTheMomentHolder[currentPositionOfMinStack - 1]) {
                    currentPositionOfMinStack--;
                }

                return valueToReturn;

            }
            return -100;
        }

        public int min() {

            if (currentPositionOfMinStack > 0) {
                return minimumAtTheMomentHolder[currentPositionOfMinStack - 1];
            }

            return Integer.MAX_VALUE;
        }
    }
}

