import java.util.*;

public class KAryHeap {
    static int k, sz = 0;
    static int[] h;

    static void insert(int v) {
        h[sz++] = v;
        int i = sz - 1;
        while (i > 0 && h[i] > h[(i - 1) / k]) {
            int t = h[i];
            h[i] = h[(i - 1) / k];
            h[(i - 1) / k] = t;
            i = (i - 1) / k;
        }
    }

    static void heapify(int i) {
        int m = i;
        for (int j = 1; j <= k; j++) {
            int c = k * i + j;
            if (c < sz && h[c] > h[m])
                m = c;
        }
        if (m != i) {
            int t = h[i];
            h[i] = h[m];
            h[m] = t;
            heapify(m);
        }
    }

    static int extractMax() {
        int m = h[0];
        h[0] = h[--sz];
        heapify(0);
        return m;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("K: ");
        k = sc.nextInt();
        System.out.print("n: ");
        int n = sc.nextInt();
        h = new int[n + 10];
        System.out.print("Elements: ");
        for (int i = 0; i < n; i++)
            insert(sc.nextInt());
        System.out.println("Heap: " + Arrays.toString(Arrays.copyOf(h, sz)));
        System.out.println("ExtractMax: " + extractMax());
        System.out.println("After: " + Arrays.toString(Arrays.copyOf(h, sz)));
    }
}
