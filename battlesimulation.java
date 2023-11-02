import java.io.*;
import java.util.*;


public class battlesimulation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        HashMap<String, String> hshmp = new HashMap<>();
        hshmp.put("R", "S");
        hshmp.put("B", "K");
        hshmp.put("L", "H");

        HashSet<String> combomap = new HashSet<>();
        combomap.add("RBL");
        combomap.add("RLB");
        combomap.add("LBR");
        combomap.add("LRB");
        combomap.add("BLR");
        combomap.add("BRL");
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        String A = st.nextToken();
        String queue = "";

        for (int i = 0; i < A.length() ; i++) {
            String val = A.substring(i, i + 1); 

            if (queue.length() < 3) {
                queue = queue + val;
            } else {
                if (combomap.contains(queue)) {
                    pw.print("C");
                    queue = val;
                } else {
                    String left = queue.substring(0, 1);
                    String right = queue.substring(1, queue.length());
                    pw.print(hshmp.get(left));
                    queue = right + val; 
                }
            }
        }

        while (queue.length() > 0) {
            if (combomap.contains(queue)) {
                pw.print("C");
                queue = "";
            } else {
                String left = queue.substring(0, 1);
                queue  = queue.substring(1, queue.length());
                pw.print(hshmp.get(left));
            }
        }

        pw.flush();
        pw.close();
    }
}
