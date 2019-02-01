package com.github.n7rider.chapter3;

import java.util.Stack;

/*
3 6 Write a program to sort a stack in ascending order You should not make any assump-
tions about how the stack is implemented The following are the only functions that
should be used to write this program: push | pop | peek | isEmpty
 */
public class Ch3_6_Sorting_A_Stack {

    public static void main(String[] args) {
        StackSorter instance = new StackSorter();
        instance.push(10);
        instance.push(1);
        instance.push(17);
        instance.push(5);
        instance.push(7);
        instance.sort_2Stacks_1NumberAtATime();
        instance.printContents();

        instance.push(10);
        instance.push(1);
        instance.push(17);
        instance.push(5);
        instance.push(7);
        instance.sort_2_stacks_simulateously_big_and_small();
        instance.printContents();

        instance.push(10);
        instance.push(1);
        instance.push(17);
        instance.push(5);
        instance.push(7);
        instance.sort_build_sorted_stack_inspired();
        instance.printContents_fromBackup();


    }

    static class StackSorter {

        Stack<Integer> actualStack = new Stack<>();
        Stack<Integer> backupStack = new Stack<>();

        public void push(Integer entry) {
            actualStack.push(entry);
        }

        public Integer pop() {
            return actualStack.pop();
        }

        public void printContents() {
            while (!actualStack.isEmpty()) {
                System.out.print(actualStack.pop() + " ");
            }
            System.out.println();
        }


        // Targets minimal memory and is simple but process time is more. Can improve performance by adding more stacks and more ifs and then merging those stacks to actualstack
        public void sort_2Stacks_1NumberAtATime() {

            // skip null check, single number checks

            int stackLength = Integer.MAX_VALUE; // or you set something if you want

            do {
                int largest = actualStack.pop();
                int currentStackLength;
                for (currentStackLength = 1; currentStackLength < stackLength && !actualStack.isEmpty(); currentStackLength++) {

                    // Push everything other than the largest into the backup stack
                    Integer currentPoppedElement = actualStack.pop();
                    if (java.lang.Integer.max(currentPoppedElement, largest) == currentPoppedElement) {
                        backupStack.push(largest);
                        largest = currentPoppedElement;
                    } else {
                        backupStack.push(currentPoppedElement);
                    }
                }

                // Now put largest into stack's bottom
                actualStack.push(largest);

                // put everything from backup to actual
                while (!backupStack.isEmpty()) {
                    actualStack.push(backupStack.pop());
                }

                stackLength = currentStackLength - 1;

            } while (stackLength != 1);

        }


        public void sort_2_stacks_simulateously_big_and_small() {

            // Skip null, blank check

            int stackLength = Integer.MAX_VALUE; // or you set something if you want

            do {
                int largest = actualStack.pop();
                int currentStackLength;
                for (currentStackLength = 1; currentStackLength < stackLength && !actualStack.isEmpty(); currentStackLength++) {

                    // Push everything other than the largest into the backup stack
                    Integer currentPoppedElement = actualStack.pop();
                    if (java.lang.Integer.max(currentPoppedElement, largest) == currentPoppedElement) {
                        backupStack.push(largest);
                        largest = currentPoppedElement;
                    } else {
                        backupStack.push(currentPoppedElement);
                    }
                }

                // Now put largest into actual stack's bottom
                actualStack.push(largest);

                stackLength = currentStackLength - 1;

                // put everything other than the smallest from backup to actual
                int smallest = backupStack.pop();
                for (currentStackLength = 1; currentStackLength < stackLength; currentStackLength++) {

                    Integer currentPoppedElement = backupStack.pop();
                    if (java.lang.Integer.min(currentPoppedElement, smallest) == currentPoppedElement) {
                        actualStack.push(smallest);
                        smallest = currentPoppedElement;
                    } else {
                        actualStack.push(currentPoppedElement);
                    }
                }

                // Put smallest into backup stack's bottom
                backupStack.push(smallest);
                stackLength = currentStackLength - 1;

            } while (stackLength != 1);


            // put all smaller ones from backup on top of actual
            while (!backupStack.isEmpty()) {
                actualStack.push(backupStack.pop());
            }
        }

        public void sort_build_sorted_stack_inspired() {

            while (!actualStack.isEmpty()) {
                int element = actualStack.pop();
                if (backupStack.isEmpty() || backupStack.peek() > element) {
                    backupStack.push(element);
                } else {
                    while (!backupStack.isEmpty() && backupStack.peek() < element) {
                        actualStack.push(backupStack.pop());
                    }
                    backupStack.push(element);
                }
            }
        }

        public void printContents_fromBackup() {
            while (!backupStack.isEmpty()) {
                System.out.print(backupStack.pop() + " ");
            }
            System.out.println();
        }
    }
}