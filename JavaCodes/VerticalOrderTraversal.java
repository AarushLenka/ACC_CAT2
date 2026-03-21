import java.util.*;

public class VerticalOrderTraversal {
    static class Node {
        int val; Node left, right;
        Node(int v) { val = v; }
    }

    static void verticalOrder(Node root) {
        if (root == null) return;
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Node> nodeQ = new LinkedList<>();
        Queue<Integer> colQ = new LinkedList<>();
        nodeQ.add(root); colQ.add(0);

        while (!nodeQ.isEmpty()) {
            Node n = nodeQ.poll();
            int col = colQ.poll();
            map.computeIfAbsent(col, k -> new ArrayList<>()).add(n.val);
            if (n.left != null) { nodeQ.add(n.left); colQ.add(col - 1); }
            if (n.right != null) { nodeQ.add(n.right); colQ.add(col + 1); }
        }

        for (var entry : map.entrySet())
            System.out.println("Col " + entry.getKey() + ": " + entry.getValue());
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
        verticalOrder(root);
    }
}
