public class RecoverBST {
    static class Node {
        int val; Node left, right;
        Node(int v) { val = v; }
    }

    static Node first, second, prev;

    // Approach 1: Inorder traversal + two-pointer
    static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null && prev.val > root.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        inorder(root.right);
    }

    static void recoverBST(Node root) {
        first = second = prev = null;
        inorder(root);
        if (first != null && second != null) {
            int t = first.val; first.val = second.val; second.val = t;
        }
    }

    static void printInorder(Node n) {
        if (n == null) return;
        printInorder(n.left);
        System.out.print(n.val + " ");
        printInorder(n.right);
    }

    public static void main(String[] args) {
        //     3           2
        //    / \   =>    / \
        //   1   4       1   4
        //      /           /
        //     2           3
        Node root = new Node(3);
        root.left = new Node(1);
        root.right = new Node(4);
        root.right.left = new Node(2);
        System.out.print("Before: "); printInorder(root); System.out.println();
        recoverBST(root);
        System.out.print("After:  "); printInorder(root); System.out.println();
    }
}
