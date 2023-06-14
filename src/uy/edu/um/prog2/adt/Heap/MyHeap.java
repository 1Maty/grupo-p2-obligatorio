package uy.edu.um.prog2.adt.Heap;

import uy.edu.um.prog2.adt.Heap.Exceptions.EmptyHeapException;
public interface MyHeap<T extends Comparable<T>> {
    void insert(T value);
    T deleteMin() throws EmptyHeapException;
    T deleteMax() throws EmptyHeapException;
    int size();
    boolean isEmpty();
    T getMin();
    T getMax();

}

