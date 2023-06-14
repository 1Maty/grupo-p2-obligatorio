package uy.edu.um.prog2.adt.BinarySearchTree;

public class TreeNode <T extends Comparable<T>,V>{

    private T key;
    private V value;
    private TreeNode<T,V> left;
    private TreeNode<T,V> right;

    public TreeNode(T key,V value){
        this.key=key; this.value=value;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public TreeNode<T, V> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T, V> left) {
        this.left = left;
    }

    public TreeNode<T, V> getRight() {
        return right;
    }

    public void setRight(TreeNode<T, V> right) {
        this.right = right;
    }
}
