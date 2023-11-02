import java.io.*;
import java.util.*;

// to be continued 

public class airports {
    static class Airport {
        int inspection;
        ArrayList<Integer> distance;
        ArrayList<Integer> timeLapse; // available if timeLapse <= timeCount 
        ArrayList<Integer> availFlight; // number of flights available to fly currently

        public Airport(int inspection, int sz) {
            this.inspection = inspection;
            this.distance = new ArrayList<>(sz);
            this.timeLapse = new ArrayList<>(sz);
            this.availFlight = new ArrayList<>(sz);
        }
    }

    static class FlightSchedule {
        int starting; 
        int ending;
        int time;

        public FlightSchedule(int starting, int ending, int time) {
            this.starting = starting;
            this.ending = ending;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Airport> arr = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr.add(new Airport(Integer.parseInt(st.nextToken()), n)); 
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr.get(i).distance.set(j, Integer.parseInt(st.nextToken()));
                arr.get(i).availFlight.set(j, 0);
            }
        }

        PriorityQueue<FlightSchedule> pq = new PriorityQueue<>(new Comparator<FlightSchedule>() {
            public int compare(FlightSchedule f1, FlightSchedule f2) {
                return Integer.compare(f1.time, f2.time);
            }
        });
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1; // starting airport 
            int f = Integer.parseInt(st.nextToken()) - 1; // ending airport 
            int t = Integer.parseInt(st.nextToken()); // time needed 
            pq.add(s, f, t);
        }

        int flightCount = 0;
        int timeCount = 0; 
        
        while (pq.size() != 0) {
            FlightSchedule fs = pq.peek();
            while (pq.size() != 0 && fs.time <= timeCount) {
                fs = pq.poll();
                Airport sap = arr.get(fs.starting);
                if (sap.timeLapse.get(fs.starting) > timeCount || sap.availFlight.get(fs.starting) == 0) { 
                    flightCount++;
                    Airport eap = arr.get(fs.ending);
                    eap.timeLapse.set(fs.starting, sap.distance.get(fs.ending)); // set new time lapse 
                    
                }
            }
            timeCount++;
        }
        

        pw.println(0); 

        pw.flush();
        pw.close();
    }
}
