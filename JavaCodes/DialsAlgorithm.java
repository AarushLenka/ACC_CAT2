import java.util.*;

public class DialsAlgorithm {
    static void dial(List<int[]>[] adj, int V, int src, int maxW) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Create buckets: one for each possible distance (0 to (V-1)*maxW)
        int maxDist = (V - 1) * maxW;
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= maxDist; i++) buckets.add(new LinkedList<>());
        buckets.get(0).add(src);

        int idx = 0;
        while (idx <= maxDist) {
            while (idx <= maxDist && buckets.get(idx).isEmpty()) idx++;
            if (idx > maxDist) break;
            int u = buckets.get(idx).remove(0);
            for (int[] e : adj[u]) { // e = {v, w}
                if (dist[u] + e[1] < dist[e[0]]) {
                    dist[e[0]] = dist[u] + e[1];
                    buckets.get(dist[e[0]]).add(e[0]);
                }
            }
        }

        System.out.println("Vertex\tDist from " + src);
        for (int i = 0; i < V; i++) System.out.println(i + "\t" + dist[i]);
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int V = 5, maxW = 4;
        List<int[]>[] adj = new List[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
        int[][] edges = {{0,1,2},{0,2,4},{1,2,1},{1,3,3},{2,3,2},{2,4,1},{3,4,4}};
        for (int[] e : edges) { adj[e[0]].add(new int[]{e[1], e[2]}); adj[e[1]].add(new int[]{e[0], e[2]}); }
        dial(adj, V, 0, maxW);
    }
}
