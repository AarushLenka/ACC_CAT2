import java.util.*;

public class ViewsOfTree {
    static class Node {
        int v;
        Node l, r;
        Node(int v) { this.v = v; }
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

    static void leftView(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        System.out.print("Left: ");
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                Node n = q.poll();
                if (i == 0)
                    System.out.print(n.v + " ");
                if (n.l != null) q.add(n.l);
                if (n.r != null) q.add(n.r);
            }
        }
        System.out.println();
    }

    static void rightView(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        System.out.print("Right: ");
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                Node n = q.poll();
                if (i == sz - 1)
                    System.out.print(n.v + " ");
                if (n.l != null) q.add(n.l);
                if (n.r != null) q.add(n.r);
            }
        }
        System.out.println();
    }

    static void topView(Node root) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Node> nq = new LinkedList<>();
        Queue<Integer> cq = new LinkedList<>();
        nq.add(root);
        cq.add(0);
        while (!nq.isEmpty()) {
            Node n = nq.poll();
            int c = cq.poll();
            map.putIfAbsent(c, n.v);
            if (n.l != null) { nq.add(n.l); cq.add(c - 1); }
            if (n.r != null) { nq.add(n.r); cq.add(c + 1); }
        }
        System.out.println("Top: " + map.values());
    }

    static void bottomView(Node root) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Node> nq = new LinkedList<>();
        Queue<Integer> cq = new LinkedList<>();
        nq.add(root);
        cq.add(0);
        while (!nq.isEmpty()) {
            Node n = nq.poll();
            int c = cq.poll();
            map.put(c, n.v);
            if (n.l != null) { nq.add(n.l); cq.add(c - 1); }
            if (n.r != null) { nq.add(n.r); cq.add(c + 1); }
        }
        System.out.println("Bottom: " + map.values());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Build tree:");
        Node root = build(sc);
        if (root == null) return;
        leftView(root);
        rightView(root);
        topView(root);
        bottomView(root);
    }
}
