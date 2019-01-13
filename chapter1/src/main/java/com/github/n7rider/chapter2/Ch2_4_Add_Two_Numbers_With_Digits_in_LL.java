package com.github.n7rider.chapter2;

import static org.junit.Assert.assertEquals;

/**
 * 2 4 You have two numbers represented by a linked list, where each node contains a single digit The digits are stored in reverse order, such that the 1’s digit is at the head of
 * the list Write a function that adds the two numbers and returns the sum as a linked
 * list
 * EXAMPLE
 * Input: (3 -> 1 -> 5) + (5 -> 9 -> 2)
 * Output: 8 -> 0 -> 8
 */
public class Ch2_4_Add_Two_Numbers_With_Digits_in_LL {

    public static void main(String[] args) {
        LinkedListNode_Int number1 = LinkedListNode_Int.fromString("315");
        LinkedListNode_Int number2 = LinkedListNode_Int.fromString("592");

        assertEquals("808", addLinkedListInts(number1, number2).convertToString());

        number1 = LinkedListNode_Int.fromString("999");
        number2 = LinkedListNode_Int.fromString("888");

        assertEquals("7881", addLinkedListInts(number1, number2).convertToString());

        number1 = LinkedListNode_Int.fromString("9991");
        number2 = LinkedListNode_Int.fromString("888");

        assertEquals("7882", addLinkedListInts(number1, number2).convertToString());


    }

    private static LinkedListNode_Int addLinkedListInts(LinkedListNode_Int number1, LinkedListNode_Int number2) {

        // Skipping null checks, blank checks

        LinkedListNode_Int number3_temp_digit_before_head = new LinkedListNode_Int();
        LinkedListNode_Int number3 = number3_temp_digit_before_head;
        int carryOver = 0;
        while (number1 != null || number2 != null || carryOver > 0) {
            int sumOfCurrentDigits = carryOver + (number1 != null ? number1.currentInt : 0) + (number2 != null ? number2.currentInt : 0);

            // Creating digits on-demand
            number3.next = new LinkedListNode_Int();
            number3 = number3.next;
            number3.currentInt = sumOfCurrentDigits % 10;

            carryOver = sumOfCurrentDigits / 10;

            number1 = number1 != null ? number1.next : null;
            number2 = number2 != null ? number2.next : null;
        }

        return number3_temp_digit_before_head.next;
    }

    static class LinkedListNode_Int {

        LinkedListNode_Int next;

        // Can use char since we need values ranging 0 to 9 alone, but that's an overkill
        int currentInt;

        static LinkedListNode_Int fromString(String input) {
            // Using a prehead enables we create objects only on-demand. Therefore, there is no blank object at the end of the linkedlist
            LinkedListNode_Int preHead = new LinkedListNode_Int();
            LinkedListNode_Int currentObject = preHead;

            int inputInteger = Integer.valueOf(input);

            int reversedInteger = 0;
            while (inputInteger > 0) {
                reversedInteger = reversedInteger * 10 + inputInteger % 10;
                inputInteger /= 10;
            }

            while (reversedInteger > 0) {

                currentObject.next = new LinkedListNode_Int();
                currentObject = currentObject.next;
                currentObject.currentInt = reversedInteger % 10;

                reversedInteger /= 10;
            }

            return preHead.next;
        }

        String convertToString() {
            StringBuilder output = new StringBuilder();
            LinkedListNode_Int current = this;
            while (current != null) {
                output.append(current.currentInt);
                current = current.next;
            }
            return output.toString();
        }
    }

}
