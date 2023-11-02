import java.io.*;
import java.util.*;
/*
 * Track interval and frequency 
 * Return the smallest one with the highest frequency 
 */
public class cakeymccakeface {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // number of times the entry detector was triggered
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // number of times the exit detector was triggered
        HashMap<Integer, Integer> hshset = new HashMap<>();

        StringTokenizer Nline = new StringTokenizer(br.readLine());
        int[] Narr = new int[N];
        for (int i = 0; i < N; i++) {
            Narr[i] = Integer.parseInt(Nline.nextToken());
        }
        StringTokenizer Mline = new StringTokenizer(br.readLine());
        int[] Marr = new int[M];
        for (int i = 0; i < M; i++) {
            Marr[i] = Integer.parseInt(Mline.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int start = Narr[i];
            for (int j = 0; j < M; j++) {
                int end = Marr[j];
                int diff = end - start;
                if (diff > 0) {
                    hshset.put(diff, hshset.getOrDefault(diff, 0) + 1);
                }
            }
        }

        int maxFreq = -1;
        int corrVal = 0;

        for (Map.Entry<Integer, Integer> entry : hshset.entrySet()) {
            int diff = entry.getKey();
            int freq = entry.getValue();

            // if current one has a greater frequency
            if (freq > maxFreq) {
                maxFreq = freq;
                corrVal = diff;
            }

            // if same frequency, take the smaller value
            if (freq == maxFreq) {
                corrVal = Math.min(corrVal, diff);
            }
        }

        pw.println(corrVal);

        pw.flush();
        pw.close();
    }
}
