package uy.edu.um.prog2.adt.Hash;

import uy.edu.um.prog2.adt.Hash.Exceptions.HashLleno;

public interface MyHash<K,T> {
    int size();
    boolean isEmpty();
    void remove(K key);
    HashNode<K,T> get(K key);
    void add(K key, T value) throws HashLleno;
}

