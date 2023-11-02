/*
A0259416X
Lee Yi Lin
Lab Group 3 
TA: Ramapriyan
*/ 

import java.util.ArrayList;
import java.util.Scanner;

public class falcondive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numLines = scanner.nextInt();
        int numChar = scanner.nextInt();
        char subChar = scanner.next().charAt(1);
        scanner.nextLine();
        String[] firstFrame = new String[numLines];
        String[] secondFrame = new String[numLines];
        String[] finalFrame = new String[numLines];
        ArrayList<Integer> firstFalcon = new ArrayList<>();
        ArrayList<Integer> secondFalcon = new ArrayList<>();

        for (int i = 0; i < numLines; i++) {
            firstFrame[i] = scanner.nextLine();
        }
        scanner.nextLine();
        for (int i = 0; i < numLines; i++) {
            secondFrame[i] = scanner.nextLine();
        }

        for (int i = 0; i < numLines; i++) {
            String firstFrameStr = firstFrame[i];
            String secondFrameStr = secondFrame[i];
            StringBuilder res = new StringBuilder();
            for (int j = 0; j < numChar; j++) {
                // to add falcon coordinates
                char firstChar = firstFrameStr.charAt(j);
                char secondChar = secondFrameStr.charAt(j);
                boolean isFirstCharSub = (firstChar == subChar);
                if (firstChar == subChar) {
                    firstFalcon.add(i);
                    firstFalcon.add(j);
                }
                if (secondChar == subChar) {
                    secondFalcon.add(i);
                    secondFalcon.add(j);
                }
                // to clear falcon 
                res.append(isFirstCharSub ? secondChar : firstChar);
            }
            finalFrame[i] = res.toString();
        }
        for (int i = 0; i < firstFalcon.size(); i ++) {
            firstFalcon.set(i, 2*secondFalcon.get(i) - firstFalcon.get(i));
        }
        for (int i = 0; i < firstFalcon.size(); i += 2) {
            int row = firstFalcon.get(i);
            int col = firstFalcon.get(i+1);
            if (row >= 0 && row < numLines) {
                String chosen = finalFrame[row];
                int chosenLength = chosen.length();
                char[] chosenChars = chosen.toCharArray(); 
                if (col >= 0 && col < chosenLength) {
                    chosenChars[col] = subChar;
                    finalFrame[row] = new String(chosenChars); 
                }
            }
        }
        for (String line : finalFrame) {
            System.out.println(line);
        }
    }
}

