package com.github.n7rider.chapter1;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 1.1 Implement an algorithm to determine if a string has all unique characters. What if you can not use additional data structures?
 */

public class Ch1_1_Unique_Characters_in_String {

    /*
    Using data structures - Using a HashSet
     */
    private static boolean checkStringCharactersAreUnique_UsingDataStructure(String input) {
        Set<Character> characterSet = new HashSet<>(input.length());
        for (char currentChar : input.toCharArray()) {
            if (!characterSet.add(currentChar)) {
                return false;
            }
        }
        return true;
    }

    /*
    Option 1 - Sort array and check successive characters are equal
    Option 2a-i - Use an integer/long as a binary index. This can hold a switch for up to 63 characters, so can work for a limited typeset like [A-Za-z] alone.
    Option 2a-ii - Use an integer/long as a binary index. Put 1 in the 2^ith position if you face a character and vice versa. Need array to accommodate all ASCII values
    Option 2b - Use boolean array of size 256 to hold a switch for each possible entry from ASCII
     */
    private static boolean checkStringCharactersAreUnique_WithoutUsingDataStructure_Option1(String input) {
        return selectionSortAndSimultaneouslyCheck(input.toCharArray());

    }

    private static boolean selectionSortAndSimultaneouslyCheck(char[] charArray) {

        for (int i = 0; i < charArray.length - 1; i++) {
            int indexOfSmallest = i;
            for (int j = i + 1; j < charArray.length; j++) {
                if (charArray[j] < charArray[indexOfSmallest]) {
                    indexOfSmallest = j;
                }
            }

            // Move smallest element to the front
            if (indexOfSmallest != i) {
                char temp = charArray[i];
                charArray[i] = charArray[indexOfSmallest];
                charArray[indexOfSmallest] = temp;
            }

            // Check if element already exists
            if (i != 0) {
                if (charArray[i] == charArray[i - 1]) {
                    return false;
                }
            }
        }

        return true;
    }


    private static boolean checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2a_i(String input) {

        long duplicateFinderBitwiseSwitch = 0;

        for (char currentChar : input.toCharArray()) {
            int currentCharAscii = ((int) currentChar);
            long currentCharPositionInSwitch = 1L << (currentCharAscii - 65L);

            long andOpForDuplicateCheck = duplicateFinderBitwiseSwitch & currentCharPositionInSwitch;
            if (andOpForDuplicateCheck != 0) {
                return false;
            } else {
                duplicateFinderBitwiseSwitch = duplicateFinderBitwiseSwitch | currentCharPositionInSwitch;
            }
        }

        return true;
    }

    private static boolean checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2a_ii(String input) {

        /* A long can hold values from 0 to 2^63. Therefore an array can hold 63 characters alone. We need 256/63 = 4.06 ~= 5 elements in the array */
        long[] duplicateFinderBitwiseSwitchArray = new long[Double.valueOf(Math.ceil(256 / 63)).intValue()];

        for (char currentChar : input.toCharArray()) {
            int currentCharAscii = ((int) currentChar);
            int currentCharPositionInSwitchArray = currentCharAscii / 63;
            int currentCharValueInGivenArrayIndex = currentCharAscii % 63;

            long duplicateCheckerNumber = 1L << (long) currentCharValueInGivenArrayIndex;

            long andOpForDuplicateCheck = duplicateFinderBitwiseSwitchArray[currentCharPositionInSwitchArray] & duplicateCheckerNumber;
            if (andOpForDuplicateCheck != 0) {
                return false;
            } else {
                duplicateFinderBitwiseSwitchArray[currentCharPositionInSwitchArray] = duplicateFinderBitwiseSwitchArray[currentCharPositionInSwitchArray] | duplicateCheckerNumber;
            }
        }

        return true;
    }

    private static boolean checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2b(String input) {

        boolean[] duplicatesCheckInformation = new boolean[256];

        for (char currentChar : input.toCharArray()) {
            int asciiValOfCurrentChar = (int) currentChar;

            if (duplicatesCheckInformation[asciiValOfCurrentChar]) {
                return false;
            } else {
                duplicatesCheckInformation[asciiValOfCurrentChar] = !duplicatesCheckInformation[asciiValOfCurrentChar];
            }
        }
        return true;

    }

    public static void main(String[] args) {
        assertTrue(checkStringCharactersAreUnique_UsingDataStructure("abcdefgh"));
        assertFalse(checkStringCharactersAreUnique_UsingDataStructure("abcdefgha"));

        assertTrue(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option1("abcdefgh"));
        assertFalse(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option1("abcdefgha"));
        assertTrue(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option1("ABCDEa"));
        assertFalse(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option1("ABCEDB"));
        assertTrue(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option1("AB[CD^Ea"));
        assertFalse(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option1("A+BC[ED+B"));

        assertTrue(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2a_i("abcdefgh"));
        assertFalse(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2a_i("abcdefgha"));
        assertTrue(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2a_i("ABCDEa"));
        assertFalse(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2a_i("ABCEDB"));
        assertTrue(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2a_i("AB[CD^Ea"));
        assertFalse(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2a_i("A+BC[ED+B"));

        assertTrue(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2a_ii("abcdefgh"));
        assertFalse(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2a_ii("abcdefgha"));
        assertTrue(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2a_ii("ABCDEa"));
        assertFalse(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2a_ii("ABCEDB"));
        assertTrue(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2a_ii("AB[CD^Ea"));
        assertFalse(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2a_ii("A+BC[ED+B"));

        assertTrue(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2b("abcdefgh"));
        assertFalse(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2b("abcdefgha"));
        assertTrue(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2b("ABCDEa"));
        assertFalse(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2b("ABCEDB"));
        assertTrue(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2b("AB[CD^Ea"));
        assertFalse(checkStringCharactersAreUnique_WithoutUsingDataStructure_Option2b("A+BC[ED+B"));

        System.out.println("End of execution");

    }

}
