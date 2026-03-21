import java.util.*;

public class TopologicalSort {
    // Kahn's Algorithm (BFS-based)
    static List<Integer> topoSort(int V, List<List<Integer>> adj) {
        int[] indegree = new int[V];
        for (List<Integer> neighbors : adj)
            for (int v : neighbors) indegree[v]++;

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) if (indegree[i] == 0) q.add(i);

        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            result.add(u);
            for (int v : adj.get(u))
                if (--indegree[v] == 0) q.add(v);
        }

        if (result.size() != V) System.out.println("Cycle detected! Not a DAG.");
        return result;
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        // Directed edges
        int[][] edges = {{5,2},{5,0},{4,0},{4,1},{2,3},{3,1}};
        for (int[] e : edges) adj.get(e[0]).add(e[1]);
        System.out.println("Topological Order: " + topoSort(V, adj));
    }
}
