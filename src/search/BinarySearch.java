package search;

public class BinarySearch {
    public static <T extends Comparable<T>> int binarySearch(T[] arr, T x)
    {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m].compareTo(x)==0)
                return m;
            if (arr[m].compareTo(x)<0)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }
//    public static int binarySearch(int[] arr, int target, int left, int right) {
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            if (arr[mid] == target) {
//            } else if (arr[mid] < target) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return left; // Повертаємо позицію, де потрібно вставити новий елемент
//    }
}
