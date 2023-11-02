import java.util.*;

public class BookShelves {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int smallBooks = scanner.nextInt();
        int mediumBooks = scanner.nextInt();
        int bigBooks = scanner.nextInt();
        int shelfWidth = scanner.nextInt();

        int result = minShelves(smallBooks, mediumBooks, bigBooks, shelfWidth);
        System.out.println(result);

        scanner.close();
    }

    public static int minShelves(int smallBooks, int mediumBooks, int bigBooks, int shelfWidth) {
        int shelves = 0;

        // Fill shelves with big books as much as possible
        shelves += bigBooks;

        // Remaining width on the current shelf
        int remainingWidth = shelfWidth - bigBooks * 3;

        // Fit medium books on the current shelf if there's enough space
        if (remainingWidth >= mediumBooks * 2) {
            shelves += 1;
            remainingWidth -= mediumBooks * 2;
        } else {
            shelves += 1;
            remainingWidth = shelfWidth - mediumBooks * 2;
        }

        // Fit small books on the current shelf if there's enough space
        if (remainingWidth >= smallBooks) {
            shelves += 1;
        } else {
            shelves += 1;
        }

        return shelves;
    }
}
