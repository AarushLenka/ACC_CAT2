public class WinnerTree {
    static int[] tree;
    static int[] players;
    static int n;

    // Build winner tree (minimum wins)
    static void build(int[] scores) {
        n = scores.length;
        players = scores;
        tree = new int[2 * n];
        // Fill leaves
        for (int i = 0; i < n; i++) tree[n + i] = i;
        // Build internal nodes (winner = index of min value)
        for (int i = n - 1; i >= 1; i--)
            tree[i] = (players[tree[2 * i]] <= players[tree[2 * i + 1]]) ? tree[2 * i] : tree[2 * i + 1];
    }

    static int getWinner() { return tree[1]; }

    // Replace winner with new value and rebuild path
    static void replaceWinner(int newVal) {
        int pos = tree[1]; // winner index
        players[pos] = newVal;
        // Update path from leaf to root
        int i = (n + pos) / 2;
        while (i >= 1) {
            tree[i] = (players[tree[2 * i]] <= players[tree[2 * i + 1]]) ? tree[2 * i] : tree[2 * i + 1];
            i /= 2;
        }
    }

    public static void main(String[] args) {
        int[] scores = {3, 7, 1, 9, 4, 2, 8, 5};
        build(scores);
        int w = getWinner();
        System.out.println("Winner: player " + w + " with score " + players[w]);
        // Extract winner and replace with MAX
        replaceWinner(Integer.MAX_VALUE);
        w = getWinner();
        System.out.println("2nd place: player " + w + " with score " + players[w]);
    }
}
