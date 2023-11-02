import java.util.*;
public class delimitersoup {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextInt();
        sc.nextLine();
        String[] arr = sc.nextLine().split("");
        ArrayList<String> front = new ArrayList<>();
        boolean noerror = true;
        for (int i = 0; i < arr.length; i++) {
            String thisStr = arr[i];
            if (thisStr.equals("(") || thisStr.equals("[") || thisStr.equals("{")) {
                front.add(thisStr);
            } else if (thisStr.equals(")")) {
                int indexOf = front.indexOf("(");
                if (front.size() >= 1) {
                    boolean isCorrect = front.get(front.size()-1).equals("(");
                    if (indexOf == -1 || !isCorrect) {
                        System.out.print(thisStr);
                        System.out.print(" ");
                        System.out.print(i);
                        noerror = false;
                        break;
                    }
                    front.remove(front.size()-1);
                } else {
                    System.out.print(thisStr);
                    System.out.print(" ");
                    System.out.print(i);
                    noerror = false;
                    break;
                }
            } else if (thisStr.equals("}")) {
                int indexOf = front.indexOf("{");
                if (front.size() >= 1) {
                    boolean isCorrect = front.get(front.size()-1).equals("{");
                    if (indexOf == -1 || !isCorrect) {
                        System.out.print(thisStr);
                        System.out.print(" ");
                        System.out.print(i);
                        noerror = false;
                        break;
                    }
                    front.remove(front.size()-1);
                } else {
                    System.out.print(thisStr);
                    System.out.print(" ");
                    System.out.print(i);
                    noerror = false;
                    break;
                }
            } else if (thisStr.equals("]")) {
                int indexOf = front.indexOf("[");
                if (front.size() >= 1) {
                    boolean isCorrect = front.get(front.size()-1).equals("[");
                    if (indexOf == -1 || !isCorrect) {
                        System.out.print(thisStr);
                        System.out.print(" ");
                        System.out.print(i);
                        noerror = false;
                        break;
                    }
                    front.remove(front.size()-1);
                } else {
                    System.out.print(thisStr);
                    System.out.print(" ");
                    System.out.print(i);
                    noerror = false;
                    break;
                }
            }
        }
        if (noerror) {
            System.out.println("ok so far");
        }
    }
}
