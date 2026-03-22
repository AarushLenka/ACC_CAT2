import java.util.*;

public class BinomialHeap {
    static class Node {
        int key, deg;
        Node child, sib, par;
        Node(int k) { key = k; }
    }

    static Node head;

    static Node merge(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;
        Node h, t;
        if (a.deg <= b.deg) { h = a; a = a.sib; }
        else { h = b; b = b.sib; }
        t = h;
        while (a != null && b != null) {
            if (a.deg <= b.deg) { t.sib = a; a = a.sib; }
            else { t.sib = b; b = b.sib; }
            t = t.sib;
        }
        t.sib = (a != null) ? a : b;
        return h;
    }

    static Node link(Node y, Node z) {
        y.par = z;
        y.sib = z.child;
        z.child = y;
        z.deg++;
        return z;
    }

    static Node union(Node a, Node b) {
        Node h = merge(a, b);
        if (h == null) return null;
        Node p = null, c = h, n = c.sib;
        while (n != null) {
            if (c.deg != n.deg || (n.sib != null && n.sib.deg == c.deg)) {
                p = c;
                c = n;
            } else if (c.key <= n.key) {
                c.sib = n.sib;
                link(n, c);
            } else {
                if (p == null) h = n;
                else p.sib = n;
                link(c, n);
                c = n;
            }
            n = c.sib;
        }
        return h;
    }

    static void insert(int k) {
        head = union(head, new Node(k));
    }

    static int getMin() {
        int m = Integer.MAX_VALUE;
        for (Node n = head; n != null; n = n.sib)
            m = Math.min(m, n.key);
        return m;
    }

    static int extractMin() {
        Node mp = null, mn = head, p = null;
        for (Node n = head; n != null; p = n, n = n.sib)
            if (n.key < mn.key) { mn = n; mp = p; }
        if (mp == null) head = mn.sib;
        else mp.sib = mn.sib;
        Node c = mn.child, r = null;
        while (c != null) {
            Node nx = c.sib;
            c.sib = r;
            c.par = null;
            r = c;
            c = nx;
        }
        head = union(head, r);
        return mn.key;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("n: ");
        int n = sc.nextInt();
        System.out.print("Elements: ");
        for (int i = 0; i < n; i++)
            insert(sc.nextInt());
        System.out.println("Min: " + getMin());
        System.out.println("ExtractMin: " + extractMin());
        System.out.println("NewMin: " + getMin());
    }
}
