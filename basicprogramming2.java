import java.util.*;
public class basicprogramming2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numN = sc.nextInt();
        int t = sc.nextInt();
        sc.nextLine();
        // positive 32 bit signed integers: max -> 2^31 therefore accnt fr long 
        ArrayList<Long> arr = new ArrayList<>();
        for (int i = 0; i < numN; i++) {
            arr.add(sc.nextLong());
        }
        if (t == 1) {
            boolean found = false;
            Set<Long> set = new HashSet<>(arr);
            for (long num : arr) {
                long complement = 7777 - num;
                if (set.contains(complement) && num != complement) {
                    System.out.println("Yes");
                    found = true;
                    break; 
                }
            }
            if (!found) {
                System.out.println("No");
            }
        } else if (t == 2) {
            Set<Long> tempArr = new HashSet<>();
            boolean unique = true;
            for (long num : arr) {
                if (tempArr.contains(num)) {
                    System.out.println("Contains duplicate");
                    unique = false;
                    break;
                }
                tempArr.add(num);
            }
            if (unique) {
                System.out.println("Unique");
            }
        } else if (t == 3) {
            int criteria = numN / 2;
            long candidate = arr.get(0);
            int count = 1;
            for (int i = 1; i < arr.size(); i++) {
                long current = arr.get(i);
                if (current == candidate) {
                    count++;
                } else {
                    if (count > criteria) {
                        System.out.println(candidate);
                        return;
                    }
                    candidate = current;
                    count = 1;
                }
            }
            if (count > criteria) {
                System.out.println(candidate);
            } else {
                System.out.println(-1);
            }   
        } else if (t == 4) {
            // change quick select O(n) 
            Collections.sort(arr); 
            if (numN%2 != 0) {
                int index = (numN + 1) / 2;
                System.out.println(arr.get(index));
            } else {
                int index = numN/2 - 1;
                System.out.println("" + arr.get(index) + " " + arr.get(index+1));
            }
        } else if (t == 5) {
            Collections.sort(arr);
            for (long val : arr) {
                if (val >= 100 && val <= 999) {
                    System.out.print(val);
                    System.out.print(" ");
                }
            }
        } 
    }
}
