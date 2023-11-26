package tree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;

public class RedBlackTree<T extends Comparable<T>> {
    static class RedBlackNode<T extends Comparable<T>> {
        T value;
        RedBlackNode<T> left;
        RedBlackNode<T> right;
        RedBlackNode<T> parent;
        
        //red == false
        //black == true
        boolean color;

        public RedBlackNode() {
            value = null;
            color = true;
        }

        public RedBlackNode(T value) {
            this.value = value;
            color = false;
            left = new RedBlackNode<T>();
            right = new RedBlackNode<T>();
        }

        public boolean isLeftChild() {
            return this == parent.left;
        }

        public void flipColor() {
            color = !color;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RedBlackNode<?> that = (RedBlackNode<?>) o;
            return color == that.color && Objects.equals(value, that.value) && Objects.equals(left, that.left) && Objects.equals(right, that.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, left, right, color);
        }

    }
    static final boolean RED = false;
    static final boolean BLACK = true;
    private RedBlackNode<T> root;
    private final RedBlackNode<T> NIL;

    public RedBlackTree() {
        root = new RedBlackNode<T>();
        NIL = new RedBlackNode<T>();
    }
    public String preOrder(){
        return new String(preOrder(root));
    }
    private StringBuilder preOrder(RedBlackNode<T> node){
        if(node.equals(NIL))
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
    private StringBuilder inOrder(RedBlackNode<T> node){
        if(node.equals(NIL))
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
    private StringBuilder postOrder(RedBlackNode<T> node){
        if(node.equals(NIL))
            return new StringBuilder();

        StringBuilder res = new StringBuilder();
        res.append(postOrder(node.left));
        res.append(postOrder(node.right));
        res.append(node.value).append(" ");
        return res;
    }

    public String levelOrder(){
        Queue<RedBlackNode<T>> queue = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        queue.add(root);
        while(!queue.isEmpty()){
            RedBlackNode<T> presentNode = queue.remove();
            res.append(presentNode.value).append(" ");
            System.out.println(presentNode.value +" "+ presentNode.color);
            if(!presentNode.left.equals(NIL)){
                queue.add(presentNode.left);
            }
            if(!presentNode.right.equals(NIL)){
                queue.add(presentNode.right);
            }
        }
        return new String(res);
    }
    public RedBlackNode<T> search(T value){
        return search(root,value);
    }
    private RedBlackNode<T> search(RedBlackNode<T> node, T value) {
        if (node.equals(NIL)) {
            throw new NoSuchElementException("There is not element with this value");
        } else if (node.value.equals(value)) {
            return node;
        } else if (value.compareTo(node.value)<0) {
            return search(node.left, value);
        } else {
            return search(node.right, value);
        }
    }

    public void insert(T value) {
        RedBlackNode<T> node = new RedBlackNode<T>(value);
        root = insert(root, node);
        insertFixUp(node);
    }

    private RedBlackNode<T> insert(RedBlackNode<T> node, RedBlackNode<T> nodeToInsert) {
        if (node.equals(NIL)) {
            return nodeToInsert;
        }
        if (nodeToInsert.value.compareTo(node.value)<0) {
            node.left = insert(node.left, nodeToInsert);
            node.left.parent = node;
        } else if (nodeToInsert.value.compareTo(node.value)>0) {
            node.right = insert(node.right, nodeToInsert);
            node.right.parent = node;
        } else throw new IllegalArgumentException("This value already exists in tree");
        return node;
    }

    private void insertFixUp(RedBlackNode<T> node) {
        root.color = BLACK;
        RedBlackNode<T> parent = node.parent;
        if(parent==null) return;
        if (node != root && parent.color == RED) {
            RedBlackNode<T> grandParent = node.parent.parent;
            RedBlackNode<T> uncle = parent.isLeftChild() ?
                    grandParent.right : grandParent.left;
            if (uncle != NIL && uncle.color == RED) { // Recoloring
                handleRecoloring(parent, uncle, grandParent);
            } else if (parent.isLeftChild()) { // Left-Left or Left-Right situation
                handleLeftSituations(node, parent, grandParent);
            } else if (!parent.isLeftChild()) { // Right-Right or Right-Left situation
                handleRightSituations(node, parent, grandParent);
            }
        }
    }

    private void handleRightSituations(RedBlackNode<T> node, RedBlackNode<T> parent, RedBlackNode<T> grandParent) {
        if (node.isLeftChild()) {
            rightRotate(parent);
        }
        leftRotate(grandParent);
        parent.flipColor();
        grandParent.flipColor();
    }

    private void handleLeftSituations(RedBlackNode<T> node, RedBlackNode<T> parent, RedBlackNode<T> grandParent) {
        if (!node.isLeftChild()) {
            leftRotate(parent);
        }
        rightRotate(grandParent);
        node.flipColor();
        grandParent.flipColor();
    }

    private void handleRecoloring(RedBlackNode<T> parent, RedBlackNode<T> uncle, RedBlackNode<T> grandParent) {
        uncle.flipColor();
        parent.flipColor();
        grandParent.flipColor();
        insertFixUp(grandParent);
    }

    private void rightRotate(RedBlackNode<T> unbalancedNode) {
        RedBlackNode<T> newRoot = unbalancedNode.left;
        unbalancedNode.left = newRoot.right;
        if (unbalancedNode.left != null) {
            unbalancedNode.left.parent = unbalancedNode;
        }
        newRoot.right = unbalancedNode;
        newRoot.parent = unbalancedNode.parent;
        updateChildrenOfParentNode(unbalancedNode, newRoot);
        unbalancedNode.parent = newRoot;
    }

    private void leftRotate(RedBlackNode<T> unbalancedNode) {
        RedBlackNode<T> newRoot = unbalancedNode.right;
        unbalancedNode.right = newRoot.left;
        if (unbalancedNode.right != null) {
            unbalancedNode.right.parent = unbalancedNode;
        }
        newRoot.left = unbalancedNode;
        newRoot.parent = unbalancedNode.parent;
        updateChildrenOfParentNode(unbalancedNode, newRoot);
        unbalancedNode.parent = newRoot;
    }

    private void updateChildrenOfParentNode(RedBlackNode<T> unbalancedNode, RedBlackNode<T> newRoot) {
        if (unbalancedNode.parent == null) {
            root = newRoot;
        } else if (unbalancedNode.isLeftChild()) {
            unbalancedNode.parent.left = newRoot;
        } else {
            unbalancedNode.parent.right = newRoot;
        }
    }
    public void delete(T k) {
        RedBlackNode<T> z = search(k);

        RedBlackNode<T> y = z;
        boolean yOrigColor = y.color;

        // case 1
        RedBlackNode<T> x;
        if (z.left.equals(NIL)) {
            x = z.right;
            transplant(z, z.right);
        }
        // case 2
        else if (z.right.equals(NIL)) {
            x = z.left;
            transplant(z, z.left);
        }
        // case 3
        else {
            y = successor(z.right);
            yOrigColor = y.color;
            x = y.right;

            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOrigColor == BLACK) {
            deleteFixUp(x);
        }
    }
    private RedBlackNode<T> successor(RedBlackNode<T> root) {
        if (root.left.equals(NIL)) {
            return root;
        } else {
            return successor(root.left);
        }
    }
    private void transplant(RedBlackNode<T> u, RedBlackNode<T> v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void deleteFixUp(RedBlackNode<T> x) {
        while (x != root && x.color == BLACK) {
            if (x.isLeftChild()) {
                RedBlackNode<T> w = x.parent.right;
                // case 1
                if (w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                // case 2
                if (w.left.color == BLACK && w.right.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                } else {
                    // case 3
                    if (w.right.color == BLACK) {
                        w.left.color = BLACK;
                        w.color = RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    // case 4
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                RedBlackNode<T> w = x.parent.left;
                // case 1
                if (w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                // case 2
                if (w.right.color == BLACK && w.left.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                } else {
                    // case 3
                    if (w.left.color == BLACK) {
                        w.right.color = BLACK;
                        w.color = RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    // case 4
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        //case 0
        x.color = BLACK;
    }
    public void deleteTree() {
        root = null;
    }
}
