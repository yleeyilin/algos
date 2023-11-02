import java.io.*;
import java.util.*;

public class joinstrings {
    static public class Vertex { 
        String item;
        Vertex next;

        public Vertex(String item, Vertex next) {
            this.item = item;
            this.next = next;
        }
    }
    static public class FastList {
        Vertex head;
        Vertex tail;

        public FastList(String item) {
            this.head = new Vertex(item, null);
            this.tail = this.head;
        }

        // add to the back of the list 
        public void add(String val) {
            Vertex temp = new Vertex(val, null);
            this.tail.next = temp;
            this.tail = temp; 
        }
        
        // concatenate methods 
        public void combine(FastList v2) {
            this.tail.next = v2.head;
            this.tail = v2.tail;
        }

        // print method 
        public void printlist(PrintWriter pw) {
            Vertex currNode = this.head;
            while (currNode != null) {
                pw.print(currNode.item);
                currNode = currNode.next;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = N - 1;

        ArrayList<FastList> lst = new ArrayList<>();
        lst.add(null);

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            FastList temp = new FastList(st.nextToken());
            lst.add(temp);
        }

        int fVal = -1;
        if (N == 1) { // without this will hv rte 
            lst.get(1).printlist(pw);
        } else {
            while (S-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                fVal = a;
                lst.get(a).combine(lst.get(b));
            }
            lst.get(fVal).printlist(pw);
        }
        pw.flush();
        pw.close();
    }
}

// public class joinstrings {
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         PrintWriter pw = new PrintWriter(System.out);

//         StringTokenizer st = new StringTokenizer(br.readLine());
//         int N = Integer.parseInt(st.nextToken());
//         int S = N - 1;

//         HashMap<Integer, String> hshMap = new HashMap<>();
//         String[] seq = new String[N + 1];
//         int ptr = 1; 

//         for (int i = 0; i < seq.length ; i++) {
//             seq[i] = "" + i;
//         }

//         while (N-- > 0) {
//             st = new StringTokenizer(br.readLine());
//             hshMap.put(ptr++, st.nextToken());
//         }

//         while (S-- > 0) {
//             st = new StringTokenizer(br.readLine());
//             int a = Integer.parseInt(st.nextToken());
//             int b = Integer.parseInt(st.nextToken());
//             seq[a] = seq[a] + "" + seq[b];
//             seq[b] = "";
//         }

//         for (int i = 1; i < seq.length; i++) {
//             if (!seq[i].equals("")) {
//                 char[] ch = seq[i].toCharArray();
//                 for (int j = 0; j < ch.length ; j++) {
//                     int val = ch[j] - '0';
//                     pw.print(hshMap.get(val));
//                 }
//             }
//         }

//         pw.flush();
//         pw.close();
//     }
// }
