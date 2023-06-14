package uy.edu.um.prog2.adt.ArrayList;
import uy.edu.um.prog2.adt.ArrayList.excepciones.IlegalIndex;
public class ArrayListImpl<T> implements ArrayList<T>{

    private T[] arraylist;
    private int initSize;
    private int actualSize;

    public ArrayListImpl(int initSize) {
        this.initSize = initSize;
        this.arraylist = (T[]) new Comparable[initSize];
        this.actualSize = 0;
    }

    public int getActualSize() {
        return actualSize;
    }

    public void setActualSize(int actualSize) {
        this.actualSize = actualSize;
    }

    @Override
    public void add(T value) {
        if (actualSize + 1 < initSize) {
            arraylist[actualSize]= value;
            actualSize++;
        }
        else{
            T[] old= arraylist;
            arraylist= (T[]) new Comparable[2*initSize];
            initSize=2*initSize;
            for (int i=0;i<actualSize;i++){
                arraylist[i]=old[i];
            }
            arraylist[actualSize]= value;
            actualSize++;
        }
    }

    @Override
    public T get(int position) throws IlegalIndex {
        if (position > initSize || position < 0) {
            throw new IlegalIndex();
        } else {
            return arraylist[position];
        }
    }

    @Override
    public void remove(int position) throws IlegalIndex{
        if(position > initSize || position < 0) {
            throw new IlegalIndex();
        }
        else{
            for (int i=position; i<actualSize-1;i++){
                arraylist[i]=arraylist[i+1];
            }
            actualSize--;
        }

    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public boolean isEmpty() {
        boolean booleano=false;
        if(actualSize==0){
            booleano=true;
        }
        return booleano;
    }
}
