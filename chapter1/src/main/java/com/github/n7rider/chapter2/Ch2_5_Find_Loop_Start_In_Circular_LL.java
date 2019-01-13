package com.github.n7rider.chapter2;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * 2 5 Given a circular linked list, implement an algorithm which returns node at the beginning of the loop
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node’s next pointer points to an
 * earlier node, so as to make a loop in the linked list
 * EXAMPLE
 * input: A -> B -> C -> D -> E -> C [the same C as earlier]
 * output: C
 */
public class Ch2_5_Find_Loop_Start_In_Circular_LL {

    public static void main(String[] args) {

        // Test 1
        LinkedListNode_Character[] linkedListEdges = LinkedListNode_Character.fromString_ReturnHeadAndEndNode("abcde");
        LinkedListNode_Character head = linkedListEdges[0];
        LinkedListNode_Character end = linkedListEdges[1];
        LinkedListNode_Character node_c = head.findChar('c');
        end.next = node_c;

        LinkedListNode_Character loopFoundNode = head.loopsMeetingPointIfLoopsMeet();
        if (loopFoundNode != null) {
            assertEquals(node_c.currentChar, loopFoundNode.findLoopStart().currentChar);
        } else {
            fail();
        }

        // Test 2
        linkedListEdges = LinkedListNode_Character.fromString_ReturnHeadAndEndNode("abcd");
        head = linkedListEdges[0];
        end = linkedListEdges[1];
        node_c = head.findChar('c');
        end.next = node_c;

        loopFoundNode = head.loopsMeetingPointIfLoopsMeet();
        if (loopFoundNode != null) {
            assertEquals(node_c.currentChar, loopFoundNode.findLoopStart().currentChar);
        } else {
            fail();
        }

        // Test 3
        linkedListEdges = LinkedListNode_Character.fromString_ReturnHeadAndEndNode("abcdef");
        head = linkedListEdges[0];
        end = linkedListEdges[1];
        LinkedListNode_Character node_d = head.findChar('d');
        end.next = node_d;

        loopFoundNode = head.loopsMeetingPointIfLoopsMeet();
        if (loopFoundNode != null) {
            assertEquals(node_d.currentChar, loopFoundNode.findLoopStart_Inspired(head).currentChar);
        } else {
            fail();
        }
    }

    static class LinkedListNode_Character {

        LinkedListNode_Character previous;
        LinkedListNode_Character next;
        char currentChar;

        static LinkedListNode_Character[] fromString_ReturnHeadAndEndNode(String input) {
            LinkedListNode_Character head = new LinkedListNode_Character();
            LinkedListNode_Character currentObject = head;
            for (char currentChar : input.toCharArray()) {
                currentObject.currentChar = currentChar;
                currentObject.next = new LinkedListNode_Character();
                currentObject.next.previous = currentObject;
                currentObject = currentObject.next;
            }
            currentObject = currentObject.previous;
            currentObject.next = null;

            return new LinkedListNode_Character[]{head, currentObject};
        }

        /*
        When iterations of speed 1x and 2x start at 0, they always meet at nth or the 0th element in a loop.
        When the 2x iteration starts at k, we meet 'k' units before 'n' since 2x already had a headstart of k. So, we meet at n-k.
        Now we iterate some units till we reach n-k+x = 0 where x=k. So, n-k+k = 0 is the starting point of the loop.

        This takes O(n) time and is better than the other algorithm.

         */
        public LinkedListNode_Character findLoopStart_Inspired(LinkedListNode_Character head) {
            LinkedListNode_Character oneIncrementedNode = head;
            LinkedListNode_Character twoIncrementedNode = this;

            while (oneIncrementedNode != twoIncrementedNode) {
                oneIncrementedNode = oneIncrementedNode.next;
                twoIncrementedNode = twoIncrementedNode.next;
            }
            return oneIncrementedNode;

        }

        /*
        This takes O(n^2) time since it compares each node with everything else in the loop till we find a place where it no more loops.
         */
        public LinkedListNode_Character findLoopStart() {
            LinkedListNode_Character loopFoundNode = this;

            LinkedListNode_Character intersectingNode = this;

            // Can start next to intersectingNode but is a waste of time. We already know that spots between intersectingNode and the place where we started are not the nodes we are looking for
            LinkedListNode_Character seekingNodeCurrent = loopFoundNode.next;

            while (intersectingNode != null) {
                if (intersectingNode == seekingNodeCurrent) {
                    // Met intersecting node. Still in the loop. Move one spot to the left and see if we are at the start of the loop.
                    intersectingNode = intersectingNode.previous;
                    seekingNodeCurrent = loopFoundNode.next;
                } else if (seekingNodeCurrent == loopFoundNode) {
                    // Loop no more intersects intersectingnode. Return the previous intersectingNode
                    return intersectingNode.next;
                } else {
                    // Neither met intersectingNode or have reached where we started. Continue.
                    seekingNodeCurrent = seekingNodeCurrent.next;
                }
            }

            return null;
        }

        public LinkedListNode_Character loopsMeetingPointIfLoopsMeet() {

            // Skipping checking for nulls and blanks

            if (this.next == null || this.next.next == null) {
                return null;
            }

            // Floyd's algorithm
            LinkedListNode_Character oneIncrementedNode = this.next;
            LinkedListNode_Character twoIncrementedNode = this.next.next;

            while (twoIncrementedNode != null) {
                if (oneIncrementedNode == twoIncrementedNode) {
                    return oneIncrementedNode;
                }

                oneIncrementedNode = oneIncrementedNode.next;
                if (twoIncrementedNode.next != null) {
                    twoIncrementedNode = twoIncrementedNode.next.next;
                } else {
                    return null;
                }
            }

            return null;
        }

        LinkedListNode_Character findChar(char input) {

            LinkedListNode_Character current = this;
            while (current != null) {
                if (input == current.currentChar) {
                    return current;
                }
                current = current.next;
            }
            return null;
        }

    }
}
