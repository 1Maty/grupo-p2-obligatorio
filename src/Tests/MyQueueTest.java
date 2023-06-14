package Tests;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.Queue.MyQueue;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedList;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    @Test
    void enqueue() {
        MyQueue<Integer> queue = new MyLinkedList<>();
        queue.enqueue(1);
    }

    @Test
    void dequeue() {
    }

    @Test
    void isEmpty() {
    }
}