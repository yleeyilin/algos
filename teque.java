import java.io.*;
import java.util.*;

public class teque {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        HashMap<Integer, Integer> front = new HashMap<>();
        HashMap<Integer, Integer> back = new HashMap<>();
        
        int frontHead = -1;
        int frontTail = 0;
        int backHead = -1;
        int backTail = 0;


        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("push_back")) {
                back.put(backTail++, Integer.parseInt(st.nextToken()));
            } else if (command.equals("push_front")) {
                front.put(frontHead--, Integer.parseInt(st.nextToken()));
            } else if (command.equals("push_middle")) {
                front.put(frontTail++, Integer.parseInt(st.nextToken()));
            } else if (command.equals("get")) {
                int indexing = Integer.parseInt(st.nextToken());
                if (indexing < front.size()) {
                    pw.println(front.get(indexing + 1 + frontHead));
                } else if (indexing >= front.size()) {
                    pw.println(back.get(indexing - front.size() + backHead + 1));
                }
            }
            // rebalance if back is bigger 
            while (back.size() - front.size() > 0) {
                front.put(frontTail++, back.get(backHead + 1));
                back.remove(++backHead);
            } 
            // rebalance if front is bigger 
            while (front.size() - back.size() > 1) {
                back.put(backHead--, front.get(frontTail - 1));
                front.remove(--frontTail);
            }
        }

        pw.flush();
        pw.close();
    }
}
