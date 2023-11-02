/*
A0259416X
Lee Yi Lin
Lab Group 3 
TA: Ramapriyan
*/ 
import java.util.*;
public class sim {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for (int j = 0; j < T; j++) {
            String input = sc.nextLine();
            LinkedList<Character> linkedList = new LinkedList<>();
            int pointer = 0;
            for (char curr : input.toCharArray()) {
                if (curr == ']') {
                    pointer = linkedList.size();
                } else if (curr == '[') {
                    pointer = 0;
                } else if (curr == '<') {
                    if (pointer > 0) {
                        linkedList.remove(--pointer);
                    }
                } else {
                    linkedList.add(pointer++, curr);
                }
            }
            StringBuilder res = new StringBuilder();
            for (char ch : linkedList) {
                res.append(ch);
            }
            System.out.println(res.toString());
        }
    }
}