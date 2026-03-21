import java.util.Arrays;

public class BellmanFord {
    static void bellmanFord(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax all edges V-1 times
        for (int i = 0; i < V - 1; i++)
            for (int[] e : edges) // e = {u, v, w}
                if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]])
                    dist[e[1]] = dist[e[0]] + e[2];

        // Check for negative weight cycles
        for (int[] e : edges)
            if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]]) {
                System.out.println("Negative weight cycle detected!");
                return;
            }

        System.out.println("Vertex\tDistance from Source " + src);
        for (int i = 0; i < V; i++) System.out.println(i + "\t" + dist[i]);
    }

    public static void main(String[] args) {
        int V = 5;
        int[][] edges = {{0,1,6},{0,2,7},{1,2,8},{1,3,5},{1,4,-4},{2,3,-3},{2,4,9},{3,1,-2},{4,0,2},{4,3,7}};
        bellmanFord(V, edges, 0);
    }
}
