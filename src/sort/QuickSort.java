package sort;

public class QuickSort {
    public static <T extends Comparable<T>> void quickSort(T[] arr, int firstElemIndex, int lastElemIndex) {
        if(lastElemIndex-firstElemIndex<=0)return;

        if(lastElemIndex-firstElemIndex==1){
            if(arr[firstElemIndex].compareTo(arr[lastElemIndex])>0)
                swap(arr,firstElemIndex,lastElemIndex);
            return;
        }
        T pivot = arr[lastElemIndex];

        int lastSwapElemIndex = firstElemIndex;
        for(int i=firstElemIndex;i<lastElemIndex;i++){
            if(arr[i].compareTo(pivot)<0) {
                swap(arr, i, lastSwapElemIndex);
                lastSwapElemIndex++;
            }
        }
        swap(arr, lastSwapElemIndex, lastElemIndex);
        if (firstElemIndex<lastElemIndex) {
            quickSort(arr,firstElemIndex,lastSwapElemIndex-1);
            quickSort(arr,lastSwapElemIndex+1, lastElemIndex);
        }
    }

    private static <T extends Comparable<T>> void swap(T[] arr,int index1, int index2){
        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
