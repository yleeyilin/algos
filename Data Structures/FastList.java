/*
Linkedlist with o(1) concatenate 
 */
static public class FastList {
    Vertex head;
    Vertex tail;

    public FastList(String item) {
        this.head = new Vertex(item, null);
        this.tail = this.head;
    }

    // add to the back of the list 
    public void add(String val) {
        Vertex temp = new Vertex(val, null);
        this.tail.next = temp;
        this.tail = temp; 
    }
    
    // concatenate methods 
    public void combine(FastList v2) {
        this.tail.next = v2.head;
        this.tail = v2.tail;
    }

    // print method 
    public void printlist(PrintWriter pw) {
        Vertex currNode = this.head;
        while (currNode != null) {
            pw.print(currNode.item);
            currNode = currNode.next;
        }
    }
}

static public class Vertex { 
    String item;
    Vertex next;

    public Vertex(String item, Vertex next) {
        this.item = item;
        this.next = next;
    }
}