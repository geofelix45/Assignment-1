import java.util.HashMap;
import java.util.ArrayList;

public class Node {

    private int count = 0;
    private ArrayList<AddressEntry> duplicate = new ArrayList<AddressEntry>();
    //public Node parent;
    public HashMap<Character,Node> children = new HashMap<Character,Node>();
    public boolean entry = false;

    public void add(AddressEntry ad)

    {
        duplicate.add(ad);
        count++;
    }

    public void showEntry()
    {
        for(int i = 0; i < count; i++)
        {
            System.out.println(duplicate.get(i).toString());
        }
    }

    public void remove(int location)
    {
        duplicate.remove(location);
        count--;
    }

}
