import java.util.*;

public class RecoverBST {
    static class Node {
        int v;
        Node l, r;
        Node(int v) { this.v = v; }
    }

    static Node first, second, prev;

    static void inorder(Node n) {
        if (n == null) return;
        inorder(n.l);
        if (prev != null && prev.v > n.v) {
            if (first == null) first = prev;
            second = n;
        }
        prev = n;
        inorder(n.r);
    }

    static void print(Node n) {
        if (n == null) return;
        print(n.l);
        System.out.print(n.v + " ");
        print(n.r);
    }

    static Node build(Scanner sc) {
        System.out.print("Val (-1=null): ");
        int v = sc.nextInt();
        if (v == -1) return null;
        Node n = new Node(v);
        System.out.println("--Left of " + v + "--");
        n.l = build(sc);
        System.out.println("--Right of " + v + "--");
        n.r = build(sc);
        return n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Build BST (2 nodes swapped, -1=null):");
        Node root = build(sc);
        System.out.print("Before: ");
        print(root);
        System.out.println();
        first = second = prev = null;
        inorder(root);
        if (first != null && second != null) {
            int t = first.v;
            first.v = second.v;
            second.v = t;
        }
        System.out.print("After:  ");
        print(root);
        System.out.println();
    }
}
