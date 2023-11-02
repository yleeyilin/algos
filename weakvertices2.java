import java.util.*;

class WeakVertices2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            int n = sc.nextInt();
            if (n == -1) {
                break; // Exit the loop
            }

            int[][] AM = new int[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    AM[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < n; i++) { // Check if i is a weak vertex
                boolean weak = true;
                for (int j = 0; j < n; j++) {
                    if (AM[i][j] == 1) { // Changed from AM[i][j] == 0 to AM[i][j] == 1
                        for (int k = 0; k < n; k++) { // Fixed the loop condition
                            if (AM[i][k] == 1 && AM[k][j] == 1) { // Updated conditions
                                weak = false;
                                break; // No need to continue checking
                            }
                        }
                    }
                }
                if (weak)
                    System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
