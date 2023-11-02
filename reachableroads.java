import java.io.*;
import java.util.*;

public class reachableroads {
    private static ArrayList<ArrayList<Integer>> AL; // stores the edges connected to each vertices 
    private static ArrayList<Boolean> visited;

    // checks if there is a possible path
    // RECURSION input is storing the value of the parent
    private static void dfs(int u) {
        visited.set(u, true);
        for (Integer v : AL.get(u)) {
            if (!visited.get(v)) {
                // set
                dfs(v);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // number of cities 

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); // number of endpoints (vertices)
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()); // number of pairs (edges)

            AL = new ArrayList<>();
            for (int i = 0; i < m; ++i) { // for each vertices 
                AL.add(new ArrayList<Integer>());
            }

            while (r-- > 0) { 
                st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken()); 
                int second = Integer.parseInt(st.nextToken()); 
                AL.get(first).add(second);
                AL.get(second).add(first);
            }

            int CC = 0; // connected components 
            // create visited matrix 
            visited = new ArrayList<>(); // tracks if a vertice has been visited 
            for (int i = 0; i < m ; i++) { // for each vertices 
                visited.add(false);
            }

            // create connected componenets
            for (int i = 0; i < m; ++i) { // for each vertices 
                if (!visited.get(i)) {
                    CC++;
                    dfs(i);
                }
            }

            pw.println(--CC); 
        }

        pw.flush();
        pw.close();
    }
}
