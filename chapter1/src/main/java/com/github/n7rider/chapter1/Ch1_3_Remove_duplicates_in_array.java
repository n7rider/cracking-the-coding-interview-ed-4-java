/*
Design an algorithm and write code to remove the duplicate characters in a string
without using any additional buffer NOTE: One or two additional variables are fine
An extra copy of the array is not
FOLLOW UP
Write the test cases for this method
 */
package com.github.n7rider.chapter1;

import static org.junit.Assert.assertEquals;

public class Ch1_3_Remove_duplicates_in_array {


    private static String removeDuplicates(String input) {

        // Skipped validation for nulls & blanks

        // Variable to mark the actual end position - after removing duplicates
        int endPosition = 0;
        char[] inputCharArray = input.toCharArray();
        boolean[] duplicateCheckInformation = new boolean[256];

        for (int i = 0; i < inputCharArray.length; i++) {
            int asciiOfCurrentChar = inputCharArray[i];

            // If not a duplicate, then copy to boolean[], increment end position, copy to the new position in array (if needed)
            if (!duplicateCheckInformation[asciiOfCurrentChar]) {
                duplicateCheckInformation[asciiOfCurrentChar] = true;

                // Don't swap as long as no duplicates have been found yet
                if (i != endPosition) {
                    inputCharArray[endPosition] = inputCharArray[i];
                }
                endPosition++;
            }
        }

        return String.valueOf(inputCharArray).substring(0, endPosition);
    }

    public static void main(String[] args) {

        assertEquals("abcdefgh", removeDuplicates("abcdefgh"));
        assertEquals("abcdefgh", removeDuplicates("abcdefgha"));
        assertEquals("ABCDEa", removeDuplicates("ABCDEa"));
        assertEquals("ABCED", removeDuplicates("ABCEDB"));
        assertEquals("AB[CD^Ea", removeDuplicates("AB[CD^Ea"));
        assertEquals("A+BC[ED", removeDuplicates("A+BC[ED+B"));

    }


}
