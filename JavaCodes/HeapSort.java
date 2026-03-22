import java.util.*;

public class HeapSort {
    static void heapify(int[] a, int n, int i) {
        int m = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && a[l] > a[m]) m = l;
        if (r < n && a[r] > a[m]) m = r;
        if (m != i) {
            int t = a[i];
            a[i] = a[m];
            a[m] = t;
            heapify(a, n, m);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("n: ");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.print("Elements: ");
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(a, n, i);
        for (int i = n - 1; i > 0; i--) {
            int t = a[0];
            a[0] = a[i];
            a[i] = t;
            heapify(a, i, 0);
        }
        System.out.println("Sorted: " + Arrays.toString(a));
    }
}
