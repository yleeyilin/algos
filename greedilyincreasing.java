import java.io.*;
import java.util.*;

public class greedilyincreasing {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        ArrayList<Integer> stk = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        while (N-- > 0) {
            int next = Integer.parseInt(st.nextToken());
            if (stk.size() == 0 || stk.get(stk.size() - 1) < next) {
                stk.add(next);
            }
        }
        pw.println(stk.size());
        for (int i : stk) {
            pw.print(i + " ");
        }

        pw.flush();
        pw.close();
    }
}
