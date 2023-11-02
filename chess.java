import java.io.*;
import java.util.*;

/*
Observe that bishop can only reach fields of the same colour 
Can reach anywhere in one / two moves (note that there are 4 kinds of move, upleft, upright, downleft, downright)
if one move: straight forward
if two moves: 
*/

public class chess {

    public static int[] upright(int firstl, int firstr, int secondl, int secondr) {
        while (firstl < 8 && firstr < 8) {
            firstl++;
            firstr++;
            if (firstl - secondl == secondr - firstr) {
                int[] temp = {firstl, firstr};
                return temp;
            }
        }
        return null;
    }

    public static int[] upleft(int firstl, int firstr, int secondl, int secondr) {
        while (firstl < 8 && firstr > 1) {
            firstl++;
            firstr--;
            if (secondl - firstl == secondr - firstr) {
                int[] temp = {firstl, firstr};
                return temp;
            }
        }
        return null;
    }

    public static int[] downleft(int firstl, int firstr, int secondl, int secondr) {
        while (firstl > 1 && firstr > 1) {
            firstl--;
            firstr--;
            if (secondl - firstl == firstr - secondr) {
                int[] temp = {firstl, firstr};
                return temp;
            }
        }
        return null;
    }

    public static int[] downright(int firstl, int firstr, int secondl, int secondr) {
        while (firstl > 1 && firstr < 8) {
            firstl--;
            firstr++;
            if (firstl - secondl == firstr - secondr) {
                int[] temp = {firstl, firstr};
                return temp;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
    
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
    
        HashMap<String, Integer> hsh = new HashMap<>();
        hsh.put("A", 8);
        hsh.put("B", 7);
        hsh.put("C", 6);
        hsh.put("D", 5);
        hsh.put("E", 4);
        hsh.put("F", 3);
        hsh.put("G", 2);
        hsh.put("H", 1);
        
        HashMap<Integer, String> hsh2 = new HashMap<>();
        hsh2.put(1, "H");
        hsh2.put(2, "G");
        hsh2.put(3, "F");
        hsh2.put(4, "E");
        hsh2.put(5, "D");
        hsh2.put(6, "C");
        hsh2.put(7, "B");
        hsh2.put(8, "A");

    
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int startF = hsh.get(st.nextToken());
            int startS = Integer.parseInt(st.nextToken());
            int endF = hsh.get(st.nextToken());
            int endS = Integer.parseInt(st.nextToken());
    
            // check if impossible
            boolean startSame = (startF % 2 == 0) == (startS % 2 == 0);
            boolean endSame = (endF % 2 == 0) == (endS % 2 == 0);
            if (startSame != endSame) {
                pw.println("Impossible");
            } else if (startF == endF && startS == endS) {
                pw.println(0 + " " + hsh2.get(startF) + " " + startS);
            } else {
                // one move only
                if (Math.abs(startF - endF) == Math.abs(startS - endS)) {
                    pw.println(1 + " " + hsh2.get(startF) + " " + startS + " " + hsh2.get(endF) + " " + endS);
                } else {
                    // need 2 moves
                    int[] firstMove = null;
    
                    // Check all four possible two-move directions
                    int[] temp;
    
                    temp = upright(startF, startS, endF, endS);
                    if (temp != null) {
                        firstMove = temp;
                    }
    
                    temp = upleft(startF, startS, endF, endS);
                    if (temp != null) {
                        firstMove = temp;
                    }
    
                    temp = downright(startF, startS, endF, endS);
                    if (temp != null) {
                        firstMove = temp;
                    }
    
                    temp = downleft(startF, startS, endF, endS);
                    if (temp != null) {
                        firstMove = temp;
                    }
    
                    if (firstMove != null) {
                        pw.println(2 + " " + hsh2.get(startF) + " " + startS + " " + hsh2.get(firstMove[0]) + " " + firstMove[1] + " " + hsh2.get(endF) + " " + endS);
                    } else {
                        pw.println("Impossible");
                    }
                }
            }
        }
    
        pw.flush();
        pw.close();
    }
    
}
