package uy.edu.um.prog2.adt.linkedlist;

import uy.edu.um.prog2.adt.linkedlist.Exceptions.IlegalIndexException;

public interface MyList<T> {
    void add(T value);

    T get(int position) throws IlegalIndexException;

    int size();

    void remove(int position) throws IlegalIndexException;

    boolean contains(T value) throws IlegalIndexException;

    void addFirst(T value);
}
