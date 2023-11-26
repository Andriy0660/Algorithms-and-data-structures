package tree;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class BinaryHeap <T extends Comparable<T>> {
    public enum HEAP_TYPE{MIN,MAX}
    private HEAP_TYPE type;
    private T[] arr;
    private int size;
    private int capacity = 10;
    private static final double LOAD_FACTOR = 0.75;
    public BinaryHeap() {
        type = HEAP_TYPE.MIN;
        arr = (T[])new Comparable[capacity+1];
        size = 0;
    }
    public BinaryHeap(HEAP_TYPE type) {
        this.type = type;
        arr = (T[])new Comparable[capacity+1];
        size = 0;
    }

    public BinaryHeap(int capacity) {
        type = HEAP_TYPE.MIN;
        this.capacity = capacity;
        arr = (T[])new Comparable[capacity+1];
        size = 0;
    }
    public BinaryHeap(HEAP_TYPE type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        arr = (T[])new Comparable[capacity+1];
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    public T peek(){
        if(isEmpty())
            throw new NoSuchElementException("The Binary Heap is empty!");
        return arr[1];
    }
    public int size(){
        return size;
    }
    public String levelOrder(){
        StringBuilder res = new StringBuilder();
        for(int i = 1; i <= size; i++){
            res.append(arr[i]);
        }
        return new String(res);
    }
    public void insert(T value) {
        if (size >= capacity * LOAD_FACTOR) {
            capacity *= 2;
            arr = Arrays.copyOf(arr, capacity);
        }
        arr[size+1] = value;
        heapifyTop(size);
        size++;
    }
    private void heapifyTop(int index) {
        int parent = index / 2;
        if (index <= 1) {
            return;
        }
        if (arr[index].equals(arr[parent])) {
            arr[index] = null;
            return;
        }
        if (type == HEAP_TYPE.MIN) {
            if (arr[index].compareTo(arr[parent]) < 0) {
                T tmp = arr[index];
                arr[index] = arr[parent];
                arr[parent] = tmp;
            }
        } else if (type == HEAP_TYPE.MAX) {
            if (arr[index].compareTo(arr[parent]) > 0) {
                T tmp = arr[index];
                arr[index] = arr[parent];
                arr[parent] = tmp;
            }
        }
        heapifyTop(parent);
    }
    public T extract() {
        if (isEmpty()) {
            throw new NoSuchElementException("The Binary Heap is empty!");
        } else {
            T extractedValue = arr[1];
            arr[1] = arr[size];
            size--;
            heapifyDown(1);
            return extractedValue;
        }
    }
    private void heapifyDown(int index) {
        int left = index*2;
        int right = index*2 + 1;
        int swapChild = 0;
        if (size < left) {
            return;
        }
        if (type == HEAP_TYPE.MAX) {
            if (size == left) {
                if (arr[index].compareTo(arr[left])<0) {
                    T tmp = arr[index];
                    arr[index] = arr[left];
                    arr[left] = tmp;
                }
                return;
            } else {
                if (arr[left].compareTo(arr[right])>0) {
                    swapChild = left;
                } else {
                    swapChild = right;
                }
                if (arr[index].compareTo(arr[swapChild])<0) {
                    T tmp = arr[index];
                    arr[index] = arr[swapChild];
                    arr[swapChild] = tmp;
                }
            }
        } else if (type == HEAP_TYPE.MIN) {
            if (size == left) {
                if (arr[index].compareTo(arr[left])>0) {
                    T tmp = arr[index];
                    arr[index] = arr[left];
                    arr[left] = tmp;
                }
                return;
            } else {
                if (arr[left].compareTo(arr[right])<0) {
                    swapChild = left;
                } else {
                    swapChild = right;
                }
                if (arr[index].compareTo(arr[swapChild])>0) {
                    T tmp = arr[index];
                    arr[index] = arr[swapChild];
                    arr[swapChild] = tmp;
                }
            }
        }
        heapifyDown(swapChild);
    }
    public void delete() {
        arr = null;
    }
}
