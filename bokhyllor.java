import java.util.*;

class bokhyllor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int small = scanner.nextInt();
        int medium = scanner.nextInt();
        int large = scanner.nextInt();
        scanner.nextLine(); 
        int width = scanner.nextInt();
        int[] numShelves = new int[60];
        int curr = 0;
        while (large > 0) {
          int temp = numShelves[curr];
          int diff = width - temp;
            if (diff%2 == 0 && medium > diff/2 && diff>3 && diff%3 != 0) {
                int val = diff/2;
                for (int i = 0; i < val; i++) {
                    numShelves[curr] += 2;
                }
                curr += 1;
                medium -= val;
            }
           if (temp + 3 > width) {
               curr += 1;
           } else {
               numShelves[curr] += 3;
               large -= 1;
           }
        }
        curr = 0;
        while (medium > 0) {
            int temp = numShelves[curr];
            if (temp + 2 > width) {
                curr += 1;
            } else {
                numShelves[curr] += 2;
                medium -= 1;
            }
        } 
        curr = 0;
        while (small > 0) {
            int temp = numShelves[curr];
            if (temp + 1 > width) {
                curr += 1;
            } else {
                numShelves[curr] += 1;
                small -= 1;
            }
        }
        int count = 0;
        for (int val:numShelves) {
            if (val == 0) {
                break;
            } else {
                count += 1;
            }
        }
        System.out.println(count);
    }
}
