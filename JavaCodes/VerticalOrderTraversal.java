import java.util.*;

public class VerticalOrderTraversal {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Build tree:");
        Node root = build(sc);
        if (root == null) return;
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Node> nq = new LinkedList<>();
        Queue<Integer> cq = new LinkedList<>();
        nq.add(root);
        cq.add(0);
        while (!nq.isEmpty()) {
            Node n = nq.poll();
            int c = cq.poll();
            map.computeIfAbsent(c, k -> new ArrayList<>()).add(n.v);
            if (n.l != null) {
                nq.add(n.l);
                cq.add(c - 1);
            }
            if (n.r != null) {
                nq.add(n.r);
                cq.add(c + 1);
            }
        }
        for (var e : map.entrySet())
            System.out.println("Col " + e.getKey() + ": " + e.getValue());
    }
}
