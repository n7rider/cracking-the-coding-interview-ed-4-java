package com.github.n7rider.chapter1;

import static org.junit.Assert.assertEquals;

public class Ch1_5_Replace_Substrings_With_Varied_Lengthed_Substrings {
    /**
     * Write a method to replace all spaces in a string with ‘%20’
     */

    // Not opting for String.replace since it doesn't deal with the problem directly
    private static String replacePattern(String input) {

        // Skipping checking nulls & blanks

        // find number of spaces in the String
        int countOfSpaces = 0;
        for (int i = 0; i < input.length(); i++) {
            // Method optimized for a single space.
            if (input.charAt(i) == ' ') {
                countOfSpaces++;

            }
        }

        // Skipping returning actual String if no space is found
        // Skipping using a StringBuffer which could have simplified things but veered too much away from the core logic.

        // create a new array with length = input length - number of spaces + (no. of spaces * length of new pattern)
        // Skipping a radical way to avoid creating a new array by creating a new array with only the excess length and populating it from the end and then the current array from the end to avoid creating a new array
        String replacingPattern = "%20";
        char[] outputArray = new char[input.length() + countOfSpaces * replacingPattern.length() - countOfSpaces];

        int outputArrayCurrentPosition = 0;
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar == ' ') {
                outputArrayCurrentPosition = populateArrayWIthPatternContents(outputArray, outputArrayCurrentPosition, replacingPattern);
            } else {
                outputArray[outputArrayCurrentPosition++] = currentChar;
            }

        }
        return String.valueOf(outputArray);
    }

    private static int populateArrayWIthPatternContents(char[] outputArray, int outputArrayCurrentPosition, String replacingPattern) {

        for (int i = 0; i < replacingPattern.length(); i++) {
            outputArray[outputArrayCurrentPosition++] = replacingPattern.charAt(i);
        }

        return outputArrayCurrentPosition;
    }


    public static void main(String[] args) {
        assertEquals("%20cat", replacePattern(" cat"));
        assertEquals("cat%20%20cat", replacePattern("cat  cat"));
        assertEquals("cat%20", replacePattern("cat "));

    }


}


/**
 * 1 6 Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees Can you do this in place?
 * ________________________________________________________________pg 101
 * 1 7 Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
 * column is set to 0
 * ________________________________________________________________pg 102
 * 1 8 Assume you have a method isSubstring which checks if one word is a substring of
 * another Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using
 * only one call to isSubstring (i e , “waterbottle” is a rotation of “erbottlewat”)
 * ________________________________________________________________p
 */
