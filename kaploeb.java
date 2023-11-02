/*
A0259416X
Lee Yi Lin
Lab Group 3 
TA: Ramapriyan
*/ 

/*
 * Summary: 
 * participants must complete k laps 
 * each participant has a unique start number 
 * goal is to compute the final ranking from the individual lap times 
 * 
 * if same time, rank by start number 
 */
import java.io.*;
import java.util.*;
public class kaploeb {
    static String addTime(String time1, String time2) {
        String[] time1Arr = time1.split("\\.");
        String[] time2Arr = time2.split("\\.");
        int sumOfS = Integer.parseInt(time1Arr[1]) + Integer.parseInt(time2Arr[1]);
        int leftover = 0; 
        if (sumOfS > 60) {
            leftover = 1;
            sumOfS -= 60;
        }
        int min = Integer.parseInt(time1Arr[0]) + Integer.parseInt(time2Arr[0]) + leftover;
        return min + "." + sumOfS;
    }
    static class Person {
        int laps;
        String timing;
        long startnum;
        public Person(int laps, String timing, long startnum) {
            this.laps = laps;
            this.timing = timing;
            this.startnum = startnum;
        }
        public boolean completed(int minLaps) {
            return minLaps == this.laps;
        }
        public void add(String val) {
            this.laps++;
            this.timing = addTime(this.timing, val);
        }
    }
    static int compareTiming(Person p1, Person p2) {
            String[] p1Timing = p1.timing.split("\\.");
            String[] p2Timing = p2.timing.split("\\.");
            int minutes1 = Integer.parseInt(p1Timing[0]);
            int seconds1 = Integer.parseInt(p1Timing[1]);
            int minutes2 = Integer.parseInt(p2Timing[0]);
            int seconds2 = Integer.parseInt(p2Timing[1]);
            if (minutes1 < minutes2) {
                return -1;
            } else if (minutes1 == minutes2) {
                if (seconds1 < seconds2) {
                    return -1;
                } else if (seconds1 == seconds2) {
                    return 0;
                }
            }
            return 1;
        }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken()); //no of loops
        int k = Integer.parseInt(st.nextToken()); //no of laps needed to finish the race 
        long s = Integer.parseInt(st.nextToken()); //no of start numbers 
        HashMap<Long, Person> hsh = new HashMap<>();
        while (l-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            long startNum = Integer.parseInt(tokenizer.nextToken());
            String time = tokenizer.nextToken();
            if (hsh.containsKey(startNum)) {
                hsh.get(startNum).add(time);
            } else {
                Person p = new Person(1, time, startNum);
                hsh.put(startNum, p);
            }
        }
        PriorityQueue<Person> pq = new PriorityQueue<>(new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                int timeDiff = compareTiming(p1, p2);
                return timeDiff!=0 ? timeDiff : Long.compare(p1.startnum, p2.startnum);
            }
        });
        for (Person person : hsh.values()) {
            pq.add(person);
        }
        while (pq.size() > 0) {
            Person p = pq.poll();
            if (p.completed(k)) {
                System.out.println(p.startnum);
            }
        }
    }
}
