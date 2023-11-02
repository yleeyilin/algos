import java.io.*;
import java.util.*;
/*
Greedily minimise the annoyance at each level 
 */
public class annoyedcoworkers {
    static class Worker {
        long annoyance; 
        int increment; 

        public Worker(int a, int b) {
            this.annoyance = a;
            this.increment = b;
        }

        void askHelp() {
            this.annoyance += this.increment;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken()); // help 
        int c = Integer.parseInt(st.nextToken()); // coworkers 

        PriorityQueue<Worker> helpQ = new PriorityQueue<Worker>(new Comparator<Worker>() {
            public int compare(Worker w1, Worker w2) {
                // rank by annoyance and tie break by increment 
                // if (w1.annoyance > w2.annoyance) {
                //     return 1;
                // } else if (w1.annoyance < w2.annoyance) {
                //     return -1; 
                // } else {
                //     return w1.increment > w2.increment ? 1 : -1;
                // }

                if (w1.annoyance + w1.increment > w2.annoyance + w2.increment ) {
                    return 1;
                } else if (w1.annoyance + w1.increment < w2.annoyance + w2.increment ) {
                    return -1; 
                } else {
                    return w1.increment > w2.increment ? 1 : -1;
                }
            }
        });

        // priority queue for workers 
        while (c-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // initial annoyance
            int d = Integer.parseInt(st.nextToken()); // increment 
            helpQ.add(new Worker(a, d)); 
        }

        // ask for help 
        while (h-- > 0) {
            Worker curr = helpQ.poll();
            curr.askHelp();
            helpQ.add(curr); 
        }

        // get max 
        long maxA = 0; 
        while (!helpQ.isEmpty()) {
            long temp = helpQ.poll().annoyance;
            if (temp > maxA) {
                maxA = temp;
            }
        }

        pw.println(maxA); 

        pw.flush();
        pw.close();
    }
}