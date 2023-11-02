/*
A0259416X
Lee Yi Lin
Lab Group 3 
TA: Ramapriyan
*/ 
import java.util.*;

public class janeeyre {
    public static class Book {
        String name;
        int pages;
        int time;
        int size;

        public Book(String name, int pages) {
            this.name = name;
            this.pages = pages;
            this.time = 0;
        }

        public Book(String name, int pages, int time) {
            this.name = name;
            this.pages = pages;
            this.time = time;
        }
    }
    public static String[] extract(String lne) {
        String[] temp = lne.split("\"");
        temp[2] = temp[2].trim();
        return Arrays.copyOfRange(temp, 1, temp.length);
    }
    public static String[] extractV2(String lne) {
        String[] temp = lne.split("\"");
        temp[2] = temp[2].trim();
        temp[0] = temp[0].trim();
        return temp;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of unread books 
        int m = sc.nextInt(); // num of books given to her 
        int k = sc.nextInt(); // num pages in jane eyre
        PriorityQueue<Book> arr = new PriorityQueue<>(new Comparator<Book>() {
            public int compare(Book a, Book b) {
                return a.name.compareTo(b.name); // compare by name
            }
        });
        PriorityQueue<Book> polled = new PriorityQueue<>(new Comparator<Book>() {
            public int compare(Book a, Book b) {
                return a.time > b.time ? 1 : -1;
            }
        });
        // ArrayList<Book> polled = new ArrayList<>();
        arr.add(new Book ("Jane Eyre", k));
        sc.nextLine();
        for (int i = 0; i < n; i++) { 
            String[] str = extract(sc.nextLine());
            arr.add(new Book(str[0], Integer.parseInt(str[1]))); // name, page
        }
        for (int i = 0; i < m; i++) {
            String[] str = extractV2(sc.nextLine());
            arr.add(new Book(str[1], Integer.parseInt(str[2]), Integer.parseInt(str[0]))); // name, page, time
        }
        long time_lapse = 0; 
        Book book = arr.peek();
        Boolean completed = false;
        while (!completed) {
            book = arr.poll();
            int rnge = polled.size();
            if (rnge > 0) {
                for (int i = 0; i < rnge; i++) {
                    Book timeLagBook = polled.peek(); 
                    if (timeLagBook.time <= time_lapse) {
                        time_lapse += timeLagBook.pages;
                        polled.poll();
                    } else {
                        break;
                    }
                }
            } 
            if (book.name.equals("Jane Eyre")) {
                time_lapse += book.pages;
                completed = true;
                System.out.println(time_lapse);
                break;
            }
            long time = book.time - time_lapse;
            if (time <= 0) {
                time_lapse += book.pages;
            } else {
                polled.add(book);
            }
        }
    }
}


// import java.util.*;

// public class janeeyre {
//     public static String[] extract(String lne) {
//         String[] temp = lne.split("\"");
//         temp[2] = temp[2].trim();
//         return Arrays.copyOfRange(temp, 1, temp.length);
//     }
//     public static String[] extractV2(String lne) {
//         String[] temp = lne.split("\"");
//         temp[2] = temp[2].trim();
//         temp[0] = temp[0].trim();
//         return temp;
//     }
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt(); // number of unread books 
//         int m = sc.nextInt(); // num of books given to her 
//         int k = sc.nextInt(); // num pages in jane eyre
//         ArrayList<ArrayList<String>> arr = new ArrayList<>();
//         ArrayList<String> tempLst = new ArrayList<>();
//         tempLst.add("Jane Eyre");
//         tempLst.add("" + k);
//         arr.add(tempLst);
//         sc.nextLine();
//         for (int i = 0; i < n; i++) {
//             String[] str = extract(sc.nextLine());
//             ArrayList<String> innerList = new ArrayList<>();
//             innerList.add(str[0]); //name 
//             innerList.add(str[1]); //pages 
//             arr.add(innerList);
//         }
//         for (int i = 0; i < m; i++) {
//             String[] str = extractV2(sc.nextLine());
//             ArrayList<String> innerList = new ArrayList<>();
//             innerList.add(str[1]); // name
//             innerList.add(str[2]); // pages 
//             innerList.add(str[0]); // time 
//             arr.add(innerList);
//         }
//         Collections.sort(arr, new Comparator<ArrayList<String>>() {
//             public int compare(ArrayList<String> a, ArrayList<String> b) {
//                 return a.get(0).compareTo(b.get(0)); // compare by name 
//             }
//         });
//         int pointer = 0; 
//         long time_lapse = 0; 
//         boolean isCompleted = false;
//         while (!isCompleted) {
//             ArrayList<String> inner = arr.get(pointer);
//             if (inner.size() == 3) {
//                 long time = Long.parseLong(inner.get(2)) - time_lapse;
//                 if (time > 0) {
//                     pointer++;
//                 } else if (inner.get(1).equals("0")) {
//                     pointer++;
//                 } else {
//                     time_lapse += Long.parseLong(inner.get(1));
//                     inner.set(1, "" + 0);
//                     pointer = 0; 
//                 }
//             } else if (inner.size() == 2) {
//                 if (inner.get(0).equals("Jane Eyre")) {
//                     time_lapse += Long.parseLong(inner.get(1));
//                     isCompleted = true;
//                     System.out.println(time_lapse);
//                     break;
//                 } else if (inner.get(1).equals("0")) {
//                     pointer++;
//                 } else {  
//                     time_lapse += Long.parseLong(inner.get(1));
//                     inner.set(1, "" + 0);
//                     pointer = 0; 
//                 }
//             }
//         }
//     }
// }
