import java.io.*;
import java.util.*;

/*
 *  Corner test case if PVH cant afford any students, should not go into negatives 
 */

public class bubbletea {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer> tea = new ArrayList<>();
        ArrayList<Integer> toppings = new ArrayList<>();

        int N = Integer.parseInt(st.nextToken()); // num of kinds of tea 
        st = new StringTokenizer(br.readLine());
        int ncopy = N;
        while (ncopy-- > 0) {
            tea.add(Integer.parseInt(st.nextToken()));  // price of each kind of tea
        }

        st = new StringTokenizer(br.readLine()); 

        int M = Integer.parseInt(st.nextToken()); // num of kinds of toppings 
        st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            toppings.add(Integer.parseInt(st.nextToken())); // price of each kind of toppings 
        }

        int cheapest = Integer.MAX_VALUE; 
        for (int i = 0 ; i < N ; i++ ) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int teaPrice = tea.get(i);
            while (K-- > 0) {
                int toppingPrice = toppings.get(Integer.parseInt(st.nextToken()) - 1);
                int total = teaPrice + toppingPrice; 
                if (total < cheapest) {
                    cheapest = total; 
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int number = X/cheapest;
        pw.println(number > 1 ? number - 1 : 0);

        pw.flush();
        pw.close();
    }
}
