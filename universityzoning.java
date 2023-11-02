/*
A0259416X
Lee Yi Lin
Lab Group 3 
TA: Ramapriyan
*/ 
import java.util.*;

public class universityzoning {
    private static long calc_steps(int currRow, int currCol, int facRow, int facCol) {
        return Math.abs(currRow - facRow) + Math.abs(currCol - facCol);
    }
    static class Coordinates {
        private int row;
        private int col;
        public Coordinates(int row, int col) {
            this.row = row;
            this.col = col;
        }
        public int getRows() {
            return this.row;
        }
        public int getCols() {
            return this.col;
        }
        @Override
        public String toString() {
            return "Row: " + this.row + "Col: " + this.col;
        }
    }
    static class StuCoordinates extends Coordinates {
        private int stuID;
        private long dist;
        public StuCoordinates(int id, int row, int col) {
            super(row, col);
            this.stuID = id;
        }
        public void setDist(long dist) {
            this.dist = dist;
        }
        public long getDist() {
            return this.dist;
        }
        public int getStuID() {
            return this.stuID;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        sc.nextInt();
        sc.nextInt();
        int numFaculty = sc.nextInt();
        long numStudents = sc.nextInt();
        int numFacCompliance = sc.nextInt();

        ArrayList<ArrayList<Coordinates>> fac = new ArrayList<>(); // faculty layer then coordinates layer 
        ArrayList<ArrayList<StuCoordinates>> students = new ArrayList<>(); // faculty layer then student layer 

        // get faculty coordinates 
        for (int i = 0; i < numFaculty; i++) {
            int numSets = sc.nextInt();
            ArrayList<Coordinates> facCoords = new ArrayList<>();
            for (int j = 0; j < numSets; j++) {
                int facRow = sc.nextInt();
                int facCol = sc.nextInt();
                facCoords.add(new Coordinates(facRow-1, facCol-1));
            }
            Collections.sort(facCoords, new Comparator<Coordinates>() {
                public int compare(Coordinates a, Coordinates b) {
                    if (a.getRows() == b.getRows()) {
                        return a.getCols() < b.getCols()? -1 : 1;
                    } else {
                        return a.getRows() < b.getRows()? -1 : 1;
                    }
                }
            });
            fac.add(facCoords);
            students.add(new ArrayList<>());
        }

        // get student coordinates
        for (int i = 0; i < numStudents; i++) {
            int stuRow = sc.nextInt();
            int stuCol = sc.nextInt();
            int stuID = sc.nextInt();
            int stuFac = sc.nextInt();
            students.get(stuFac - 1).add(new StuCoordinates(stuID, stuRow-1, stuCol-1));
        } 
        for (ArrayList<StuCoordinates> stuCoords:students) {
            Collections.sort(stuCoords, new Comparator<StuCoordinates>() {
                public int compare(StuCoordinates a, StuCoordinates b) {
                    return a.getStuID() < b.getStuID() ? -1 : 1;
                }
            });
        }

        // get compliance target for each faculty
        int[] required = new int[numFaculty];
        for (int i = 0; i < numFaculty; i++) {
            required[i] = sc.nextInt();
        }

        // student coordinate checker   
        for (int i = 0; i < numFaculty; i++) {
            ArrayList<Coordinates> facCoords = fac.get(i); 
            ArrayList<StuCoordinates> stuCoords = students.get(i); 
            int sizingStu = stuCoords.size();
            for (int j = 0; j < sizingStu; j++) {
                StuCoordinates student = stuCoords.get(j);
                Coordinates faculty = facCoords.get(j);
                int stuRow = student.getRows();
                int stuCol = student.getCols();
                int facRow = faculty.getRows();
                int facCol = faculty.getCols();
                student.setDist(calc_steps(stuRow, stuCol, facRow, facCol));
            }
            Collections.sort(stuCoords, new Comparator<StuCoordinates>() {
                public int compare(StuCoordinates a, StuCoordinates b) {
                    return a.getDist() > b.getDist() ? 1 : -1;
                }
            });
            ArrayList<StuCoordinates> extractedSteps = new ArrayList<>(stuCoords.subList(0, required[i]));
            students.set(i, extractedSteps);
        }
        ArrayList<Long> sums = new ArrayList<>();
        for (ArrayList<StuCoordinates> sublist : students) {
            long sum = sublist.stream().mapToLong(StuCoordinates::getDist).sum();
            sums.add(sum);
        }
        Collections.sort(sums);
        long steps = 0;
        for (int i = 0; i < numFacCompliance; i++) {
            steps += sums.get(i);
        }
        System.out.println(steps);
    }
}
