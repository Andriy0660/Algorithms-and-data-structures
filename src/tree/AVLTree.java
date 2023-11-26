package tree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class AVLTree <T extends Comparable<T>>{
    private BinaryNode<T> root;

    public AVLTree() {
        this.root = null;
    }
    private int getBalance(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }
    private int getHeight(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    public String preOrder(){
        return new String(preOrder(root));
    }
    private StringBuilder preOrder(BinaryNode<T> node){
        if(node==null)
            return new StringBuilder();

        StringBuilder res = new StringBuilder();
        res.append(node.value).append(" ");
        res.append(preOrder(node.left));
        res.append(preOrder(node.right));
        return res;
    }

    public String inOrder(){
        return new String(inOrder(root));
    }
    private StringBuilder inOrder(BinaryNode<T> node){
        if(node==null)
            return new StringBuilder();

        StringBuilder res = new StringBuilder();
        res.append(inOrder(node.left));
        res.append(node.value).append(" ");
        res.append(inOrder(node.right));
        return res;
    }

    public String postOrder(){
        return new String(postOrder(root));
    }
    private StringBuilder postOrder(BinaryNode<T> node){
        if(node==null)
            return new StringBuilder();

        StringBuilder res = new StringBuilder();
        res.append(postOrder(node.left));
        res.append(postOrder(node.right));
        res.append(node.value).append(" ");
        return res;
    }

    public String levelOrder(){
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        queue.add(root);
        while(!queue.isEmpty()){
            BinaryNode<T> presentNode = queue.remove();
            res.append(presentNode.value).append(" ");
            if(presentNode.left!=null){
                queue.add(presentNode.left);
            }
            if(presentNode.right!=null){
                queue.add(presentNode.right);
            }
        }
        return new String(res);
    }
    public BinaryNode<T> search(T value){
        return search(root,value);
    }
    private BinaryNode<T> search(BinaryNode<T> node, T value) {
        if (node == null ) {
            throw new NoSuchElementException("There is not element with this value");
        } else if (node.value.equals(value)) {
            return node;
        } else if (value.compareTo(node.value)<0) {
            return search(node.left, value);
        } else {
            return search(node.right, value);
        }
    }

    private BinaryNode<T> rightRotate(BinaryNode<T> unbalancedNode) {
        BinaryNode<T> newRoot = unbalancedNode.left;
        unbalancedNode.left = unbalancedNode.left.right;
        newRoot.right = unbalancedNode;
        unbalancedNode.height = 1 + Math.max(getHeight(unbalancedNode.left), getHeight(unbalancedNode.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    private BinaryNode<T> leftRotate(BinaryNode<T> unbalancedNode) {
        BinaryNode<T> newRoot = unbalancedNode.right;
        unbalancedNode.right = unbalancedNode.right.left;
        newRoot.left = unbalancedNode;
        unbalancedNode.height = 1 + Math.max(getHeight(unbalancedNode.left), getHeight(unbalancedNode.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    public void insert(T value) {
        root = insertNode(root, value);
    }
    private BinaryNode<T> insertNode(BinaryNode<T> node, T value) {
        if (node == null) {
            return new BinaryNode<T>(value);
        } else if (value.compareTo(node.value)<0) {
            node.left = insertNode(node.left, value);
        } else if(value.compareTo(node.value)>0){
            node.right = insertNode(node.right, value);
        } else throw new IllegalArgumentException("This value already exists in tree");

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if (balance > 1) {
            // Відхил вліво
            if (value.compareTo(node.left.value)<0) {
                return rightRotate(node);
            } else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        if (balance < -1) {
            // Відхил вправо
            if (value.compareTo(node.right.value)>0) {
                return leftRotate(node);
            } else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    public void delete(T value) {
        root = deleteNode(root, value);
    }
    public BinaryNode<T> deleteNode(BinaryNode<T> node, T value) {
        if (node == null)
            return node;
        if (value.compareTo(node.value)<0) {
            node.left = deleteNode(node.left, value);
        } else if (value.compareTo(node.value)>0) {
            node.right = deleteNode(node.right, value);
        } else { //node.value == value
            if (node.left != null && node.right != null) {
                BinaryNode<T> minNodeForRight = successor(node.right);
                node.value = minNodeForRight.value;
                node.right = deleteNode(node.right, minNodeForRight.value);
            } else if (node.left != null) {
                node = node.left;
            } else if (node.right != null) {
                node = node.right;
            } else {
                return null;
            }
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node);

        if (balance > 1) {
            // Відхил вліво
            if (value.compareTo(node.left.value)<0) {
                return rightRotate(node);
            } else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        if (balance < -1) {
            // Відхил вправо
            if (value.compareTo(node.right.value)>0) {
                return leftRotate(node);
            } else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }
    public BinaryNode<T> successor(BinaryNode<T> root) {
        if (root.left == null) {
            return root;
        } else {
            return successor(root.left);
        }
    }
    public void deleteTree() {
        root = null;
    }
}
