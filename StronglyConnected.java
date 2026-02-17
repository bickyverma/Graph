class StronglyConneted{

    public int StronglyConnetedComponent(ArrayList<ArrayList<Integer>> adj) {

        int V = adj.size();

        boolean[] visit = new boolean[V];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visit[i]) {
                dfs1(i, adj, visit, st);
            }
        }

        ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();

        for(int i = 0; i < V; i++) {
            adj2.add(new ArrayList<>());
        }
        for(int i = 0; i < V; i++) {
            for (int nei : adj.get(i)) {
                adj2.get(nei).add(i); //revverse graph
            }
        }

        for(int i = 0; i < V; i++) {
            visit[i] = false;
        }

        int count = 0;

        while(!st.isEmpty()) {
            int node = st.pop();

            if (!visit[node]) {
                dfs2(node, adj2, visit);
                count++;
            }
        }

        return count;
    }

    // First DFS
    void dfs1(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visit, Stack<Integer> st) {
        visit[node] = true;

        for (int nei : adj.get(node)) {
            if (!visit[nei]) {
                dfs1(nei, adj, visit, st);
            }
        }

        st.push(node);
    }

    // Second DFS
    void dfs2(int node, ArrayList<ArrayList<Integer>> adj2, boolean[] visit) {
        visit[node] = true;

        for (int nei : adj2.get(node)) {
            if (!visit[nei]) {
                dfs2(nei, adj2, visit);
            }
        }
    }
}
