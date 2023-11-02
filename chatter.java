import java.io.*;
import java.util.*;
/*
 * Summary: 
 * Determine how large are the groups of connected components and how large each group is 
 * 
 * People are numbered 0 to n-1 (n people)
 * n (people)
 * r (random seed to connect users)
 * a b c (parameters for the random number generator)
 * 
 * 
 * WRONG APPROACH HERE: SHOULD BE getting the representative of each UFDS data structure and then hashmapping size and freq 
 * Tracking size should be done by the representative 
 */
public class chatter {
    public static int randomGen(int seed, int a, int b, int c) {
        return (seed*a + b) % c;
    }
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);

        while (io.hasMoreTokens()) {
            int n = io.getInt();
            int ncopy = n;
            ChatterUFDS UF = new ChatterUFDS(n);
            int r = io.getInt();
            int a = io.getInt();
            int b = io.getInt();
            int c = io.getInt();
            r = (r*a+b) % c;
            HashMap<Integer, Integer> hshmp = new HashMap<>(); // size : freq
            HashSet<Integer> val = new HashSet<>(); 
            while (ncopy-- > 0) {
                r = randomGen(r, a, b, c);
                int x = r % n;
                r = randomGen(r, a, b, c);
                int y = r % n;
                int xSize = UF.sizeOfSet(x);
                int ySize = UF.sizeOfSet(y);

                // if (hshmp.containsKey(xSize) && hshmp.get(xSize) == 1) {
                //     hshmp.remove(xSize);
                // } else if (hshmp.containsKey(xSize)) {
                //     hshmp.put(xSize, hshmp.get(xSize) - 1);
                // }
            
                // if (hshmp.containsKey(ySize) && hshmp.get(ySize) == 1) {
                //     hshmp.remove(ySize);
                // } else if (hshmp.containsKey(ySize)) {
                //     hshmp.put(ySize, hshmp.get(ySize) - 1);
                // }
                if (hshmp.containsKey(xSize)) {
                    hshmp.put(xSize, hshmp.get(xSize) - 1);
                }
                if (hshmp.containsKey(ySize)) {
                    hshmp.put(ySize, hshmp.get(ySize) - 1);
                }
            
                UF.unionSet(x,y);
                val.add(x);
                val.add(y);
                int newSize = UF.sizeOfSet(x);
                // io.print(newSize);
                if (hshmp.containsKey(newSize)) {
                    hshmp.put(newSize, hshmp.get(newSize) + 1);
                } else {
                    hshmp.put(newSize, 1);
                }
            }
            
            io.print(UF.numDisjointSets());
            io.print(" ");
            // Convert the entrySet to a list
            List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(hshmp.entrySet());

            // Sort the list by keys in descending order
            Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
                    return entry2.getKey().compareTo(entry1.getKey()); // Reverse order
                }
            });

            // Sort the list by keys (ascending)
            // Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            //     @Override
            //     public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
            //         return entry1.getKey().compareTo(entry2.getKey());
            //     }
            // });

            for (Map.Entry<Integer, Integer> entry : entryList) {
                int size = entry.getKey();
                int freq = entry.getValue();
                // io.println("curr: size" + size + " freq:" + freq);
                if (freq > 1) {
                    io.print(size + "x" + freq);
                } else {
                    io.print(size);
                }
                io.print(" ");
            }
            io.println();
            io.flush();
        }
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

    // int m = io.getInt();
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

class ChatterUFDS {
    private ArrayList<Integer> p, rank, setSize; 
    private int numSets;

    public ChatterUFDS(int N) {
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


// class UnionFind {                                              // OOP style
//     public ArrayList<Integer> p, rank, setSize;
//     private int numSets;
//     private int n;
//     private HashSet<Integer> reps;

//     public UnionFind(int N) {
//         n=N;
//         p = new ArrayList<>(N);
//         rank = new ArrayList<>(N);
//         setSize = new ArrayList<>(N);
//         reps = new HashSet<>(N);
//         numSets = N;
//         for (int i = 0; i < N; i++) {
//             p.add(i);
//             rank.add(0);
//             setSize.add(1);
//             reps.add(i);
//         }
//     }

//     public int findSet(int i) {
//         if (p.get(i) == i) return i;
//         else {
//             int ret = findSet(p.get(i)); p.set(i, ret);
//             return ret; } }

//     public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

//     public void unionSet(int i, int j) {
//         if (!isSameSet(i, j)) { numSets--;
//             int x = findSet(i), y = findSet(j);
//             // rank is used to keep the tree short
//             if (rank.get(x) > rank.get(y)) { 
//                 p.set(y, x); 
//                 setSize.set(x, setSize.get(x) + setSize.get(y)); 
//                 reps.remove(y);}
//             else { 
//                 p.set(x, y); 
//                 setSize.set(y, setSize.get(y) + setSize.get(x)); 
//                 reps.remove(x);
//                 if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y) + 1); 
//             } 
//         } 
//     }
//     public int numDisjointSets() {return numSets; }
//     public int sizeOfSet(int i) { return setSize.get(findSet(i)); }
    
//     public void answer(PrintWriter pw) {
//         pw.print(numDisjointSets() + " ");
//         // counting sort O(n)
//         int[] counts = new int[n];
//         for (int r : reps) {
//             counts[sizeOfSet(r)]++;
//         }
//         for (int i=n-1;i>0;i--) {
//             if (counts[i]==0) continue;
//             else if (counts[i]==1) pw.print(i+ " ");
//             else pw.print(i+"x"+counts[i]+ " ");
//         }
//         pw.print("\n");
//     }
// }
