import java.io.*;

public class rankproblem {

    private static int getIndex(String val, String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(val)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int numTeams = Integer.parseInt(firstLine[0]);
        int numMatch = Integer.parseInt(firstLine[1]);
        String[] team = new String[numTeams];
        for (int i = 1; i < numTeams+1; i++) {
            team[i-1] = "T" + i;
        }
        for (int i = 0; i < numMatch; i++) {
            String[] pairTeam = br.readLine().split(" ");
            int winner = getIndex(pairTeam[0], team);
            int loser = getIndex(pairTeam[1], team);
            if (loser < winner) {
                String temp = pairTeam[1];
                for (int j = loser; j < winner; j++) {
                    team[j] = team[j+1];
                }
                team[winner] = temp;
            }   
        }
        StringBuilder res = new StringBuilder();
        for (String t:team) {
            res.append(t).append(" ");
        }
        System.out.println(res.toString());
    }
}
