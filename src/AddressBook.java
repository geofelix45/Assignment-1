import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 * Creates a data structure to house AddressEntry objects in the form of a trie tree utilizing HashMap and
 * the Node class. Contains methods to add, remove, find, read, or list depending on interactions between
 * this class and the menu class
 *
 * @author Geovany Felix
 * @since February 2021
 */

public class AddressBook {

    /**
     * singleton declaration
     */
    private static AddressBook single_instance = null;

    /**initialize roots as the top of the tree*/
    private HashMap<Character,Node> roots = new HashMap<Character,Node>();
    /** current is used for carrying a node to another method when a return statement cannot be used
     * (recursive methods)*/
    private Node current = new Node();
    /** instance of an AddressEntry object, used for adding new entries */
    private AddressEntry ad = new AddressEntry();
    /** used as a "counter" when parsing through the alphabetical values from the tree (a-z, 0-25) */
    private int letter;
    /** tallies total entries in AddressBook */
    private int addressCount = 0;

    /**
     * takes a filename and adds the entries to the book by parsing through the file data
     *
     * @param filename used to find .txt file to read from
     */
    public void readFromFile(String filename)
    {
        /** tallies the amount of new entries from the file */
        int newCount = 0;
        File file = new File(filename);
        Scanner scan = null;

        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            return;
        }

        /** adds data from file into new AddressEntry, x, and adds x to AddressBook */
        while (scan.hasNextLine()) {

            AddressEntry x = new AddressEntry();
            x.setFirstName(scan.nextLine());
            x.setLastName(scan.nextLine());
            x.setStreet(scan.nextLine());
            x.setCity(scan.nextLine());
            x.setState(scan.nextLine());
            x.setZip(Integer.parseInt(scan.nextLine()));
            x.setPhone(scan.nextLine());
            x.setEmail(scan.nextLine());
            add(x.getLastName(), x);
            newCount++;
        }

