package uy.edu.um.prog2.adt.Stack;
import uy.edu.um.prog2.adt.Stack.Exceptions.EmptyStackException;
public interface MyStack<T> {
    void pop () throws EmptyStackException;
    T top() throws EmptyStackException;
    void push(T element);
    boolean isEmpty ();
    void makeEmpty() throws EmptyStackException;
    int size();
}
