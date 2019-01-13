package com.github.n7rider.chapter2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * 2 2 Implement an algorithm to find the nth to last element of a singly linked list
 */
public class Ch2_2_Find_Nth_Last_Element_In_LL {

    public static void main(String[] args) {
        LinkedListNode_Character linkedListStartingNode = LinkedListNode_Character.fromString("FOLLOW UP");
        assertEquals('O', nthNodeFromLast(5, linkedListStartingNode));
        assertEquals('P', nthNodeFromLast(1, linkedListStartingNode));

        try {
            nthNodeFromLast(100, linkedListStartingNode);
            fail();
        } catch (IllegalArgumentException iae) {
            // Junit 5 has assertThrows() which is more suitable here, but not using it.

        }

    }

    public static char nthNodeFromLast(int n, LinkedListNode_Character linkedListStartingNode) {
        // Skipping checking for blanks, nulls

        // Establish a 'n' point difference between head & currentObject
        LinkedListNode_Character currentObject = linkedListStartingNode;
        for (int i = 0; i < n; i++) {
            if (currentObject != null) {
                currentObject = currentObject.next;
            } else {
                throw new IllegalArgumentException();
            }
        }

        // Move with the 'n' point difference till you reach the end. Now, the head will be the nth char from the last
        // Watch for null character (ASCII 0) too
        while (currentObject != null && currentObject.currentChar != (char) 0) {
            currentObject = currentObject.next;
            linkedListStartingNode = linkedListStartingNode.next;
        }

        return linkedListStartingNode.currentChar;

    }


    static class LinkedListNode_Character {

        LinkedListNode_Character next;
        char currentChar;

        // Using tempObjectBeforeHead ensures no blank object is created at the end of the list.
        // IMO, the forward-only nature of LL necessitates an empty object either at the front or the end.
        static LinkedListNode_Character fromString(String input) {
            LinkedListNode_Character tempObjectBeforeHead = new LinkedListNode_Character();
            LinkedListNode_Character currentObject = tempObjectBeforeHead;
            for (char currentChar : input.toCharArray()) {
                currentObject.next = new LinkedListNode_Character();
                currentObject = currentObject.next;
                currentObject.currentChar = currentChar;
            }
            return tempObjectBeforeHead.next;
        }
    }
}




