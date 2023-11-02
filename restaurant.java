import java.io.*;
import java.util.*;

public class restaurant {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio(System.in, System.out);
        int count = 0; 

        while (io.hasMoreTokens()) {
            int N = io.getInt();

            if (N == 0) {
                break;
            }

            int[] pileSum = new int[3]; // tracks the current sum 

            while (N-- > 0) {
                String command = io.getWord();
                int m = io.getInt();
                if (command.equals("DROP")) {
                    pileSum[2] += m;
                    io.println(String.format("DROP 2 %d", m));
                } else if (command.equals("TAKE")) {
                    if (pileSum[1] >= m) {
                        pileSum[1] -= m;
                        io.println(String.format("TAKE 1 %d", m));
                    } else { // pile 1 smaller than what we can take 
                        if (pileSum[1] != 0) { // move condition 
                            io.println(String.format("TAKE 1 %d", pileSum[1]));
                            m -= pileSum[1];
                            pileSum[1] = 0; 
                        }
                        io.println(String.format("MOVE 2->1 %d", pileSum[2])); 
                        pileSum[1] = pileSum[2] - m;
                        pileSum[2] = 0;
                        io.println(String.format("TAKE 1 %d", m));
                    }
                }
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
