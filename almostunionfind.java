import java.io.*;
import java.util.*;
/*
 * The crux of this question lies in the adaptation of the UFDS data structure. 
 * How to track the new parents of the set itself 
 */
public class almostunionfind {
    public static void main(String[] args) {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());
        Kattio io = new Kattio(System.in, System.out);

        while (io.hasMoreTokens()) {
            int n = io.getInt();
            int m = io.getInt();
            UnionFind UF = new UnionFind(n+1);

            while (m-- > 0) {
                int command = io.getInt();
                int p = io.getInt();
                if (command == 1) {
                    int q = io.getInt();
                    UF.unionSet(p, q);
                } else if (command == 2) {
                    int q = io.getInt();
                    UF.moveTo(p, q);
                } else if (command == 3) {
                    io.println(UF.sizeOfSet(p) + " " + UF.sumElem(p));
                }
                io.println(UF.p + " " + UF.parentSet);
                io.println("CHECK THIS BITCH " + UF.findSet(3));
                io.flush();
            }
        }
    }
}

 // UFDS Data Structures
class UnionFind {
    ArrayList<Integer> p, parentSet;
    ArrayList<Long> setSize, setSum;

    // constructor
    public UnionFind(int N) {
        p = new ArrayList<>(N); 
        setSize = new ArrayList<>(N); 
        setSum = new ArrayList<>(N); 
        parentSet = new ArrayList<>(); 
        
        for (int i = 0; i < N; i++) {
            p.add(i);
            setSize.add((long) 1);
            setSum.add((long) i);
            parentSet.add(i);
        }
    }

    // findset will return parent
    // parent set of 3 is still 4?
    public int findSet(int i) {
        int newP = parentSet.get(i); // contains known parents 
        // System.out.println("parent set of " + i + " is " + newP);
        while (newP != p.get(newP)) { // if the parent set is not the same as the actual parent in p 
            newP = p.get(newP);
        }
        return newP;
    }

    // checks if they are the same set 
    public boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    // union 2 sets 
    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            int x = findSet(i); 
            int y = findSet(j);
            p.set(x, y);
            setSize.set(y, setSize.get(y) + setSize.get(x));
            setSum.set(y, setSum.get(x) + setSum.get(y));
            parentSet.set(i, y);
        }
    }

    public void moveTo(int i, int j) { // move 3 to 4 
        // move i to j 
        if (!isSameSet(i, j)) {
            int x = findSet(i); // i = 3 , x = 3
            int y = findSet(j);  // j = 4, y = 4
            parentSet.set(i, y); 
            setSize.set(x, setSize.get(x) - 1);
            setSum.set(x, setSum.get(x) - i);
            setSize.set(y, setSize.get(y) + 1);
            setSum.set(y, setSum.get(y) + i);
        }
    }

    // get size of set 
    public long sizeOfSet(int i) {
        System.out.println("This size" + i);
        System.out.println(findSet(i));
        System.out.println(setSize.get(findSet(i)));
        return setSize.get(findSet(i));
    } 

    // get sum of set 
    public long sumElem(int i) {
        System.out.println("This set" + i);
        System.out.println(findSet(i));
        System.out.println(setSum.get(findSet(i)));
        return setSum.get(findSet(i));
    }
}



class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
    super(new BufferedOutputStream(System.out));
    r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
    super(new BufferedOutputStream(o));
    r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
    return peekToken() != null;
    }

    public int getInt() {
    return Integer.parseInt(nextToken());
    }

    public double getDouble() { 
    return Double.parseDouble(nextToken());
    }

    public long getLong() {
    return Long.parseLong(nextToken());
    }

    public String getWord() {
    return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
    if (token == null) 
        try {
        while (st == null || !st.hasMoreTokens()) {
            line = r.readLine();
            if (line == null) return null;
            st = new StringTokenizer(line);
        }
        token = st.nextToken();
        } catch (IOException e) { }
    return token;
    }

    private String nextToken() {
    String ans = peekToken();
    token = null;
    return ans;
    }
}