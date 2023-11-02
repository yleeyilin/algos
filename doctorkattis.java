import java.io.*;
import java.util.*;
/*
 * N - nuber of injured cats 
 * given names, level, future infection 
 * 
 * priority queue: 
 * - highest infection 
 * - tie break to time arrived 
 * 
 * commands:
 * - arriveatclinic 
 * - update infection level (max to be 100)
 * - treated 
 * - query: print peek() 
 * 
 * To be optimised further
 */

public class doctorkattis {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n, t, level, cnt = 0;
        n = Integer.parseInt(br.readLine());
        String name;
        TreeSet<Tuple> cats = new TreeSet<>();
        HashMap<String, Tuple> pointers = new HashMap<>();
        Tuple it;

        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());

            if (t == 0) {
                name = st.nextToken();
                level = Integer.parseInt(st.nextToken());
                level = -level;
                it = new Tuple(level, cnt, name);
                cats.add(it);
                pointers.put(name, it);
                cnt++;
            } else if (t == 1) {
                name = st.nextToken();
                level = Integer.parseInt(st.nextToken());
                Tuple current = pointers.get(name);
                int cur_level = current.level;
                int cur_order = current.order;
                cats.remove(current);
                it = new Tuple(-level + cur_level, cur_order, name);
                cats.add(it);
                pointers.put(name, it);
            } else if (t == 2) {
                name = st.nextToken();
                Tuple current = pointers.get(name);
                cats.remove(current);
                pointers.remove(name);
            } else {
                if (cats.isEmpty()) {
                    pw.println("The clinic is empty");
                } else {
                    it = cats.first();
                    level = it.level;
                    name = it.name;
                    pw.println(name);
                }
            }
        }

        pw.flush();
        pw.close();
    }

    public static class Tuple implements Comparable<Tuple> {
        int level, order;
        String name;

        public Tuple(int level, int order, String name) {
            this.level = level;
            this.order = order;
            this.name = name;
        }

        @Override
        public int compareTo(Tuple other) {
            if (this.level != other.level) {
                return Integer.compare(this.level, other.level);
            } else {
                return Integer.compare(this.order, other.order);
            }
        }
    }
}


// import java.io.*;
// import java.util.*;
// /*
//  * N - nuber of injured cats 
//  * given names, level, future infection 
//  * 
//  * priority queue: 
//  * - highest infection 
//  * - tie break to time arrived 
//  * 
//  * commands:
//  * - arriveatclinic 
//  * - update infection level (max to be 100)
//  * - treated 
//  * - query: print peek() 
//  */
// public class doctorkattis {
//     public static class Cat {
//         String name;
//         int time;
//         int infection;

//         public Cat(String name, int time, int infection) {
//             this.name = name;
//             this.time = time;
//             this.infection = infection;
//         }
//     }
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         PrintWriter pw = new PrintWriter(System.out);

//         StringTokenizer st = new StringTokenizer(br.readLine());
//         int N = Integer.parseInt(st.nextToken());
//         TreeSet<Cat> tSet = new TreeSet<Cat>(new Comparator<Cat>() {
//             public int compare(Cat c1, Cat c2) {
//                 if (c1.infection != c2.infection) {
//                     return Integer.compare(c2.infection, c1.infection);
//                 } else {
//                     return Integer.compare(c2.time, c1.time);
//                 }
//             }
//         });
//         HashMap<String, Cat> hshmp = new HashMap<>();


//         int arrivalTime = 0; 
//         while (N-- > 0) {
//             st = new StringTokenizer(br.readLine());
//             int command = Integer.parseInt(st.nextToken());
//             if (command == 0) {
//                 String name = st.nextToken();
//                 Cat arrivedCat = new Cat(name, arrivalTime, -Integer.parseInt(st.nextToken()));
//                 tSet.add(arrivedCat);
//                 hshmp.put(name, arrivedCat);
//                 arrivalTime++;
//             } else if (command == 1) {
//                 Cat newCat = hshmp.get(st.nextToken());
//                 tSet.remove(newCat);
//                 newCat.infection = newCat.infection - Integer.parseInt(st.nextToken());
//                 tSet.add(newCat);
//                 hshmp.put(newCat.name, newCat);
//             } else if (command == 2) {
//                 Cat newCat = hshmp.get(st.nextToken());
//                 tSet.remove(newCat);
//                 hshmp.remove(newCat.name);
//             } else if (command == 3) {
//                 pw.println(tSet.size() == 0? "The clinic is empty" : tSet.last().name);
//             }
//         }

//         pw.flush();
//         pw.close();
//     }
// }

