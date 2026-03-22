import java.util.*;

public class BoundaryTraversal {
    static class Node {
        int v;
        Node l, r;
        Node(int v) { this.v = v; }
    }

    static void lb(Node n, List<Integer> res) {
        if (n == null || (n.l == null && n.r == null)) return;
        res.add(n.v);
        lb(n.l != null ? n.l : n.r, res);
    }

    static void leaf(Node n, List<Integer> res) {
        if (n == null) return;
        if (n.l == null && n.r == null) {
            res.add(n.v);
            return;
        }
        leaf(n.l, res);
        leaf(n.r, res);
    }

    static void rb(Node n, List<Integer> res) {
        if (n == null || (n.l == null && n.r == null)) return;
        rb(n.r != null ? n.r : n.l, res);
        res.add(n.v);
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
        System.out.println("Build tree:");
        Node root = build(sc);
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            res.add(root.v);
            lb(root.l, res);
            leaf(root.l, res);
            leaf(root.r, res);
            rb(root.r, res);
        }
        System.out.println("Boundary: " + res);
    }
}
