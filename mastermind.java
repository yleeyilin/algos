import java.io.*;
import java.util.*;

public class mastermind {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        String code = st.nextToken();
        String guess = st.nextToken();
        HashMap<String, Integer> trackCode = new HashMap<>(); // character, frequency 
        ArrayList<String> leftGuess = new ArrayList<>();

        int r = 0; 
        int s = 0;
        for (int i = 0; i < n ; i++) {
            String code1 = code.substring(i, i + 1);
            String guess1 = guess.substring(i, i + 1);
            if (code1.equals(guess1)) {
                r++;
            } else {
                leftGuess.add(guess1);
                if (trackCode.containsKey(code1)) {
                    trackCode.put(code1, trackCode.get(code1) + 1);
                } else {
                    trackCode.put(code1, 1);
                }
            }
        }

        for (String left : leftGuess) {
            if (trackCode.containsKey(left)) {
                if (trackCode.get(left) >= 1) {
                    trackCode.put(left, trackCode.get(left) - 1);
                    s++;
                }
            }
        }

        pw.println(r + " " + s); 

        pw.flush();
        pw.close();
    }
}
