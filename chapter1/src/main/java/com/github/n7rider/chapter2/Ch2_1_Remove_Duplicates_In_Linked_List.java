package com.github.n7rider.chapter2;

import static org.junit.Assert.assertEquals;

/**
 * 2 1 Write code to remove duplicates from an unsorted linked list
 * FOLLOW UP
 * How would you solve this problem if a temporary buffer is not allowed?
 */
public class Ch2_1_Remove_Duplicates_In_Linked_List {

    public static void main(String[] args) {
        LinkedListNode_Character linkedlistStartingNode1 = LinkedListNode_Character.fromString("FOLLLQQQW UP");
        assertEquals("FOLQW UP", removeDuplicateWithLinkedList(linkedlistStartingNode1));

        LinkedListNode_Character linkedlistStartingNode2 = LinkedListNode_Character.fromString("FOLLLQQQW UP");
        assertEquals("FOLQW UP", removeDuplicateWithLinkedList_Inspired(linkedlistStartingNode2));
    }

    /*
    I used a boolean to hold information on duplicates assuming that the LL contains characters. Something like this needed if we have complex objects inside.
     */
    static String removeDuplicateWithLinkedList_Inspired(LinkedListNode_Character inputLinkedList) {

        // Skipping checks for nulls, blanks, single length Strings

        LinkedListNode_Character startingNode = inputLinkedList;
        LinkedListNode_Character previous = startingNode;
        LinkedListNode_Character currentObject = startingNode.next;
        while (currentObject != null) {

            LinkedListNode_Character duplicateCheckerCurrentPosition = startingNode;
            while (duplicateCheckerCurrentPosition != currentObject) {
                if (duplicateCheckerCurrentPosition.currentChar == currentObject.currentChar) {
                    previous.next = currentObject.next;
                    break;
                }
                duplicateCheckerCurrentPosition = duplicateCheckerCurrentPosition.next;
            }

            // Move previous only if the current object is not a duplicate. Else, wait here for the next non-duplicate object to point to.
            if (duplicateCheckerCurrentPosition == currentObject) {
                previous = previous.next;
            }
            currentObject = currentObject.next;

        }

        return inputLinkedList.convertToString();
    }


    static String removeDuplicateWithLinkedList(LinkedListNode_Character inputLinkedList) {

        // Skipping checks for nulls, blanks, single length Strings

        boolean[] duplicateInfoHolder = new boolean[256];

        LinkedListNode_Character previous = inputLinkedList;
        LinkedListNode_Character currentObject = previous.next;
        while (currentObject != null) {
            int asciiVal = currentObject.currentChar;
            if (duplicateInfoHolder[asciiVal]) {
                previous.next = currentObject.next;
            } else {
                previous = currentObject;
                duplicateInfoHolder[asciiVal] = true;
            }
            currentObject = currentObject.next;
        }

        return inputLinkedList.convertToString();
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

        String convertToString() {
            StringBuilder output = new StringBuilder();
            LinkedListNode_Character current = this;
            while (current != null) {
                output.append(current.currentChar);
                current = current.next;
            }
            return output.toString();
        }
    }
}

