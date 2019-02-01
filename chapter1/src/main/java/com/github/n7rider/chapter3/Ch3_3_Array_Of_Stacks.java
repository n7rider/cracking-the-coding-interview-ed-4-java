package com.github.n7rider.chapter3;

import java.util.Stack;

import static org.junit.Assert.*;

/*
3 3 Imagine a (literal) stack of plates If the stack gets too high, it might topple There-
fore, in real life, we would likely start a new stack when the previous stack exceeds
some threshold Implement a data structure SetOfStacks that mimics this SetOf-
Stacks should be composed of several stacks, and should create a new stack once
the previous one exceeds capacity SetOfStacks push() and SetOfStacks pop() should
behave identically to a single stack (that is, pop() should return the same values as it
would if there were just a single stack)
FOLLOW UP
Implement a function popAt(int index) which performs a pop operation on a specific
sub-stack
 */
public class Ch3_3_Array_Of_Stacks {

    public static void main(String[] args) {
        SetOfStacks setOfStacks = new SetOfStacks(3, 3);

        assertTrue(setOfStacks.push(10));
        assertTrue(setOfStacks.push(21));
        assertTrue(setOfStacks.push(32));
        assertTrue(setOfStacks.push(43));
        assertTrue(setOfStacks.push(54));
        assertTrue(setOfStacks.push(65));
        assertTrue(setOfStacks.push(76));
        assertTrue(setOfStacks.push(87));
        assertTrue(setOfStacks.push(98));
        assertFalse(setOfStacks.push(101));

        assertEquals(98, setOfStacks.pop());
        assertEquals(87, setOfStacks.pop());

        assertEquals(65, setOfStacks.popAt(1));
        assertEquals(54, setOfStacks.popAt(1));
        assertEquals(43, setOfStacks.popAt(1));

        assertEquals(-100, setOfStacks.popAt(1));

        assertEquals(76, setOfStacks.pop());
        assertEquals(32, setOfStacks.pop());
        assertEquals(21, setOfStacks.pop());
        assertEquals(10, setOfStacks.pop());
        assertEquals(-100, setOfStacks.pop());

    }

    static class SetOfStacks {

        int numberOfElementsPerStack;

        int numberOfStacks;
        Stack[] stackSet;

        int stackInUsage = 0;
        int[] numberOfElementsInStack;

        SetOfStacks(int numberOfStacks, int numberOfElementsPerStack) {
            this.numberOfElementsPerStack = numberOfElementsPerStack;
            this.numberOfStacks = numberOfStacks;
            this.stackSet = new Stack[numberOfStacks];

            for (int i = 0; i < numberOfStacks; i++) {
                stackSet[i] = new Stack<Integer>();
            }

            this.numberOfElementsInStack = new int[numberOfStacks];

        }

        public boolean push(int value) {

            if (numberOfElementsInStack[stackInUsage] == numberOfElementsPerStack) {
                if (stackInUsage + 1 == numberOfStacks) {
                    return false;
                }
                stackInUsage++;
            }

            stackSet[stackInUsage].push(value);
            numberOfElementsInStack[stackInUsage]++;
            return true;
        }

        public int pop() {

            int stackToPop = stackInUsage;
            while (stackToPop >= 0) {

                if (stackSet[stackToPop].isEmpty()) {
                    stackToPop--;
                } else {
                    stackInUsage = stackToPop;
                    return (Integer) stackSet[stackToPop].pop();
                }
            }

            stackInUsage = 0;
            return -100;

        }

        public int popAt(int stackIndex) {

            if (stackIndex > numberOfStacks || stackSet[stackIndex].isEmpty()) {
                return -100;
            }

            // No null check of the stack is required since we move to the next stack only on-demand

            return (Integer) stackSet[stackIndex].pop();


        }

    }
}
