import java.util.*;

class StringInt {
    public String s;
    public Integer i;
    public StringInt(String _s, Integer _i) {
        s = _s;
        i = _i;
    }
}
/*
Implements separate chaining collision resolution technique 
 */
public class hashtable {
    private static final int M = 997;
    private LinkedList<StringInt>[] underlying_table = new LinkedList[M];

    public hashtable() {
        for (int i = 0; i < M ; i++) {
            underlying_table[i] = new LinkedList<StringInt>(); 
        }
    }
    
    private int hash_function(String v) { // assumption 1: v uses ['A'..'Z'] only
        int sum = 0;                // assumption 2: v is a short string
        for (int i = 0; i < v.length(); i++) {
        char c = v.charAt(i); // for each character c in v
        sum = ((sum*26)%M + (c-'A'+1))%M; // M is table size
        }
        return sum;
    }

    public void Insert(String key, int value) { // to emulate mapper[key] = value
        Boolean contains_key = false;
        for (StringInt key_value : underlying_table[hash_function(key)])
            if (key_value.s.equals(key)) { // if there is an existing key
            contains_key = true;
            key_value.i = value; // update the satellite data
            }
        if (!contains_key) // no previous key before
            underlying_table[hash_function(key)].add(new StringInt(key, value)); // just append at the back
    }
    
    public int Search(String key) { // to emulate mapper[key]
        for (StringInt key_value : underlying_table[hash_function(key)]) // O(k), k is the length of this list
            if (key_value.s.equals(key)) // if there is an existing key
            return key_value.i; // return this satellite data
        return -1; // no previous key before, return a symbol to say such key does not exist
    }

    public void Remove(String key) { // to emulate mapper.erase(key)
        LinkedList<StringInt> row = underlying_table[hash_function(key)]; // get the reference of the row
        for (StringInt key_value : underlying_table[hash_function(key)]) // O(k), k is the length of this list
            if (key_value.s.equals(key)) { // if there is an existing key
            row.remove(key_value); // erase this (key, value) pair from this vector
            break; // do not do anything else
            }
        // we do nothing if key is not actually found
    }
    
    public Boolean IsEmpty() {
        int total = 0;
        for (int i = 0; i < M; i++)
            total += underlying_table[i].size();
        return total == 0;
    }
}
