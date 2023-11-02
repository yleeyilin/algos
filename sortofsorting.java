import java.util.*;
import java.io.*;

public class sortofsorting {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            } 
            String[] names = new String[n];
            for (int i = 0; i < n; i++) {
                names[i] = br.readLine();
            }
            Arrays.sort(names, new Comparator<String>() {
                public int compare(String s1, String s2) {
                    return s1.substring(0,2).compareTo(s2.substring(0,2));
                }
            });
            for (int i = 0; i < n; i++) {
                pw.println(names[i]);
            }
            pw.println();
        }
        pw.close();
    }
}