import java.io.*;
import java.util.*;

public class proofs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int counter = 0;
        HashSet<String> proven = new HashSet<>();

        boolean hypothesis = true;
        boolean correct = true;
        while (n-- > 0) {
            counter++;
            st = new StringTokenizer(br.readLine());
            
            while (st.hasMoreTokens()) {
                String next = st.nextToken();
                // if arrow, it is now reaching conclusion 
                if (next.equals("->")) {
                    hypothesis = false; 
                } else if (hypothesis) {  
                    if (!proven.contains(next)) {
                        correct = false;
                        break;
                    }
                    // next must be in hashset 
                    // if not break everything and print line number 
                } else {
                    // add 
                    proven.add(next);
                }
            }
            if (!correct) {
                pw.println(counter);
                break;
            }
            hypothesis = true; 

        }
        if (correct) {
            pw.println("correct");
        }
        pw.flush();
        pw.close();
    }
}
