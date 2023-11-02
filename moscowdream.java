import java.io.*;
import java.util.*;

/*
 * The trick here is the corner case where n < 3 
 */
public class moscowdream {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()); 
        int b = Integer.parseInt(st.nextToken()); 
        int c = Integer.parseInt(st.nextToken()); 
        int n = Integer.parseInt(st.nextToken()); 

        if (a == 0 || b == 0 || c == 0) {
            pw.println("NO");
        } else if (a + b + c < n || n < 3) {
            pw.println("NO"); 
        } else {
            pw.println("YES");
        }

        pw.flush();
        pw.close();
    }
}
