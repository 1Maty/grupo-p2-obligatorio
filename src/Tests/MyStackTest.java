package Tests;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.Stack.Exceptions.EmptyStackException;
import uy.edu.um.prog2.adt.Stack.MyStack;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedList;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    @Test
    void pop() throws EmptyStackException {
        MyStack<Integer> stack = new MyLinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.pop();
        assertEquals(stack.top(),3);

    }

    @Test
    void top() throws EmptyStackException {
        MyStack<Integer> stack = new MyLinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(4,stack.top());
    }

    @Test
    void push() throws EmptyStackException {
        MyStack<Integer> stack = new MyLinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(4,stack.top());
    }

    @Test
    void isEmpty() throws EmptyStackException {
        MyStack<Integer> stack = new MyLinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertFalse(stack.isEmpty());
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void makeEmpty() throws EmptyStackException {
        MyStack<Integer> stack = new MyLinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.makeEmpty();
        assertEquals(0,stack.size());
    }
}