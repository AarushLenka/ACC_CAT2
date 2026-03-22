import java.util.*;

public class WinnerTree {
    static int[] tree, p;
    static int n;

    static void build() {
        tree = new int[2 * n];
        for (int i = 0; i < n; i++)
            tree[n + i] = i;
        for (int i = n - 1; i >= 1; i--)
            tree[i] = (p[tree[2 * i]] <= p[tree[2 * i + 1]]) ? tree[2 * i] : tree[2 * i + 1];
    }

    static void replaceWinner(int v) {
        p[tree[1]] = v;
        int pos = (n + tree[1]) / 2;
        while (pos >= 1) {
            tree[pos] = (p[tree[2 * pos]] <= p[tree[2 * pos + 1]]) ? tree[2 * pos] : tree[2 * pos + 1];
            pos /= 2;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Players: ");
        int orig = sc.nextInt();
        n = orig;
        while ((n & (n - 1)) != 0)
            n++;
        p = new int[n];
        System.out.print("Values: ");
        for (int i = 0; i < orig; i++)
            p[i] = sc.nextInt();
        for (int i = orig; i < n; i++)
            p[i] = Integer.MAX_VALUE;
        build();
        System.out.println("Winner: p[" + tree[1] + "] = " + p[tree[1]]);
        System.out.print("Replace winner with: ");
        replaceWinner(sc.nextInt());
        System.out.println("New winner: p[" + tree[1] + "] = " + p[tree[1]]);
    }
}
