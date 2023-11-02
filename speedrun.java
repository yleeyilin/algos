import java.io.*;
import java.util.*;

// Earliest Deadline First Scheduling 
/*
Greedy by taking the smallest deadline that does not intersect 
 */

public class speedrun {
    static class Interval {
        int start;
        int end;
        int interval; 

        public Interval(int start, int end) {
            this.start = start;
            this.end = end; 
            this.interval = end - start + 1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int G = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        LinkedList<Interval> selected = new LinkedList<>();

        PriorityQueue<Interval> setInterval = new PriorityQueue<>(new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return Integer.compare(i1.end, i2.end); 
            }
        });


        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int tstart = Integer.parseInt(st.nextToken());
            int tend = Integer.parseInt(st.nextToken());
            setInterval.add(new Interval(tstart, tend));
        }

        while (setInterval.size() != 0) {
            Interval curr = setInterval.poll(); // get the smallest deadline 
            pw.println(curr.end);
            if (selected.size() == 0) {
                selected.add(curr);
            } else {
                Interval next = selected.getLast();
                if (next.end <= curr.start) {
                    selected.add(curr);
                }
            }
        }

        pw.println(selected.size() >= G? "YES" : "NO"); 

        pw.flush();
        pw.close();
    }
}