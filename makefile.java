import java.io.*;
import java.util.*;
// incomplete
public class makefile {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();
            String curr = temp.substring(0, temp.length()-1);

            String collect = "";
            while (st.hasMoreTokens()) {
                if (collect.length() != 0) {
                    collect += " ";
                }
                collect += curr;
            }
            pw.println(collect);
        }


        pw.println(2); 

        pw.flush();
        pw.close();
    }
}
