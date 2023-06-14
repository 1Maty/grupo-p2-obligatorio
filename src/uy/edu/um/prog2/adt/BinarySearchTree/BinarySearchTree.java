package uy.edu.um.prog2.adt.BinarySearchTree;

import uy.edu.um.prog2.adt.linkedlist.MyLinkedList;

public interface BinarySearchTree <T extends Comparable<T>,V>{
    void add(T key,V value);
    void remove(T valor);
    boolean contains(T valor);
    TreeNode<T,V> find(T valor);
    MyLinkedList<TreeNode<T,V>> preOrder();
    MyLinkedList<TreeNode<T,V>> inOrder();
    MyLinkedList<TreeNode<T,V>> postOrder();

}
