package uy.edu.um.prog2.adt.Hash;

import uy.edu.um.prog2.adt.Hash.Exceptions.HashLleno;

public class MyHashImpl<K, T> implements MyHash<K, T> {
    private int hashSize;
    private int actualSize;
    private HashNode<K, T>[] hash;
    private final double factorcarga = 0.75;


    public MyHashImpl(int initSize) {
        this.hash = new HashNode[initSize];
        this.hashSize = initSize;
        this.initHash();
    }

    private void initHash() {
        for (int i = 0; i < this.hashSize; i++) {
            this.hash[i] = null;
        }

        this.actualSize = 0;
    }

    private int hashCode(K key) {
        int posicionAUbicar = key.hashCode() % this.hashSize;
        while (posicionAUbicar<0){
            posicionAUbicar=posicionAUbicar+this.hashSize;
        }
        return posicionAUbicar;
    }

    public int size() {
        return this.actualSize;
    }

    public boolean isEmpty() {
        return this.actualSize == 0;
    }

    public void remove(K key) {
        int pos= hashCode(key);
        while(hash[pos]!=null && !hash[pos].getKey().equals(key)){
            pos=(pos+1)%hashSize;
        }
        if(hash[pos]!=null){
            hash[pos].setDeleted(true);
            actualSize--;
        }

    }

    public HashNode<K,T> get(K key) {
        int pos = hashCode(key);
        while (hash[pos] != null) {
            if (hash[pos].getKey().equals(key)) {
                if (hash[pos].isDeleted()) {
                    return null;
                } else {
                    return hash[pos];
                }
            }
            pos =(pos+1)%hashSize;
        }
        return null;
    }





    public void add(K key, T value) throws HashLleno {
        if(hashSize==actualSize){
            throw new HashLleno();
        }
        else {
            int posicionAUbicar = hashCode(key);
            for (posicionAUbicar = this.hashCode(key) ; this.hash[posicionAUbicar] != null && !this.hash[posicionAUbicar].isDeleted(); posicionAUbicar = (posicionAUbicar + 1) % this.hashSize) {
                if (this.hash[posicionAUbicar].getKey() == key) {
                    this.hash[posicionAUbicar] = new HashNode(key, value);
                    return;
                }
            }
            this.hash[posicionAUbicar] = new HashNode(key, value);
            this.actualSize++;
        }
    }
}

