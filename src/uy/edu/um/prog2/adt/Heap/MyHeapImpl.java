package uy.edu.um.prog2.adt.Heap;

import uy.edu.um.prog2.adt.Heap.Exceptions.EmptyHeapException;

public class MyHeapImpl<T extends Comparable<T>> implements MyHeap<T> {
    private T[] heap;
    private int size;
    private boolean isHeapMax;
    private int maxsize;
    private static final int FRONT = 1;

    public MyHeapImpl(int maxsize, boolean isHeapMax) {
        this.maxsize = maxsize;
        this.isHeapMax = isHeapMax;
        this.heap = (T[]) new Comparable[maxsize + 1];
        this.size = 0;
    }

    private int getFatherIndex(int childIndex) {
        return childIndex / 2;
    }

    private int getLeftChildIndex(int fatherIndex) {
        return 2 * fatherIndex;
    }

    private int getRightChildIndex(int fatherIndex) {
        return 2 * fatherIndex + 1;
    }

    private boolean isLeaf(int pos) {
        return pos > this.size / 2 && pos <= this.size;
    }

    private void swap(int fpos, int spos) {
        T tmp = this.heap[fpos];
        this.heap[fpos] = this.heap[spos];
        this.heap[spos] = tmp;
    }

    public void insert(T value) {
        if (this.size < this.maxsize) {
            if (this.size == 0) {
                this.heap[0] = value;
            }

            int current;
            if (!this.isHeapMax) {
                this.heap[++this.size] = value;

                for(current = this.size; this.heap[current].compareTo(this.heap[this.getFatherIndex(current)]) < 0 && current != 0; this.heap[0] = this.heap[1]) {
                    this.swap(current, this.getFatherIndex(current));
                    current = this.getFatherIndex(current);
                }
            } else {
                this.heap[++this.size] = value;

                for(current = this.size; this.heap[current].compareTo(this.heap[this.getFatherIndex(current)]) > 0 && current != 0; this.heap[0] = this.heap[1]) {
                    this.swap(current, this.getFatherIndex(current));
                    current = this.getFatherIndex(current);
                }
            }

        }
    }

    public T deleteMin() throws EmptyHeapException {
        if (this.size == 0) {
            throw new EmptyHeapException();
        } else {
            T eliminado = this.heap[1];
            this.heap[1] = this.heap[this.size--];
            this.minHeapify(1);
            return eliminado;
        }
    }

    public T deleteMax() throws EmptyHeapException {
        if (this.size == 0) {
            throw new EmptyHeapException();
        } else {
            T eliminado = this.heap[1];
            this.heap[1] = this.heap[this.size--];
            this.maxHeapify(1);
            return eliminado;
        }
    }

    private void minHeapify(int pos) {
        if (!this.isLeaf(pos)) {
            if (this.heap[pos].compareTo(this.heap[this.getLeftChildIndex(pos)]) > 0 || this.heap[pos].compareTo(this.heap[this.getRightChildIndex(pos)]) > 0) {
                if (this.heap[this.getLeftChildIndex(pos)].compareTo(this.heap[this.getRightChildIndex(pos)]) < 0) {
                    this.swap(pos, this.getLeftChildIndex(pos));
                    this.minHeapify(this.getLeftChildIndex(pos));
                } else {
                    this.swap(pos, this.getRightChildIndex(pos));
                    this.minHeapify(this.getRightChildIndex(pos));
                }
            }

            this.heap[0] = this.heap[1];
        }

    }

    private void maxHeapify(int pos) {
        if (!this.isLeaf(pos)) {
            if (this.heap[pos].compareTo(this.heap[this.getLeftChildIndex(pos)]) < 0 || this.heap[pos].compareTo(this.heap[this.getRightChildIndex(pos)]) < 0) {
                if (this.heap[this.getLeftChildIndex(pos)].compareTo(this.heap[this.getRightChildIndex(pos)]) > 0) {
                    this.swap(pos, this.getLeftChildIndex(pos));
                    this.maxHeapify(this.getLeftChildIndex(pos));
                } else {
                    this.swap(pos, this.getRightChildIndex(pos));
                    this.maxHeapify(this.getRightChildIndex(pos));
                }
            }

            this.heap[0] = this.heap[1];
        }

    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T getMin() {
        return this.heap[1];
    }

    public T getMax() {
        return this.heap[1];
    }
}