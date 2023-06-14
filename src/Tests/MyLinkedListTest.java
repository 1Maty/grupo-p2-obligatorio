package Tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import uy.edu.um.prog2.adt.linkedlist.*;
import uy.edu.um.prog2.adt.linkedlist.Exceptions.IlegalIndexException;

class MyLinkedListTest {

    @Test
    void add() throws IlegalIndexException {
        MyLinkedList<Integer> lista = new MyLinkedList<>();
        lista.add(1);
        lista.add(2);
        assertEquals(1,lista.get(0));
        assertEquals(2,lista.get(1));
    }

    @Test
    void remove() throws IlegalIndexException {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.remove(3);
        assertEquals(4,list.size());
    }

    @Test
    void get() throws IlegalIndexException {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertEquals(list.get(2),2);
    }
}