package uy.edu.um.prog2.adt.Queue;
import uy.edu.um.prog2.adt.Queue.Exceptions.EmptyQueueException;
public interface MyQueue<T> {
    void enqueue(T element);
    T dequeue () throws EmptyQueueException;
    boolean isEmpty();
    int size();
}