        System.out.println("Read in " + newCount + " new Addresses, successfully loaded, currently " + addressCount + " Addresses.");


    }

    /**
     * remove() is used to interface with the user and ask, if any entry is found, whether or not they
     * would like to delete said entry. Entry is found using recursive method from removeFind()
     * and utilized the remove() method from AddressEntry to delete the item
     *
     * @param lastName String used to find and remove entry
     */
    public void remove(String lastName)
    {
        Scanner input = new Scanner(System.in);
        Scanner confirm = new Scanner(System.in);

        /** Checks if String is longer than 1 character and if entry is found with single letter */
        if (roots.containsKey(lastName.charAt(0))) {
            if (lastName.length() == 1 && roots.get(lastName.charAt(0)).entry) {
                System.out.println("No Entry Found.");
                return;
            } else {
                if(removeFind(lastName.substring(1), roots.get(lastName.charAt(0)))) /** removeFind() is boolean to confirm that entry exists */
                {
                    System.out.println("Which selection would you like to remove?");
                    current.showEntry(); /** current holds the node that has the entry to be deleted */
                    String selection = input.nextLine();

                    System.out.println("enter 'y' to confirm delete or 'n' to return.");
                    String confirmation = confirm.nextLine();

                    if(confirmation.charAt(0) == 'y')
                    {
                        current.remove(Integer.valueOf(selection) - 1);
                        addressCount--;
                    }
                    else
                    {
                        return;
                    }

                }
            }
        }
    }

    /**
     * Goes through the tree to list any entries. Uses the showAll method if the corresponding letter
     * from the root contains a word. showAll also utilizes the showEntry method from Node to print out
     * the entries found in the list.
     */
    public void list()
    {
        /** parses through each letter in the root */
        for(int i = 0; i < 26; i++)
        {
            if(roots.containsKey((char)(i + 65)))
            {
                showAll(roots.get((char)(i + 65)));
            }
        }
    }

    /**
     * add() checks if root requires a new Node before using the create method to find the path
     * for the new AddressEntry
     *
     * @param lastName String used to search for position in tree to place AddressEntry
     * @param ad AddressEntry to be stored
     */
    public void add(String lastName, AddressEntry ad)
    {
        if (!roots.containsKey(lastName.charAt(0))) {
            roots.put(lastName.charAt(0), new Node());
        }

        this.ad = ad; /** class variable ad gets the AddressEntry that was passed */
        create(lastName.substring(1),roots.get(lastName.charAt(0)));
    }

    /**
     * create() is a recursive method used to pave a path in the tree for the entry
     *
     * @param string contains the substring of a last name to create a path for the entry
     * @param node holds the current node of the path being made for the entry
     */
    private void create(String string, Node node) {

        /** used to get the child node if a path exists, or create a new node*/
        final Node nextChild;

        if (node.child.containsKey(string.charAt(0))) {
            nextChild = node.child.get(string.charAt(0));
        }
        else {
            nextChild = new Node();
            node.child.put(string.charAt(0), nextChild);
        }/** entry has found its spot in the tree, boolean entry is set to true to signify the node
         * contains an entry, addressCount is updated.
         */
        if (string.length() == 1) {
            nextChild.entry = true;
            addressCount++;
            nextChild.add(ad);
            return;
        } else {
            create(string.substring(1),nextChild); /** recursive call */
        }
    }

    /**
     * find ensured the last name is valid before passing the substring to searchFor() to find the entry
     * recursively
     *
     * @param partOfLastName String that holds the beginning of or all of the last name to be found
     */
    public void find(String partOfLastName) {
        if (roots.containsKey(partOfLastName.charAt(0))) {
            if (partOfLastName.length() == 1 && roots.get(partOfLastName.charAt(0)).entry) {
                System.out.println("No Entry Found.");
                return;
            } else {
                searchFor(partOfLastName.substring(1), roots.get(partOfLastName.charAt(0)));
                return;
            }
        }
    }

    /**
     * searchFor() is a recursive method that uses a parsed substring and the current node, updated
     * during each call, to find the final destination of the path before calling the showAll method to
     * find any entries that contain the string provided by the user
     *
     * @param string the substring of the file path
     * @param node the current Node of the tree
     */
    private void searchFor(String string, Node node) {
        if (string.length()==0) {
            showAll(node); /** end of string and path */
        }
        else {

            if (node.child.containsKey(string.charAt(0))) {
                searchFor(string.substring(1), node.child.get(string.charAt(0))); /** recursive call */
            } else {
                System.out.println("No entry found.");
            }
        }
    }

    /**
     * showAll() takes a node from a path in the tree, then recursively prints all child Nodes and
     * child Nodes of those Nodes and so forth. showAll() uses the showEntry() method from Node to
     * print all the entries found in the selected Node
     *
     * @param node last Node that was provided in path
     */
    public void showAll(Node node)
    {
        if(node.entry) /** conditional that signifies the current Node has one/many entries */
        {
            node.showEntry();
        }
        for(int i = 0; i < 26; i++) /** goes through each letter */
        {

            letter = i + 97; /** accounts for ascii value */

            if(node.child.containsKey((char)letter))
            {
                showAll(node.child.get((char)letter)); /** recursive call */
            }

        }
    }

    /**
     * removeFind is separate than Find in that it requires an exact match to the
     * lastName provided. Furthermore it returns a boolean value to determine whether or not
     * a valid entry was found
     *
     * @param lastName lastName of entry to be removed
     * @param node current Node provided from root
     * @return returns true if the entry exists, false otherwise
     */
    private boolean removeFind(String lastName, Node node)
    {
        if(lastName.length() == 0)
        {
            if(node.entry)
            {
                current = node; /** if the entry is found, current gets node so it can be deleted in remove() */
                return true;
            }
            else
            {
                return false; /** false if final destination holds no entry */
            }
        }
            if (node.child.containsKey(lastName.charAt(0)))
            {
                return removeFind(lastName.substring(1), node.child.get(lastName.charAt(0))); /** recursive call */
            }
            else
            {
                return false;
            }
    }

    /**
     * "singleton" method
     * @return single_instance
     */
    public static AddressBook getInstance()
    {
        if (single_instance == null)
            single_instance = new AddressBook();

        return single_instance;
    }


}
