package tree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> {
    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public String preOrder() {
        return new String(preOrder(root));
    }

    private StringBuilder preOrder(BinaryNode<T> node) {
        if (node == null)
            return new StringBuilder();

        StringBuilder res = new StringBuilder();
        res.append(node.value).append(" ");
        res.append(preOrder(node.left));
        res.append(preOrder(node.right));
        return res;
    }

    public String inOrder() {
        return new String(inOrder(root));
    }

    private StringBuilder inOrder(BinaryNode<T> node) {
        if (node == null)
            return new StringBuilder();

        StringBuilder res = new StringBuilder();
        res.append(inOrder(node.left));
        res.append(node.value).append(" ");
        res.append(inOrder(node.right));
        return res;
    }

    public String postOrder() {
        return new String(postOrder(root));
    }

    private StringBuilder postOrder(BinaryNode<T> node) {
        if (node == null)
            return new StringBuilder();

        StringBuilder res = new StringBuilder();
        res.append(postOrder(node.left));
        res.append(postOrder(node.right));
        res.append(node.value).append(" ");
        return res;
    }

    public String levelOrder() {
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode<T> presentNode = queue.remove();
            res.append(presentNode.value).append(" ");
            if (presentNode.left != null) {
                queue.add(presentNode.left);
            }
            if (presentNode.right != null) {
                queue.add(presentNode.right);
            }
        }
        return new String(res);
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    private BinaryNode<T> insert(BinaryNode<T> currentNode, T value) {
        if (currentNode == null) {
            return new BinaryNode<T>(value);
        } else if (value.compareTo(currentNode.value) < 0) {
            currentNode.left = insert(currentNode.left, value);
        } else if (value.compareTo(currentNode.value) > 0) {
            currentNode.right = insert(currentNode.right, value);
        } else throw new IllegalArgumentException("This value already exists in tree");
        return currentNode;
    }

    public BinaryNode<T> search(T value) {
        return search(root, value);
    }

    private BinaryNode<T> search(BinaryNode<T> node, T value) {
        if (node == null) {
            throw new NoSuchElementException("There is not element with this value");
        } else if (node.value.equals(value)) {
            return node;
        } else if (value.compareTo(node.value) < 0) {
            return search(node.left, value);
        } else {
            return search(node.right, value);
        }
    }

    public void delete(T value) {
        delete(root, value);
    }

    private BinaryNode<T> delete(BinaryNode<T> node, T value) {
        if (node == null) {
            return null;
        }
        if (value.compareTo(node.value) < 0) {
            node.left = delete(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = delete(node.right, value);
        } else {  //node.value == value
            if (node.left != null && node.right != null) {
                BinaryNode<T> successor = successor(node.right);
                node.value = successor.value;
                node.right = delete(node.right, successor.value);
            } else if (node.left != null) {
                node = node.left;
            } else if (node.right != null) {
                node = node.right;
            } else {
                node = null;
            }
        }
        return node;
    }

    private BinaryNode<T> successor(BinaryNode<T> root) {
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
