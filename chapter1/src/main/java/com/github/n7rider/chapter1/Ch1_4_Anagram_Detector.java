package com.github.n7rider.chapter1;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Ch1_4_Anagram_Detector {

    /**
     * Write a method to decide if two strings are anagrams or not
     */


    private static boolean isAnagram(String string1, String string2) {

        // Skip checking nulls & blanks

        if (string1.length() != string2.length()) {
            return false;
        }

        char[] charArray1 = string1.toCharArray();
        char[] charArray2 = string2.toCharArray();

        // Skipping doing quick sort manually. We have a chapter that deals with sort related problems
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        return String.valueOf(charArray1).equals(String.valueOf(charArray2));
    }

    public static void main(String[] args) {
        assertTrue(isAnagram("abcd", "dcba"));
        assertTrue(isAnagram("abcda", "cbaad"));
        assertFalse(isAnagram("edcbaX", "y"));
        assertFalse(isAnagram("edcba", "aabcde"));

    }

}