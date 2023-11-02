import java.util.Scanner;

public class tennis {

    public static int[] convert(String args) {
        String[] temp = args.split(":");
        int[] output = new int[2];
        for (int i = 0; i < 2; i++) {
            output[i] = Integer.parseInt(temp[i]);
        }
        return output;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] names = scanner.nextLine().split(" ");
        int numMatch = Integer.parseInt(scanner.nextLine());
        boolean isFirstFederer = (names[0].equals("federer"));
        boolean isSecondFederer = (names[1].equals("federer"));
        for (int i = 0; i < numMatch; i++) {
            boolean isNegative = false;
            boolean isNotValidScore = false;
            String[] sets = scanner.nextLine().split(" ");
            int[] scoreTracker = new int[5];
            for (int j = 0; j < sets.length; j++) {
                int[] scores = convert(sets[j]);
                if (scores[0] < 0 || scores[1] < 0 ) {
                    isNegative = true;
                } 
                if (scores[1] == 6 && scores[0] == 7) {
                    // first player wins tiebreaker represented by 3
                    scoreTracker[j] = 3;
                } else if (scores[0] == 6 && scores[1] == 7){
                    // second player wins tiebreaker represented by 4
                    scoreTracker[j] = 4;
                } else if (j < 2 && (scores[1] >= 8 || scores[0] >= 8)) {
                    // condition for e.g. 7:10 4:10 
                    isNotValidScore = true;
                } else if (j < 2 && ((scores[1] == 7 && scores[0] < 5) || (scores[0] == 7 && scores[1] < 5))) {
                    // condition for e.g. 4:7 
                    isNotValidScore = true;
                } else if ((scores[0] > scores[1]) && (scores[0] - scores[1] >= 2) && scores[0] >= 6){
                    // first player wins is represented by 1
                    scoreTracker[j] = 1;
                } else if ((scores[1] > scores[0]) && (scores[1] - scores[0] >= 2) && scores[1] >= 6){
                    // second player wins is represented by 2
                    scoreTracker[j] = 2;
                } else if (scores[1] == scores[0] && j > 1) {
                    // next round tie 
                    scoreTracker[j] = 6;
                } else {
                    // anything that is for sure invalid 
                    scoreTracker[j] = 5;
                }
            }
            boolean isForSureInvalid = false;
            for (int winner : scoreTracker) {
                if (winner == 5) {
                    isForSureInvalid = true;
                }
            }
            if (isForSureInvalid || isNegative || isNotValidScore) {
                System.out.println("ne");
            } else if (scoreTracker[3] == 3 || scoreTracker[4] == 3 || scoreTracker[2] == 3 || 
            scoreTracker[3] == 4 || scoreTracker[4] == 4 || scoreTracker[2] == 4) {
                // check if valid or not
                System.out.println("ne");
            } else {
                int firstWon = 0;
                int secondWon = 0;
                for (int winner : scoreTracker) {
                    if (winner == 3 || winner == 1) {
                        firstWon += 1;
                    } else if (winner == 4 || winner == 2) {
                        secondWon += 1;
                    }
                }
                if (firstWon == secondWon) { 
                    System.out.println("ne");
                } else if (secondWon > 0 && isFirstFederer) {
                    System.out.println("ne");
                } else if (firstWon > 0 && isSecondFederer) {
                    System.out.println("ne");
                } else if ((firstWon > secondWon && firstWon == 2) || 
                (secondWon > firstWon && secondWon == 2)) {
                    System.out.println("da");
                } else {
                    System.out.println("ne");
                }
            }
        }
    }
}