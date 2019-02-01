package com.github.n7rider.chapter3;

import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/*
3 5 Implement a MyQueue class which implements a queue using two stacks

 */
public class Ch3_5_Queue_With_Two_Stacks {

    public static void main(String[] args) {

        StacksAsQueue_Simple<String> instance = new StacksAsQueue_Simple();

        assertNull(instance.dequeue());

        instance.queue("a");
        instance.queue("b");
        instance.queue("c");
        instance.queue("d");

        assertEquals("a", instance.dequeue());

        StacksAsQueue_Inspired<String> instance_inspired = new StacksAsQueue_Inspired();

        assertNull(instance_inspired.dequeue());

        instance_inspired.queue("a");
        instance_inspired.queue("b");
        instance_inspired.queue("c");

        assertEquals("a", instance_inspired.dequeue());
        assertEquals("b", instance_inspired.dequeue());

        instance_inspired.queue("d");

        assertEquals("c", instance_inspired.dequeue());

    }

    static class StacksAsQueue_Simple<T> {

        Stack<T> actualStack;
        Stack<T> backupStack;

        public StacksAsQueue_Simple() {
            this.actualStack = new Stack<T>();
            this.backupStack = new Stack<T>();
        }

        public void queue(T t) {
            while (!actualStack.empty()) {
                backupStack.push(actualStack.pop());
            }
            actualStack.push(t);
            while (!backupStack.empty()) {
                actualStack.push(backupStack.pop());
            }
        }

        public T dequeue() {
            return actualStack.empty() ? null : actualStack.pop();
        }
    }

    // This does all the complex work during dequeue. The advantage is that it we in addition to the O(1) insertion time, we get O(1) dequeue time for all items except the first one.
    static class StacksAsQueue_Inspired<T> {

        Stack<T> actualStack;
        Stack<T> backupStack;

        public StacksAsQueue_Inspired() {
            this.actualStack = new Stack<T>();
            this.backupStack = new Stack<T>();
        }

        public void queue(T t) {
            actualStack.push(t);
        }

        public T dequeue() {
            if (backupStack.isEmpty()) {
                while (!actualStack.isEmpty()) {
                    backupStack.push(actualStack.pop());
                }
            }

            if (backupStack.isEmpty()) {
                return null;
            }
            return backupStack.pop();

        }

    }
}
