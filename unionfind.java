import java.io.*;
import java.util.*;
/*
 * Optimising UFDS by using traditional arrays 
 */
public class unionfind {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        UFDS UF = new UFDS(N);

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (command.equals("=")) {
                UF.unionSet(a, b);
            } else if (command.equals("?")) {
                if (UF.isSameSet(a, b)) {
                    pw.println("yes");
                } else {
                    pw.println("no");
                }
            }
        }
        pw.flush();
    }
}

class UFDS {
    private int[] p, rank;

    public UFDS(int N) {
        p = new int[N];
        rank = new int[N];
        
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
        }
    }

    public int findSet(int i) {
        if (p[i] == i) return i;
        else {
            int ret = findSet(p[i]);
            p[i] = ret;
            return ret;
        }
    }

    public boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            int x = findSet(i);
            int y = findSet(j);

            if (rank[x] > rank[y]) {
                p[y] = x;
            } else {
                p[x] = y;

                if (rank[x] == rank[y])
                    rank[y] = rank[y] + 1;
            }
        }
    }
}