/*
1.1 Implement an algorithm to determine if a string has all uniqur characters. What if you can not use additional data structures?
 */
package com.github.n7rider.chapter1;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Ch1_1_Unique_Characters_in_String {

    private static boolean checkStringCharactersAreUnique_UsingDataStructure(String input) {
        Set<Character> characterSet = new HashSet<>(input.length());
        for(char currentChar: input.toCharArray()) {
            if(!characterSet.add(currentChar)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assertTrue(checkStringCharactersAreUnique_UsingDataStructure("abcdefgh"));
        assertFalse(checkStringCharactersAreUnique_UsingDataStructure("abcdefgha"));

        System.out.println("End of execution");

    }

}
