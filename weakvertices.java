import java.io.*;
import java.util.*;

public class weakvertices {
    public static void main(String[] args) {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        // PrintWriter pw = new PrintWriter(System.out);

        while (true) {
            // StringTokenizer st = new StringTokenizer(br.readLine());
            // int n = Integer.parseInt(st.nextToken());
            int n = sc.nextInt();

            if (n == -1) {
                break; // Exit the loop
            }

            // HashSet<Integer> hshset = new HashSet<>(); // contains integers that pass; 
            int[][] adjMatrix = new int[n][n];

            // create adj matrix 
            for (int row = 0; row < n; ++row) {
                // st = new StringTokenizer(br.readLine());
                for (int col = 0; col < n ; ++col) {
                    // adjMatrix[row][col] = Integer.parseInt(st.nextToken());
                    adjMatrix[row][col] = sc.nextInt();
                }
            }

            // matching algorithm 
            // ArrayList<Integer> currArrows = new ArrayList<>(); // stores the cols with 1s
            // for (int row = 0; row < n; row++) { // i
            //     for (int col = 0; col < n ; col++) { // j 
            //         if (adjMatrix[row][col] == 1) {
            //             currArrows.add(col);
            //             for (int tempCol : currArrows) { // k 
            //                 if (adjMatrix[col][tempCol] == 1 && adjMatrix[tempCol][row] == 1) {
            //                     hshset.add(row);
            //                     hshset.add(col);
            //                     hshset.add(tempCol); 
            //                 }
            //             }
            //         }
            //     }
            //     currArrows.clear();
            // }

            // for (int i = 0 ; i < n ; i++ ){
            //     if (!hshset.contains(i)) {
            //         pw.print(i);
            //         pw.print(" ");
            //     }
            // }

            for (int row = 0; row < n; row++) { // i
                boolean weak = true;
                for (int col = 0; col < n ; col++) { // j 
                    if (adjMatrix[row][col] == 1) {
                        for (int tempCol = 0; tempCol < n; tempCol++) { 
                            if (adjMatrix[row][tempCol] == 1 && adjMatrix[tempCol][col] == 1) {
                                weak = false;
                                break;
                            }
                        }
                    }
                }
                if (weak) {
                    System.out.print(row + " ");
                }
            }
            System.out.println();

            // pw.flush();
            // pw.close();
        }
    }
}
