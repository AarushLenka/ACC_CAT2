import java.util.*;

public class DialsAlgorithm {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Vertices: ");
        int V = sc.nextInt();
        System.out.print("Edges: ");
        int E = sc.nextInt();
        System.out.print("MaxWeight: ");
        int W = sc.nextInt();
        List<int[]>[] adj = new List[V];
        for (int i = 0; i < V; i++)
            adj[i] = new ArrayList<>();
        System.out.println("Enter edges (u v w):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }
        System.out.print("Source: ");
        int s = sc.nextInt();
        int[] d = new int[V];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[s] = 0;
        int mx = (V - 1) * W;
        List<List<Integer>> bkt = new ArrayList<>();
        for (int i = 0; i <= mx; i++)
            bkt.add(new LinkedList<>());
        bkt.get(0).add(s);
        int idx = 0;
        while (idx <= mx) {
            while (idx <= mx && bkt.get(idx).isEmpty())
                idx++;
            if (idx > mx) break;
            int u = bkt.get(idx).remove(0);
            for (int[] e : adj[u]) {
                if (d[u] + e[1] < d[e[0]]) {
                    d[e[0]] = d[u] + e[1];
                    bkt.get(d[e[0]]).add(e[0]);
                }
            }
        }
        for (int i = 0; i < V; i++)
            System.out.println(i + " -> " + d[i]);
    }
}
