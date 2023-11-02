import java.io.*;
import java.util.*;
/*
 * How to use? 
 * 1. Instantiation 
 * Kattio io = new Kattio(System.in, System.out);
 * 
 * 2. Retrieve values 
 */

 /*
  * The crux of this question lies in the adaptation of the UFDS data structure. 
  * How to track the new parents of the set itself 
  */
public class infschema {
    public static void main(String[] args) {
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
                io.flush();
            }
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