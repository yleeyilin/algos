import java.io.*;
import java.util.*;
/*
 * Sequence A with N integers, each integer is unique 
 * Subsequence can be obtained by removing 0-x numbers from start and end
 * Result: Number of different subsequences of odd length that have their median == B 
 * Median refers to value after being sorted  
 * 
 * Observation: 
 * If A is odd already, new substring can be done by removing the highest value and the smallest value each time 
 * If A is even, 
 * 
 * Steps:
 * Identify the position of the wanted median 
 * Calculate the freq of smaller and larger on left 
 * Calculate the freq of smaller and larger on right  
 * 
 * put all those in the same 
 * 
 * the crux of the problem is to balance both the LHS and the RHS 
 * 
 * TC1 :  LS = 3 , LL = 0, RS = 0, RL = 1 {0, 1}
 * TC2 :  LS = 2 , LL = 3, RS = 0, RL = 0 {0}
 * TC3 :  LS = 1 , LL = 2, RS = 2, RL = 1 {3, 2, 1, 0}
 * 
 * balancing factor of left and right 
 * left should be smaller 
 * right should be bigger 
 */

public class srednji {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int T = N;
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> valArr = new ArrayList<>();

        boolean isLeft = true; 
        int LS = 0;
        int LL = 0;
        int RS = 0;
        int RL = 0; 
        int leftPtr = 0;
        int rightPtr = N-1;
        
        while (N-- > 0) {
            int val = Integer.parseInt(st.nextToken());
            valArr.add(val);
            if (val == B) {
                isLeft = false;
            }
            // if value is larger 
            if (val > B) {
                if (isLeft) {
                    LL++;
                } else {
                    RL++;
                }
            } 
            // if value is smaller
            if (val < B) {
                if (isLeft) {
                    LS++;
                } else {
                    RS++;
                }
            }
        }

        int totalLeft = LL + LS;
        int totalRight = RL + RS;

        int perm = 1; 
        while (totalLeft != 0 && totalRight != 0 && leftPtr < T-1 && rightPtr > 0) {
            int leftHeavy = totalLeft - totalRight;
            if (totalLeft == totalRight) {
                int leftRemove = valArr.get(leftPtr);
                int rightRemove = valArr.get(rightPtr);
                if (leftRemove > B) {
                    LL--;
                } else {
                    LS--;
                } 
                if (rightRemove > B) {
                    RL--;
                } else {
                    RS--;
                }
                leftPtr++;
                rightPtr--;
                perm++;
                continue; 
            }
            if (leftHeavy > 0) {
                // left heavy -> 2 cases  
                // left and more large 
                if (LL > RL) {
                    break; 
                } else {
                    int leftRemove = valArr.get(leftPtr);
                    if (leftRemove > B) {
                        LL--;
                    } else {
                        LS--;
                    }
                    leftPtr++;
                }
            } else {
                // right heavy 
                // right and less large 
                if (RL < LL) {
                    break; 
                } else {
                    int rightRemove = valArr.get(rightPtr);
                    if (rightRemove > B) {
                        RL--;
                    } else {
                        RS--;
                    }
                    rightPtr--;
                }
            } 
        }
        pw.println(perm); 
        pw.flush();
        pw.close();
    }
}


// import java.util.HashMap;
// import java.util.Map;
// import java.util.Scanner;

// public class srednji {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         int n = scanner.nextInt();
//         int med = scanner.nextInt();

//         int[] v = new int[n];
//         int pos = 0; // tracks position of median

//         for (int i = 0; i < n; i++) {
//             v[i] = scanner.nextInt();
//             if (v[i] == med) {
//                 pos = i;
//             }
//         }

//         Map<Integer, Integer> m = new HashMap<>();
//         m.put(0, 1);
//         int sum = 0;
           // Right side of median 
//         for (int i = pos + 1; i < n; i++) {
//             if (v[i] > med) {
//                 sum++;
//             } else {
//                 sum--;
//             }
//             m.put(sum, m.getOrDefault(sum, 0) + 1);
//         }

//         long ans = m.get(0);
//         sum = 0;

//         for (int i = pos - 1; i >= 0; i--) {
//             if (v[i] > med) {
//                 sum++;
//             } else {
//                 sum--;
//             }
//             ans += m.getOrDefault(-sum, 0);
//         }

//         System.out.println(ans);
//     }
// }
