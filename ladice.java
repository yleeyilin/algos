import java.io.*;
import java.util.*;
/*
 * N items and L drawers 
 * Each drawer can contain one iterm 
 * Determine in advance exactly 2 drawers (Ai and Bi) for each item i
 * 
 * Check if Ai is empty 
 * Check if Bi is empty 
 * Move from Ai to other drawer
 * Move from Bi to other drawer 
 * Successful: "LADICA"
 * 
 * Give up and throw away "SMECE"
 * 
 * Solution: 
 * 1. Union the 2, incr counter (extra)
 * 
 */
public class ladice {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        UnionFindLaddice UF = new UnionFindLaddice(L+1);
        int[] arr = new int[L+1];

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int pA = UF.findSet(A);
            int pB = UF.findSet(B);
            int newV = arr[pA];
            if (!UF.isSameSet(A, B)) {
                newV = arr[pA] + arr[pB];
            }
            UF.unionSet(A, B);
            int p = UF.findSet(A);
            arr[p] = newV + 1;
            if (UF.sizeOfSet(A) < arr[p]) {
                arr[p]--;
                pw.println("SMECE");
            } else {
                pw.println("LADICA");
            }
        }
        pw.flush();
        pw.close();
    }
}

class UnionFindLaddice {
    private ArrayList<Integer> p, rank, setSize;
    private int numSets;

    public UnionFindLaddice(int N) {
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