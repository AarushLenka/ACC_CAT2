import java.util.*;

public class BellmanFord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Vertices: ");
        int V = sc.nextInt();
        System.out.print("Edges: ");
        int E = sc.nextInt();
        int[][] e = new int[E][3];
        System.out.println("Enter edges (u v weight):");
        for (int i = 0; i < E; i++) {
            e[i][0] = sc.nextInt();
            e[i][1] = sc.nextInt();
            e[i][2] = sc.nextInt();
        }
        System.out.print("Source: ");
        int s = sc.nextInt();
        int[] d = new int[V];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[s] = 0;
        for (int i = 0; i < V - 1; i++)
            for (int[] ed : e)
                if (d[ed[0]] != Integer.MAX_VALUE && d[ed[0]] + ed[2] < d[ed[1]])
                    d[ed[1]] = d[ed[0]] + ed[2];
        for (int[] ed : e)
            if (d[ed[0]] != Integer.MAX_VALUE && d[ed[0]] + ed[2] < d[ed[1]]) {
                System.out.println("Negative cycle!");
                return;
            }
        for (int i = 0; i < V; i++)
            System.out.println(i + " -> " + d[i]);
    }
}
