import java.io.*;
import java.util.*;
/*
 * Task: observe interactions and track the size of each person's network 
 * 
 */
public class virtualfriends {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int F = Integer.parseInt(st.nextToken());
            VFUFDS UF = new VFUFDS(F*2);
            HashMap<String, Integer> indexing = new HashMap<>();
            int ind = 0; 
            while (F-- > 0) {
                st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                if (!indexing.containsKey(name1)) {
                    indexing.put(name1, ind);
                    ind++;
                }
                int name1ind = indexing.get(name1);
                String name2 = st.nextToken();
                if (!indexing.containsKey(name2)) {
                    indexing.put(name2, ind);
                    ind++;
                }
                int name2ind = indexing.get(name2);
                UF.unionSet(name1ind, name2ind);
                pw.println(UF.sizeOfSet(name1ind));
            }
        }
        pw.flush();
        pw.close();
    }
}

class VFUFDS {
    private ArrayList<Integer> p, rank, setSize; 
    private int numSets;

    public VFUFDS(int N) {
        p = new ArrayList<>(N);
        rank = new ArrayList<>(N);
        setSize = new ArrayList<>(N);
        numSets = N;
        
        for (int i = 0; i < N; i++) {
            p.add(i);
            rank.add(0);
            setSize.add(1);
        }
    }

    public int findSet(int i) {
        if (p.get(i) == i) return i;
        else {
            int ret = findSet(p.get(i));
            p.set(i, ret);
            return ret;
        }
    }

    public boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            numSets--;
            int x = findSet(i);
            int y = findSet(j);

            if (rank.get(x) > rank.get(y)) {
                p.set(y, x);
                setSize.set(x, setSize.get(x) + setSize.get(y));
            } else {
                p.set(x, y);
                setSize.set(y, setSize.get(y) + setSize.get(x));

                if (rank.get(x) == rank.get(y))
                    rank.set(y, rank.get(y) + 1);
            }
        }
    }

    public int numDisjointSets() {
        return numSets;
    }

    public int sizeOfSet(int i) {
        return setSize.get(findSet(i));
    }
}