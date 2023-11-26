package tree;

import java.util.Objects;

public class BinaryNode <T extends Comparable<T>>{
    public T value;
    public BinaryNode<T> left;
    public BinaryNode<T> right;
    public int height;

    public BinaryNode() {
    }

    public BinaryNode(T value) {
        this.value = value;
        height = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryNode<?> that = (BinaryNode<?>) o;
        return height == that.height && Objects.equals(value, that.value) && Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left, right, height);
    }
}
