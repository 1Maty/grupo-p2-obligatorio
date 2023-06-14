package uy.edu.um.prog2.adt.ArrayList;

import uy.edu.um.prog2.adt.ArrayList.excepciones.IlegalIndex;

public interface ArrayList<T> {
    void add(T value);
    T get(int position) throws IlegalIndex;
    void remove(int position) throws IlegalIndex;
    int size();
    boolean isEmpty();
}
