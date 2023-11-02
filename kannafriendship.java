/*
A0259416X
Lee Yi Lin
Lab Group 3 
TA: Ramapriyan
*/ 
// import java.io.*;
// import java.util.*;

// public class kannafriendship {
//     public static class Interval {
//         int start;
//         int end;
//         int interval_length;

//         public Interval(int start, int end) {
//             this.start = start;
//             this.end = end;
//             this.interval_length = end - start + 1;
//         }
//     }

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         PrintWriter pw = new PrintWriter(System.out); // Create a PrintWriter for output
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         Integer.parseInt(st.nextToken());
//         int Q = Integer.parseInt(st.nextToken());

//         TreeMap<Integer, Interval> treemp = new TreeMap<>();

//         int bullet_points = 0;

//         while (Q-- > 0) {
//             StringTokenizer tik = new StringTokenizer(br.readLine());
//             int ti = Integer.parseInt(tik.nextToken());
//             if (ti == 1) {
//                 int si = Integer.parseInt(tik.nextToken());
//                 int ei = Integer.parseInt(tik.nextToken());
//                 Interval intervl = new Interval(si, ei);

//                 Map.Entry<Integer, Interval> floorEntry = treemp.floorEntry(si);
//                 if (floorEntry != null) {
//                     Interval lInt = floorEntry.getValue();
//                     if (lInt.end >= si - 1) {
//                         bullet_points -= lInt.interval_length;
//                         intervl.start = Math.min(lInt.start, si);
//                         intervl.end = Math.max(lInt.end, ei);
//                         intervl.interval_length = intervl.end - intervl.start + 1;
//                         treemp.remove(floorEntry.getKey());
//                     }
//                 }

//                 Map.Entry<Integer, Interval> ceilingEntry = treemp.ceilingEntry(si);
//                 while (ceilingEntry != null) {
//                     Interval rInt = ceilingEntry.getValue();
//                     if (ei >= rInt.start) {
//                         bullet_points -= rInt.interval_length;
//                         intervl.end = Math.max(rInt.end, ei);
//                         intervl.interval_length = intervl.end - intervl.start + 1;
//                         treemp.remove(ceilingEntry.getKey());
//                         ceilingEntry = treemp.ceilingEntry(ceilingEntry.getKey());
//                     } else {
//                         break;
//                     }
//                 }
//                 treemp.put(intervl.start, intervl);
//                 bullet_points += intervl.interval_length;
                
//             } else if (ti == 2) {
//                 pw.println(bullet_points); 
//             }
//         }

//         pw.flush();
//         pw.close();
//     }
// }

import java.io.*;
import java.util.*;

/*
Greedy Problem 
 */
public class kannafriendship {
    public static class Interval {
        int start; 
        int end;
        int interval_length;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end; 
            this.interval_length = end - start + 1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        TreeSet<Interval> treemp = new TreeSet<Interval>(new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                // If an interval has an earlier start time than another, then it is considered smaller
                // If an interval has the same start time as another, then compare the end times of the intervals
                if (i1.start < i2.start) {
                    return -1;
                } else if (i1.start == i2.start) {
                    if (i1.end < i2.end) {
                        return -1;
                    } else if (i1.end == i2.end) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
                return 1; // i1.start > i2.start
            }
        });

        int bullet_points = 0;

        while (Q-- > 0) {
            StringTokenizer tik = new StringTokenizer(br.readLine());
            int ti = Integer.parseInt(tik.nextToken()); 
            if (ti == 1) {
                int si = Integer.parseInt(tik.nextToken()); 
                int ei = Integer.parseInt(tik.nextToken()); 
                Interval intervl = new Interval(si, ei);                

                // merge left
                Interval lInt = treemp.floor(intervl);
                if (lInt != null) {
                    // merge operation 
                    int s1 = lInt.start;
                    int e1 = lInt.end;
                    int s2 = intervl.start;
                    int e2 = intervl.end;
                    if (e1 >= s2 - 1) {
                        // if there is an intersection 
                        bullet_points -= lInt.interval_length;
                        intervl.start = Math.min(s1, s2);
                        intervl.end = Math.max(e1, e2);
                        intervl.interval_length = intervl.end - intervl.start + 1;
                        treemp.remove(lInt);
                    } 
                }

                // merge right
                Interval rInt = treemp.ceiling(intervl);
                while (rInt != null) {
                    // merge operation 
                    int s1 = rInt.start;
                    int e1 = rInt.end;
                    int e2 = intervl.end;
                    if (e2 >= s1) {
                        // if there is an intersection
                        bullet_points -= rInt.interval_length; 
                        intervl.end = Math.max(e1, e2);
                        intervl.interval_length = intervl.end - intervl.start + 1;
                        treemp.remove(rInt);
                        rInt = treemp.ceiling(rInt);
                    } else {
                        break;
                    }
                }

                // clean up after merging 
                treemp.add(intervl);
                bullet_points += intervl.interval_length;
            } else if (ti == 2) {
                pw.println(bullet_points);
            }
        }
        pw.flush();
        pw.close();
    }
}
