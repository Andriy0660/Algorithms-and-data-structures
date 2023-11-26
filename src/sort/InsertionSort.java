package sort;
public class InsertionSort {
    public static void insertionSort(int[] arr)
    {
        for (int i = 1; i < arr.length; i++) {
            for(int j = i-1; j >= 0 && arr[j] > arr[j+1]; j--){
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
}
