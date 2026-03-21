import java.util.*;

public class ViewsOfTree {
    static class Node {
        int val; Node left, right;
        Node(int v) { val = v; }
    }

    // Left View — first node at each level
    static void leftView(Node root) {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>(); q.add(root);
        System.out.print("Left View: ");
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node n = q.poll();
                if (i == 0) System.out.print(n.val + " ");
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
        }
        System.out.println();
    }

    // Right View — last node at each level
    static void rightView(Node root) {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>(); q.add(root);
        System.out.print("Right View: ");
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node n = q.poll();
                if (i == size - 1) System.out.print(n.val + " ");
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
        }
        System.out.println();
    }

    // Top View — first node seen at each column
    static void topView(Node root) {
        if (root == null) return;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Node> nq = new LinkedList<>(); Queue<Integer> cq = new LinkedList<>();
        nq.add(root); cq.add(0);
        while (!nq.isEmpty()) {
            Node n = nq.poll(); int col = cq.poll();
            map.putIfAbsent(col, n.val);
            if (n.left != null) { nq.add(n.left); cq.add(col - 1); }
            if (n.right != null) { nq.add(n.right); cq.add(col + 1); }
        }
        System.out.println("Top View: " + map.values());
    }

    // Bottom View — last node seen at each column
    static void bottomView(Node root) {
        if (root == null) return;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Node> nq = new LinkedList<>(); Queue<Integer> cq = new LinkedList<>();
        nq.add(root); cq.add(0);
        while (!nq.isEmpty()) {
            Node n = nq.poll(); int col = cq.poll();
            map.put(col, n.val); // Always overwrite — last one wins
            if (n.left != null) { nq.add(n.left); cq.add(col - 1); }
            if (n.right != null) { nq.add(n.right); cq.add(col + 1); }
        }
        System.out.println("Bottom View: " + map.values());
    }

    public static void main(String[] args) {
        //       1
        //      / \
        //     2   3
        //    / \ / \
        //   4  5 6  7
        Node root = new Node(1);
        root.left = new Node(2); root.right = new Node(3);
        root.left.left = new Node(4); root.left.right = new Node(5);
        root.right.left = new Node(6); root.right.right = new Node(7);

        leftView(root);
        rightView(root);
        topView(root);
        bottomView(root);
    }
}
