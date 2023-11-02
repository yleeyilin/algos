import java.io.*;
import java.util.*;

public class hermits {
    public static class Street {
        int index;
        int people; 
        int cross;

        public Street(int index, int people) {
            this.index = index;
            this.people = people;
            this.cross = people;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // number of streets in the district 

        st = new StringTokenizer(br.readLine());
        ArrayList<Street> arr = new ArrayList<>(); 
        for (int i = 0 ; i < N ; i++) {
            arr.add(new Street(i, Integer.parseInt(st.nextToken()))); 
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); 
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            arr.get(a).cross += arr.get(b).people;
            arr.get(b).cross += arr.get(a).people;;
        }

        Collections.sort(arr, new Comparator<Street>() {
            public int compare(Street s1, Street s2) {
                if (s1.cross < s2.cross) {
                    return -1;
                } else if (s1.cross > s2.cross) {
                    return 1;
                } 
                return s1.index < s2.index ? -1 : 1;
            }
        });

        pw.println(++arr.get(0).index); 

        pw.flush();
        pw.close();
    }
}
