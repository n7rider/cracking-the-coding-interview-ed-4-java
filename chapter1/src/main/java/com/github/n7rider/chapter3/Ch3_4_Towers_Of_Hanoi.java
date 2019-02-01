package com.github.n7rider.chapter3;

import java.util.Stack;

/*
3 4 In the classic problem of the Towers of Hanoi, you have 3 rods and N disks of different
sizes which can slide onto any tower The puzzle starts with disks sorted in ascending
order of size from top to bottom (e g , each disk sits on top of an even larger one) You
have the following constraints:
(A) Only one disk can be moved at a time
(B) A disk is slid off the top of one rod onto the next rod
(C) A disk can only be placed on top of a larger disk
Write a program to move the disks from the first rod to the last using Stacks

 */
public class Ch3_4_Towers_Of_Hanoi {

    public static void main(String[] args) {

        Mover mover = new Mover(5);
        mover.printAllStacks();

// specifies how many discs to move
        mover.move_min(5, 0, 2, 1);
        mover.printAllStacks();
    }

    static class Mover {

        int count;
        Stack[] nodes = new Stack[3];

        Mover(int count) {
            this.count = count;

            nodes[0] = new Stack();
            nodes[1] = new Stack();
            nodes[2] = new Stack();

            for (int i = 0; i < count; i++) {
                nodes[0].push(count - 1 - i);
            }
        }

        public void move_min(int count, int from, int to, int intermediate) {

            // move N-1 discs from origin to intermediate disc
            moveIndividualDiscs(count - 1, from, intermediate, to);

            // nove Nth disc from origin to destination
            moveIndividualDiscs(1, from, to, intermediate);

            // move N-1 discs from intermediate to destination
            moveIndividualDiscs(count - 1, intermediate, to, from);
        }

        private void moveIndividualDiscs(int count, int from, int intermediate, int to) {

            if (count == 1) {
                System.out.println("Pushing " + nodes[from].peek() + " from node " + (from) + " to " + (intermediate));
                nodes[intermediate].push(nodes[from].pop());
                return;
            }

            move_min(count, from, intermediate, to);
        }

        public void printAllStacks() {

            for (int i = 0; i < 3; i++) {
                System.out.print("Stack " + i + " : ");
                System.out.println(nodes[i].toString());
            }
        }

    }
}
