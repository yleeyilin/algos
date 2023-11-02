import java.io.*;
import java.util.*;

public class bossbattle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        pw.println(N > 2 ? N - 2 : 1); 

        pw.flush();
        pw.close();
    }
}
