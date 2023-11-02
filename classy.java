import java.io.*;
import java.util.*;

// read the qnd properly

public class classy {
    static class Person implements Comparable<Person> {
        String name;
        ArrayList<Integer> rank;

        public Person(String name, String[] classyness) {
            this.name = name;
            this.rank = new ArrayList<>();
            for (int i = classyness.length - 1; i >= 0; i--) {
                if (classyness[i].equals("upper")) {
                    rank.add(3);
                } else if (classyness[i].equals("middle")) {
                    rank.add(2);
                } else {
                    rank.add(1);
                }
            }
        }

        @Override
        public int compareTo(Person other) {
            Person p = other;
            for (int i = 0; i < Math.max(this.rank.size(), p.rank.size()); i++) {
                int rank1 = (i < this.rank.size()) ? this.rank.get(i) : 2;
                int rank2 = (i < p.rank.size()) ? p.rank.get(i) : 2;
                if (rank1 != rank2) {
                    return Integer.compare(rank2, rank1); // Compare in reverse order
                }
            }
            return this.name.compareTo(p.name);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            List<Person> people = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                name = name.substring(0, name.length() - 1); // Remove the colon
                String[] classiness = st.nextToken().split("-");
                people.add(new Person(name, classiness));
            }

            Collections.sort(people);

            for (Person person : people) {
                pw.println(person.name);
            }

            for (int i = 0; i < 30; i++) {
                pw.print("=");
            }
            pw.println();
        }

        pw.flush();
        pw.close();
    }
}
