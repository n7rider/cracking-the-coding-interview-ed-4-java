package com.github.n7rider.chapter1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Ch1_8_Subset_String_Check {

    /**
     * Assume you have a method isSubstring which checks if one word is a substring of another.
     * Given two strings s1 and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring (i.e., "waterbottle" is a rotation of "erbottlewat"
     **/

    private static boolean isSubString(String actualStringInput, String testStringInput) {

        // Skipping null check, blank check, equal length validation

        // Using char array to avoid usage of inbuilt text processors
        char[] actualString = actualStringInput.toCharArray();
        char[] testString = testStringInput.toCharArray();

        // Find first character of actual in given. Then do equals check for the length of the string
        char firstCharOfActual = actualString[0];

        for (int i = 0; i < testStringInput.length(); i++) {
            if (testString[i] == firstCharOfActual) {
                int j = 1;
                for (j = 1; j < testStringInput.length(); j++) {
                    if (actualString[j] != testString[(i + j) % testStringInput.length()]) {
                        break;
                    }
                }
                if (j == testStringInput.length()) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Puts two copies of the string juxtaposed and tries to find a substring
     * * Mine does the same but handles things in terms of characters
     *
     * @param actualStringInput
     * @param testStringInput
     * @return
     */
    private static boolean isSubString_inspired(String actualStringInput, String testStringInput) {
        testStringInput += testStringInput;
        return testStringInput.contains(actualStringInput);
    }

    public static void main(String[] args) {

        assertTrue(isSubString("waterbottle", "erbottlewat"));
        assertFalse(isSubString("waterbottle", "erbottlawat"));
        assertTrue(isSubString("ttha", "that"));
        assertFalse(isSubString("main", "niam"));

        assertTrue(isSubString_inspired("waterbottle", "erbottlewat"));
        assertFalse(isSubString_inspired("waterbottle", "erbottlawat"));
        assertTrue(isSubString_inspired("ttha", "that"));
        assertFalse(isSubString_inspired("main", "niam"));
    }
}