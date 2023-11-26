package sort;

import java.lang.reflect.Array;

public class MergeSort {
    public static <T extends Comparable<T>> void mergeSort(T[] arr, int firstElemIndex, int endElemIndex)
    {
        if (firstElemIndex < endElemIndex)
        {
            int middleELemIndex = (firstElemIndex + endElemIndex) / 2;
            mergeSort(arr, firstElemIndex, middleELemIndex);
            mergeSort(arr, middleELemIndex + 1, endElemIndex);
            merge(arr, firstElemIndex, middleELemIndex, endElemIndex);
        }
    }
    public static <T extends Comparable<T>> void merge(T[] arr, int firstElemIndex, int middleElemIndex, int endElemIndex)
    {
        int i, j, k;
        int n1 = middleElemIndex - firstElemIndex + 1;
        int n2 = endElemIndex - middleElemIndex;

        T[] arrayL = (T[])new Comparable[n1];
        T[] arrayR =  (T[])new Comparable[n2];

        for (i = 0; i < n1; i++)
            arrayL[i] = arr[firstElemIndex + i];
        for (j = 0; j < n2; j++)
            arrayR[j] = arr[middleElemIndex + 1 + j];

        i=0;
        j=0;
        k= firstElemIndex;
        while (i<n1 && j<n2)
        {   //                                  <=
            if(arrayL[i].compareTo(arrayR[j])<0 || arrayL[i].compareTo(arrayR[j])==0)
            {
                arr[k] = arrayL[i];
                i++;
            }
            else
            {
                arr[k] = arrayR[j];
                j++;
            }
            k++;
        }
        while (i < n1)
        {
            arr[k] = arrayL[i];
            i++;
            k++;
        }
        while (j < n2)
        {
            arr[k] = arrayR[j];
            j++;
            k++;
        }
    }
}
