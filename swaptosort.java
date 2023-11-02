/*
A0259416X
Lee Yi Lin
Lab Group 3 
TA: Ramapriyan
*/ 
import java.io.*;
import java.util.*;

public class swaptosort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        UnionFind UF = new UnionFind(N);
        while (K-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(tokenizer.nextToken()) - 1;
            int second = Integer.parseInt(tokenizer.nextToken()) - 1;
            UF.unionSet(first, second);
        } 
        for (int i = 0; i < N; i++) {
            if (!UF.isSameSet(i, N-i-1)) {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
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