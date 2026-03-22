import java.util.*;

public class DFS {
    static void dfs(List<List<Integer>> adj, int u, boolean[] vis) {
        vis[u] = true;
        System.out.print(u + " ");
        for (int v : adj.get(u))
            if (!vis[v])
                dfs(adj, v, vis);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Vertices: ");
        int V = sc.nextInt();
        System.out.print("Edges: ");
        int E = sc.nextInt();
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        System.out.println("Enter edges (u v):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        System.out.print("Source: ");
        int s = sc.nextInt();
        System.out.print("DFS: ");
        dfs(adj, s, new boolean[V]);
        System.out.println();
    }
}
