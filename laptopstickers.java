import java.io.*;

public class laptopstickers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tempArr = br.readLine().split(" ");
        int length = Integer.parseInt(tempArr[0]);
        int height = Integer.parseInt(tempArr[1]);
        char[][] laptop = new char[height][length];
        int numStickers = Integer.parseInt(tempArr[2]);
        char[] alphabet = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                laptop[i][j] = '_';
            }
        }
        for (int i = 0; i < numStickers; i++) {
            String[] arrLine = br.readLine().split(" ");
            int stickerLength = Integer.parseInt(arrLine[0]);
            int stickerHeight = Integer.parseInt(arrLine[1]);
            int stickerCol = Integer.parseInt(arrLine[2]);
            int stickerRow = Integer.parseInt(arrLine[3]);
            for (int row = stickerRow; row<Math.min(stickerRow+stickerHeight, height); row++) {
                for (int col = stickerCol; col<Math.min(stickerCol+stickerLength, length); col++) {
                    laptop[row][col] = alphabet[i];
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(laptop[i][j]);
            }
            System.out.println(); 
        }
    }
}
