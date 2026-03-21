import java.util.*;

public class BoundaryTraversal {
    static class Node {
        int val; Node left, right;
        Node(int v) { val = v; }
    }

    static void leftBoundary(Node n, List<Integer> res) {
        if (n == null || (n.left == null && n.right == null)) return;
        res.add(n.val);
        leftBoundary(n.left != null ? n.left : n.right, res);
    }

    static void leaves(Node n, List<Integer> res) {
        if (n == null) return;
        if (n.left == null && n.right == null) { res.add(n.val); return; }
        leaves(n.left, res); leaves(n.right, res);
    }

    static void rightBoundary(Node n, List<Integer> res) {
        if (n == null || (n.left == null && n.right == null)) return;
        rightBoundary(n.right != null ? n.right : n.left, res);
        res.add(n.val); // Add in reverse (bottom-up)
    }

    static List<Integer> boundaryTraversal(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        res.add(root.val);
        leftBoundary(root.left, res);
        leaves(root.left, res); leaves(root.right, res);
        rightBoundary(root.right, res);
        return res;
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
        System.out.println("Boundary: " + boundaryTraversal(root));
    }
}
