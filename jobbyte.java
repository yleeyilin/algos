/*
A0259416X
Lee Yi Lin
Lab Group 3 
TA: Ramapriyan
*/ 
import java.io.*;
import java.util.Arrays;

public class jobbyte {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numFriends = Integer.parseInt(br.readLine());
        String[] jobsStr = br.readLine().split(" ");
        int[] jobs = new int[numFriends];
        for (int i = 0; i < numFriends; i++) {
            jobs[i] = Integer.parseInt(jobsStr[i]);
        }
        int count = 0;
        for (int i = 0; i < numFriends; i++) {
            while (jobs[i] != i+1) {
                int newIndex = jobs[i] - 1;
                int temp = jobs[i];
                jobs[i] = jobs[newIndex];
                jobs[newIndex] = temp;
                System.out.println(Arrays.toString(jobs));
                count++;
            }
        }       
        System.out.println(count);
    }
}