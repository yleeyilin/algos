import java.io.*;
import java.util.*;
/*
Take the largest energy quest from the current pool of quest which is smaller or equal to X

 */
public class kattissquest {
    public static class Quest {
        int energy;
        int gold;
        int id;

        public Quest(int energy, int gold, int id) {
            this.energy = energy; 
            this.gold = gold;
            this.id = id;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        TreeMap<Integer, PriorityQueue<Integer>> pool = new TreeMap<>();

        // TreeSet<Quest> treeSet = new TreeSet<>(new Comparator<Quest>() { // delete
        //     public int compare(Quest q1, Quest q2) {
        //         if (q1.energy < q2.energy) {
        //             return -1;
        //         } else if (q1.energy > q2.energy) {
        //             return 1;
        //         } else {
        //             return q1.gold > q2.gold ? 1 : q1.gold == q2.gold? Integer.compare(q1.id, q2.id) : -1;
        //         }
        //     }
        // }); // does not allow fr duplicates with same energy

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("add")) {
                int E = Integer.parseInt(st.nextToken());
                int G = Integer.parseInt(st.nextToken());

                if (pool.containsKey(E)) {
                    pool.get(E).add(G);
                } else {
                    PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
                        public int compare(Integer i1, Integer i2) {
                            return Integer.compare(i2, i1);
                        }
                    });
                    pq.add(G);
                    pool.put(E, pq);
                }
            } else if (command.equals("query")) {
                int X = Integer.parseInt(st.nextToken());
                long goldReward = 0;
                while (X > 0) {
                    Map.Entry<Integer, PriorityQueue<Integer>> pos = pool.lowerEntry(X+1); // find largest energy quest from current pool
                    if (pos == null) {
                        break;
                    }
                    int thisEnergy = pos.getKey();
                    X -= thisEnergy;
                    int thisGold = pos.getValue().poll(); // takes out the smallest one first based on comparator
                    goldReward += thisGold;
                    if (pos.getValue().isEmpty()) {
                        pool.remove(thisEnergy);
                    }
                }
                pw.println(goldReward);
            }
        }

        pw.flush();
        pw.close();
    }
}