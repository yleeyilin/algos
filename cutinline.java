import java.util.Scanner;
import java.util.ArrayList;

public class cutinline {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numPeople = sc.nextInt();
        sc.nextLine();
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < numPeople; i++) {
            arr.add(sc.nextLine().split(" ")[0]);
        }
        int numOps = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numOps; i++) {
            String[] lne = sc.nextLine().split(" ");
            if (lne[0].equals("cut")) {
                String cutter = lne[1];
                String gettingCut = lne[2];
                int indexToCut = arr.indexOf(gettingCut);
                arr.add(indexToCut, cutter);
            } else if (lne[0].equals("leave")) {
                arr.remove(lne[1]);
            } 
        }
        for (String name:arr) {
            System.out.println(name);
        }
    }
}
