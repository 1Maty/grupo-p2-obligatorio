package uy.edu.um.prog2.adt.linkedlist;

import uy.edu.um.prog2.adt.Queue.Exceptions.EmptyQueueException;
import uy.edu.um.prog2.adt.Queue.MyQueue;
import uy.edu.um.prog2.adt.Stack.MyStack;
import uy.edu.um.prog2.adt.Stack.Exceptions.EmptyStackException;
import uy.edu.um.prog2.adt.linkedlist.Exceptions.IlegalIndexException;

public class MyLinkedList<T> implements MyList<T>, MyStack<T>, MyQueue<T> {

    private Node<T> first;
    private Node<T> last;
    private int size = 0;

    public MyLinkedList() {
        first = null;
        last = null;
    }

    public Node getFirst() {
        return first;
    }

    @Override
    public void add(T value) {
        Node<T> nuevoNodo = new Node<T>(value);
        if (first == null) {
            first = nuevoNodo;
            size++;
            return;
        } else {
            Node<T> nodoTemp = first;
            while (nodoTemp.getNext() != null) {
                nodoTemp = nodoTemp.getNext();
            }
            nodoTemp.setNext(nuevoNodo);
            size++;
            return;
        }
    }

    @Override
    public void remove(int index) throws IlegalIndexException {
        if (first != null) {
            if (index == 0) {
                Node<T> nuevoPrimero = first.getNext();
                first = nuevoPrimero;
                size--;
                return;
            }
            Node<T> nodoAEliminar = findNode(index);
            index--;
            if (nodoAEliminar != null) {
                Node<T> nodoAnterior = findNode(index);
                nodoAnterior.setNext(nodoAEliminar.getNext());
                size--;
                return;
            }
        }
        throw new IlegalIndexException();
    }

    public Node<T> findNode(int index) {
        if (first != null) {
            if (index >= 0 && size >= index) {
                Node<T> result = first;
                while (index != 0) {
                    result = result.getNext();
                    index--;
                }
                return result;
            }
        }
        return null;
    }

    @Override
    public T get(int index) throws IlegalIndexException {
        if (first != null) {
            if (index >= 0 && size > index) {
                int cont = 0;
                Node<T> result = first;
                while (cont < index) {
                    result = result.getNext();
                    cont++;
                }
                return result.getValue();
            } else {
                throw new IlegalIndexException();
            }
        }
        throw new IlegalIndexException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T value) throws IlegalIndexException {
        int contador = 0;
        boolean booleano = false;
        int i = 0;
        int size = this.size;
        while (contador < size) {
            T temp = this.get(contador);
            contador++;
            if (temp.equals(value)) {
                i++;
            }
        }
        if (i >= 1) {
            booleano = true;
        }
        return booleano;
    }

    @Override
    public void addFirst(T value) {
        if (first == null) {
            Node<T> temp = new Node<T>(value);
            first = temp;
            size++;
        } else {
            Node<T> nodo = new Node<T>(value);
            nodo.setNext(first);
            first = nodo;
            size++;
        }
    }


    @Override
    public void pop() throws EmptyStackException {
        if (first != null) {
            Node<T> temp = first;
            Node<T> nuevoPrimero = temp.getNext();
            if (first != last) {
                first = nuevoPrimero;
                return;
            } else {
                first = null;
                last = null;
                return;
            }
        }
        throw new EmptyStackException();
    }

    @Override
    public T top() throws EmptyStackException {
        if(first != null){
            return first.getValue();
        }
        throw new EmptyStackException();
    }

    @Override
    public void push(T value) {
        Node<T> nuevo = new Node<T>(value);
        if (first != null) {
            Node<T> temp = first;
            nuevo.setNext(first);
            first = nuevo;
            size++;
            return;
        }
        first = nuevo;
        last = nuevo;
        size++;
    }

    @Override
    public void enqueue(T element) {
        Node temp = new Node(element);

        if (first == null) {
            first = temp;
        }

        if (last != null) {
            last.setNext(temp);
        }
        last = temp;
        size++;
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        T valor = null;
        Node temp = first;
        if (first == null) {
            valor = null;
        } else {
            first = temp.getNext();
            temp.setNext(null);
            valor = (T) temp.getValue();
            size--;
        }
        return valor;
    }

    @Override
    public boolean isEmpty() {
        boolean empty = false;
        if (first == null) {
            empty = true;
        }
        return empty;
    }

    @Override
    public void makeEmpty() throws EmptyStackException {
        if(first != null) {
            int tamano = this.size();
            for (int i = 0; i < tamano; i++) {
                this.pop();
                size--;
            }
            return;
        }
        throw new EmptyStackException();
    }
}

