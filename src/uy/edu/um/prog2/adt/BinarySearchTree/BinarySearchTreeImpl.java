package uy.edu.um.prog2.adt.BinarySearchTree;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedList;
import uy.edu.um.prog2.adt.linkedlist.MyList;


public class BinarySearchTreeImpl<T extends Comparable<T>,V> implements BinarySearchTree<T,V> {

    private TreeNode<T,V> root;

    @Override
    public void add(T key,V valor) {
        root= insert(key,valor,root);
    }

    private TreeNode<T,V> insert(T key,V valor, TreeNode<T,V> root){
        TreeNode<T,V> result= null;
        if(root==null){
            TreeNode<T,V> newNode= new TreeNode<>(key,valor);
            result= newNode;
        }
        else if(key.compareTo(root.getKey())>0){
            root.setRight(insert(key,valor,root.getRight()));
            result=root;
        }
        else if(key.compareTo(root.getKey())<0){
            root.setLeft(insert(key,valor,root.getLeft()));
            result=root;
        }
        return result;
    }

    @Override
    public void remove(T key) {
        root=delete(key,root);
    }

    private TreeNode<T,V> delete(T key, TreeNode<T,V> root){
        TreeNode<T,V> result=root;
        if(root==null){
        }
        else if(key.compareTo(root.getKey())<0){
            root.setLeft(delete(key,root.getLeft()));
        }
        else if(key.compareTo(root.getKey())>0){
            root.setRight(delete(key,root.getRight()));
        }
        else{
            if(root.getRight()==null && root.getLeft()==null){
                result=null;
            }
            else if(root.getLeft()!=null){
                TreeNode<T,V> candidate= getMax(root.getLeft());
                root.setValue(candidate.getValue());
                root.setKey(candidate.getKey());
                root.setLeft(delete(candidate.getKey(),root.getLeft()));
            }
            else{
                TreeNode<T,V> candidate= getMin(root.getRight());
                root.setValue(candidate.getValue());
                root.setKey(candidate.getKey());
                root.setRight(delete(candidate.getKey(),root.getRight()));
            }
        }
        return result;
    }
    private TreeNode<T,V> getMin(TreeNode<T,V> root){
        TreeNode<T,V> result=null;
        if(root.getLeft()==null){result=root;}
        else{result=getMin(root.getLeft());}
        return result;
    }
    private TreeNode<T,V> getMax(TreeNode<T,V> root){
        TreeNode<T,V> result=null;
        if(root.getRight()==null){result=root;}
        else{result=getMax(root.getRight());}
        return result;
    }


    @Override
    public boolean contains(T key) {
        return search(this.root,key);
    }

    private boolean search(TreeNode<T,V> root, T key) {
        if (root == null) {
            return false;
        } else if (root.getKey().compareTo(key)==0) {
            return true;
        } else if (root.getKey().compareTo(key)>0) {
            return search(root.getLeft(), key);
        }
        return search(root.getRight(), key);
    }

    @Override
    public TreeNode<T,V> find(T key) {
        TreeNode<T,V> nodoencontrado= findA(key,root);
        return nodoencontrado;
    }
    private TreeNode<T,V> findA(T key, TreeNode<T,V> root) {
        if (root == null) { return null;
        } else if (root.getKey().compareTo(key)==0) {
            return root;
        } else if (root.getKey().compareTo(key)>0) {
            return findA(key, root.getLeft());
        }
        return findA(key,root.getRight());
    }


    @Override
    public MyLinkedList<TreeNode<T,V>> preOrder() {
        MyLinkedList<TreeNode<T,V>> lista= new MyLinkedList<>();
        return preOrder(lista, root);
    }
    private MyLinkedList<TreeNode<T,V>> preOrder(MyLinkedList<TreeNode<T,V>> lista,TreeNode<T,V> root){
        if(root!=null){
            lista.add(root);
            preOrder(lista,root.getLeft());
            preOrder(lista,root.getRight());
        }
        return lista;
    }

    @Override
    public MyLinkedList<TreeNode<T,V>> inOrder() {
        MyLinkedList<TreeNode<T,V>> lista= new MyLinkedList<>();
        return inOrder(lista,root);
    }
    private MyLinkedList<TreeNode<T,V>> inOrder(MyLinkedList<TreeNode<T,V>> lista,TreeNode<T,V> root){
        if(root.getLeft()!=null){
            inOrder(lista,root.getLeft());}
        lista.add(root);
        if(root.getRight()!=null){
            inOrder(lista,root.getRight());
        }
        return lista;
    }
    @Override
    public MyLinkedList<TreeNode<T,V>> postOrder() {
        MyLinkedList<TreeNode<T,V>> lista= new MyLinkedList<>();
        return postOrder(lista,root);
    }

    private MyLinkedList<TreeNode<T,V>> postOrder(MyLinkedList<TreeNode<T,V>> lista,TreeNode<T,V> root){
        if(root.getLeft()!=null){
            postOrder(lista,root.getLeft());
        }
        if(root.getRight()!=null){
            postOrder(lista,root.getRight());
        }
        lista.add(root);
        return lista;
    }

}
