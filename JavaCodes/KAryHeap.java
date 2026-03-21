import java.util.Arrays;

public class KAryHeap {
    static int k;
    static int[] heap;
    static int size = 0;

    KAryHeap(int cap, int k) { this.k = k; heap = new int[cap]; }

    static void insert(int val) {
        heap[size++] = val;
        int i = size - 1;
        while (i > 0 && heap[i] > heap[(i - 1) / k]) {
            int t = heap[i]; heap[i] = heap[(i - 1) / k]; heap[(i - 1) / k] = t;
            i = (i - 1) / k;
        }
    }

    static void maxHeapify(int i) {
        int max = i;
        for (int j = 1; j <= k; j++) {
            int child = k * i + j;
            if (child < size && heap[child] > heap[max]) max = child;
        }
        if (max != i) { int t = heap[i]; heap[i] = heap[max]; heap[max] = t; maxHeapify(max); }
    }

    static int extractMax() {
        int max = heap[0];
        heap[0] = heap[--size];
        maxHeapify(0);
        return max;
    }

    public static void main(String[] args) {
        KAryHeap h = new KAryHeap(20, 3); // 3-ary max heap
        for (int v : new int[]{10, 20, 15, 30, 40, 5, 25}) insert(v);
        System.out.println("Heap: " + Arrays.toString(Arrays.copyOf(heap, size)));
        System.out.println("Extract Max: " + extractMax());
        System.out.println("After extract: " + Arrays.toString(Arrays.copyOf(heap, size)));
    }
}
