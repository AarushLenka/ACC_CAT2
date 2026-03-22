import java.util.*;

public class TopologicalSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Vertices: ");
        int V = sc.nextInt();
        System.out.print("Edges: ");
        int E = sc.nextInt();
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        int[] in = new int[V];
        System.out.println("Enter directed edges (u v):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            in[v]++;
        }
        Queue<Integer> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++)
            if (in[i] == 0)
                q.add(i);
        while (!q.isEmpty()) {
            int u = q.poll();
            res.add(u);
            for (int v : adj.get(u))
                if (--in[v] == 0)
                    q.add(v);
        }
        if (res.size() != V)
            System.out.println("Cycle detected!");
        System.out.println("Topo Order: " + res);
    }
}
