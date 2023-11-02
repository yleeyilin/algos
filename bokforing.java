import java.io.*;
import java.util.*;
/*
optimisation by not putting in unnecessary keys when restarting  
 */
public class bokforing {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> hshMp = new HashMap<>();

        int restart = 0; 

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int i, x;
            if (command.equals("SET")) {
                i = Integer.parseInt(st.nextToken());
                x = Integer.parseInt(st.nextToken());
                hshMp.put(i, x);
            } else if (command.equals("PRINT")) {
                i = Integer.parseInt(st.nextToken());
                if (!hshMp.containsKey(i)) {
                    pw.println(restart);
                    hshMp.put(i, restart);
                } else {
                    pw.println(hshMp.get(i));
                }
            } else if (command.equals("RESTART")) {
                restart = Integer.parseInt(st.nextToken());
                hshMp.clear();
            }
        }

        pw.flush();
        pw.close();
    }
}
