package tree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinaryTree <T extends Comparable<T>> {
    private BinaryNode<T> root;

    public BinaryTree() {
        root = null;
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
    public BinaryNode<T> exists(Integer value){
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            BinaryNode<T> presentNode = queue.remove();
            if(presentNode.value.equals(value)) return presentNode;
            if(presentNode.left!=null){
                queue.add(presentNode.left);
            }
            if(presentNode.right!=null){
                queue.add(presentNode.right);
            }
        }
        throw new NoSuchElementException("There is not element with this value");
    }
    public void insert(T value){
        BinaryNode<T> newNode = new BinaryNode<T>(value);
        if(root == null) {
            root = newNode;
            return;
        }
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            BinaryNode<T> presentNode = queue.remove();
            if(presentNode.left==null){
                presentNode.left = newNode;
                return;
            }
            else if(presentNode.right==null){
                presentNode.right = newNode;
                return;
            }
            queue.add(presentNode.left);
            queue.add(presentNode.right);
        }
    }
    public void delete(T value) {
        Queue<BinaryNode<T>> queue = new LinkedList<BinaryNode<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode<T> presentNode = queue.remove();
            if (presentNode.value.equals(value)) {
                presentNode.value = getDeepestNode().value;
                deleteDeepestNode();
                return;
            } else {
                if (presentNode.left != null) queue.add(presentNode.left);
                if (presentNode.right != null) queue.add(presentNode.right);
            }
        }
    }
    private BinaryNode<T> getDeepestNode() {
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(root);
        BinaryNode<T> presentNode = null;
        while(!queue.isEmpty()){
            presentNode = queue.remove();
            if(presentNode.left!=null){
                queue.add(presentNode.left);
            }
            if(presentNode.right!=null){
                queue.add(presentNode.right);
            }
        }
        return presentNode;
    }
    private void deleteDeepestNode() {
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(root);
        BinaryNode<T> previousNode = null, presentNode = null;
        while(!queue.isEmpty()){
            previousNode = presentNode;
            presentNode = queue.remove();
            if(presentNode.left==null){
                previousNode.right=null;
                return;
            }
            else if(presentNode.right==null){
                presentNode.left=null;
                return;
            }
            queue.add(presentNode.left);
            queue.add(presentNode.right);
        }
    }
    public void deleteTree() {
        root = null;
    }
}
