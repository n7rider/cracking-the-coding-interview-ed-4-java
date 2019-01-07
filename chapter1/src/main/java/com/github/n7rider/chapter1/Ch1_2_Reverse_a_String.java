
package com.github.n7rider.chapter1;

import static org.junit.Assert.assertEquals;

/**
 * 1.2 Write code to reverse a C-Style String (C-String means that “abcd” is represented as
 * five characters, including the null character )
 */
public class Ch1_2_Reverse_a_String {

    private static String reverseCString(String input) {
        char[] inputCharArray = input.toCharArray();
        int stringContentEndIndex = inputCharArray.length - 2;
        for (int i = 0; i <= stringContentEndIndex / 2; i++) {
            char temp = inputCharArray[i];
            inputCharArray[i] = inputCharArray[stringContentEndIndex - i];
            inputCharArray[stringContentEndIndex - i] = temp;
        }

        return String.valueOf(inputCharArray);
    }

    // Skipping the pointer based gotcha in this C based question


    public static void main(String[] args) {
        assertEquals("dcbaX", reverseCString("abcdX"));
        assertEquals("edcbaX", reverseCString("abcdeX"));

    }
}
