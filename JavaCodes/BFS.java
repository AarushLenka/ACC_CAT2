import java.util.*;

public class BFS {
    static void bfs(List<List<Integer>> adj, int src) {
        boolean[] vis = new boolean[adj.size()];
        Queue<Integer> q = new LinkedList<>();
        vis[src] = true;
        q.add(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print(u + " ");
            for (int v : adj.get(u))
                if (!vis[v]) { vis[v] = true; q.add(v); }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,4}};
        for (int[] e : edges) { adj.get(e[0]).add(e[1]); adj.get(e[1]).add(e[0]); }
        System.out.print("BFS from 0: "); bfs(adj, 0);
    }
}
