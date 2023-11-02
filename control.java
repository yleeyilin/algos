import java.io.*;
import java.util.*;
/*
 * Ingredients used, after union must be in the same set 
 * 
 * Ingredients can be represented by union. 
 * Each ingredient cannot be in two sets. 
 * Size of two sets cannot be > number of products 
 * 
 * 
 * use hashmap to only add values of the sizes of the stuff within the same set 
 */

public class control {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        UnionFind UF = new UnionFind(500001);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int count = 0;

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            HashMap<Integer, Integer> hshmap = new HashMap<>();
            int sizeIngred = M;
            int key = 0;
            
            while (M-- > 0) {
                int temp = Integer.parseInt(st.nextToken());
                int parent = UF.findSet(temp);
                if (!hshmap.containsKey(parent)) {
                    key = parent;
                    hshmap.put(parent, UF.sizeOfSet(parent));
                } 
            }

            int summation = 0;
            for (Map.Entry<Integer, Integer> entry : hshmap.entrySet()) {
                int val = entry.getValue();
                summation += val;
            }

            if (summation == sizeIngred) {
                for (Map.Entry<Integer, Integer> entry : hshmap.entrySet()) {
                    int temp = entry.getKey();
                    UF.unionSet(key, temp);
                }
                count++;
            }
        }

        pw.println(count); 

        pw.flush();
        pw.close();
    }
}

class UnionFind {
    private ArrayList<Integer> p, rank, setSize;
    private int numSets;

    public UnionFind(int N) {
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
