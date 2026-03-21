import java.util.Arrays;

public class HeapSort {
    static void heapify(int[] a, int n, int i) {
        int max = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && a[l] > a[max]) max = l;
        if (r < n && a[r] > a[max]) max = r;
        if (max != i) { int t = a[i]; a[i] = a[max]; a[max] = t; heapify(a, n, max); }
    }

    static void heapSort(int[] a) {
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(a, n, i);      // Build max-heap
        for (int i = n - 1; i > 0; i--) {
            int t = a[0]; a[0] = a[i]; a[i] = t;                      // Swap root to end
            heapify(a, i, 0);                                          // Restore heap
        }
    }

    public static void main(String[] args) {
        int[] a = {12, 11, 13, 5, 6, 7};
        heapSort(a);
        System.out.println("Sorted: " + Arrays.toString(a));
    }
}
