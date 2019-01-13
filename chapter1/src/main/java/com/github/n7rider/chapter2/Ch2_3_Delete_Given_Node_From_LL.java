package com.github.n7rider.chapter2;

import static org.junit.Assert.assertEquals;

/**
 * 2 3 Implement an algorithm to delete a node in the middle of a single linked list, given
 * only access to that node
 * EXAMPLE
 * Input: the node ‘c’ from the linked list a->b->c->d->e
 * Result: nothing is returned, but the new linked list looks like a->b->d->e
 */
public class Ch2_3_Delete_Given_Node_From_LL {

    public static void main(String[] args) {
        LinkedListNode_Character linkedListNode = LinkedListNode_Character.fromString("ABCDE");
        LinkedListNode_Character nodeToDelete = Ch2_3_Delete_Given_Node_From_LL.findGivenNodeFromLL('C', linkedListNode);
        deleteGivenNodeFromLL(nodeToDelete);
        assertEquals("ABDE", linkedListNode.convertToString());
    }

    private static void deleteGivenNodeFromLL(LinkedListNode_Character nodeToDelete) {

        // Skipping checking for null and last/first element in LL

        nodeToDelete.currentChar = nodeToDelete.next.currentChar;
        nodeToDelete.next = nodeToDelete.next.next;
    }

    private static LinkedListNode_Character findGivenNodeFromLL(char charToFind, LinkedListNode_Character linkedListNode) {

        while (linkedListNode != null) {
            if (linkedListNode.currentChar == charToFind) {
                return linkedListNode;
            }
            linkedListNode = linkedListNode.next;
        }
        return null;
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
