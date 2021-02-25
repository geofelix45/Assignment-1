import java.util.HashMap;
import java.util.ArrayList;

/**
 * Creates a Node object which holds a flag to show if the Node carries an entry, said entry, a list
 * for multiple entries of the same last name, and a subsequent child node
 *
 * @author Geovany Felix
 * @since February 2021
 */
public class Node {

    private int count = 0; /** counter that tallies how many entries exist in the Node */
    private ArrayList<AddressEntry> entries = new ArrayList<AddressEntry>(); /** list that holds the entries */
    public HashMap<Character,Node> child = new HashMap<Character,Node>(); /** child Node */
    public boolean entry = false; /** entry flag */

    /**
     *
     * @param ad AddressEntry to be added to entries list
     */
    public void add(AddressEntry ad)

    {
        entries.add(ad);
        count++;
    }

    /**
     * prints out all entries in the list
     */
    public void showEntry()
    {
        for(int i = 0; i < count; i++)
        {
            System.out.println(entries.get(i).toString()); /** uses the toString method from AddressEntry to format */
        }
    }

    /**
     * remove is called from AddressBook to delete an item from the entries list
     *
     * @param location the location of the entry in the list to be deleted
     */
    public void remove(int location)
    {
        entries.remove(location);
        count--;
    }

}
