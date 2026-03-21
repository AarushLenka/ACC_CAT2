import java.util.*;

public class DFS {
    static void dfs(List<List<Integer>> adj, int u, boolean[] vis) {
        vis[u] = true;
        System.out.print(u + " ");
        for (int v : adj.get(u))
            if (!vis[v]) dfs(adj, v, vis);
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,4}};
        for (int[] e : edges) { adj.get(e[0]).add(e[1]); adj.get(e[1]).add(e[0]); }
        System.out.print("DFS from 0: ");
        dfs(adj, 0, new boolean[V]);
    }
}
