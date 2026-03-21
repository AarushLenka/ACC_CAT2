import java.util.*;

public class BinomialHeap {
    static class Node {
        int key, degree;
        Node child, sibling, parent;
        Node(int k) { key = k; }
    }

    static Node head;

    static Node merge(Node h1, Node h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        Node head, tail;
        if (h1.degree <= h2.degree) { head = h1; h1 = h1.sibling; }
        else { head = h2; h2 = h2.sibling; }
        tail = head;
        while (h1 != null && h2 != null) {
            if (h1.degree <= h2.degree) { tail.sibling = h1; h1 = h1.sibling; }
            else { tail.sibling = h2; h2 = h2.sibling; }
            tail = tail.sibling;
        }
        tail.sibling = (h1 != null) ? h1 : h2;
        return head;
    }

    static Node link(Node y, Node z) { // Make y child of z
        y.parent = z; y.sibling = z.child; z.child = y; z.degree++; return z;
    }

    static Node union(Node h1, Node h2) {
        Node h = merge(h1, h2);
        if (h == null) return null;
        Node prev = null, curr = h, next = curr.sibling;
        while (next != null) {
            if (curr.degree != next.degree || (next.sibling != null && next.sibling.degree == curr.degree)) {
                prev = curr; curr = next;
            } else if (curr.key <= next.key) {
                curr.sibling = next.sibling; link(next, curr);
            } else {
                if (prev == null) h = next; else prev.sibling = next;
                link(curr, next); curr = next;
            }
            next = curr.sibling;
        }
        return h;
    }

    static void insert(int key) {
        Node n = new Node(key);
        head = union(head, n);
    }

    static int getMin() {
        int min = Integer.MAX_VALUE;
        for (Node n = head; n != null; n = n.sibling) min = Math.min(min, n.key);
        return min;
    }

    static int extractMin() {
        Node minPrev = null, min = head, prev = null;
        for (Node n = head; n != null; prev = n, n = n.sibling)
            if (n.key < min.key) { min = n; minPrev = prev; }
        if (minPrev == null) head = min.sibling; else minPrev.sibling = min.sibling;
        // Reverse children of min
        Node child = min.child, revHead = null;
        while (child != null) {
            Node next = child.sibling; child.sibling = revHead; child.parent = null; revHead = child; child = next;
        }
        head = union(head, revHead);
        return min.key;
    }

    public static void main(String[] args) {
        for (int v : new int[]{12, 7, 25, 15, 28, 33, 41}) insert(v);
        System.out.println("Min: " + getMin());
        System.out.println("Extract Min: " + extractMin());
        System.out.println("New Min: " + getMin());
    }
}
