import java.io.*;
import java.util.*;

public class coursescheduling {

    static class Together {
        String course;
        int people;

        public Together(String course, int people) {
            this.course = course;
            this.people = people;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        HashMap<String, HashSet<String>> hshMap = new HashMap<>();

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            String newName = st.nextToken() + st.nextToken();
            String course = st.nextToken();
            if (!hshMap.containsKey(course)) {
                hshMap.put(course, new HashSet<>());
            } 
            hshMap.get(course).add(newName);
        }

        PriorityQueue<Together> pq = new PriorityQueue<>(new Comparator<>() {
            public int compare(Together t1, Together t2) {
                return t1.course.compareTo(t2.course);
            }
        });
        for (Map.Entry<String, HashSet<String>> entry : hshMap.entrySet()) {
            pq.add(new Together(entry.getKey(), entry.getValue().size()));
        }

        while (pq.size() != 0) {
            Together temp = pq.poll();
            pw.println(temp.course + " " + temp.people);
        }

        pw.flush();
        pw.close();
    }
}
