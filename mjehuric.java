import java.io.*;

public class mjehuric {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tempArr = br.readLine().split(" ");
        int[] arr = new int[tempArr.length];
        for (int i = 0; i < tempArr.length; i++) {
            arr[i] = Integer.parseInt(tempArr[i]);
        }
        int swapLast = arr.length;
        while (swapLast != 0) {
            for (int i = 0; i < swapLast-1; i++) {
                int first = arr[i];
                int second = arr[i+1];
                if (first > second) {
                    arr[i] = second;
                    arr[i+1] = first;
                    StringBuilder res = new StringBuilder();
                    for (int t:arr) {
                        res.append(t).append(" ");
                    }
                    System.out.println(res.toString());
                }  
            }
            swapLast--;
        }
    }
}
