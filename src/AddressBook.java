import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class AddressBook {

    private static AddressBook single_instance = null;

    private HashMap<Character,Node> roots = new HashMap<Character,Node>();
    private Node current = new Node();
    private AddressEntry ad = new AddressEntry();
    private int letter;
    private int addressCount = 0;

    public void readFromFile(String filename)
    {
        int newCount = 0;
        File file = new File(filename);
        Scanner scan = null;

        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            return;
        }

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

    public void remove(String lastName)
    {
        Scanner input = new Scanner(System.in);
        Scanner confirm = new Scanner(System.in);

        if (roots.containsKey(lastName.charAt(0))) {
            if (lastName.length() == 1 && roots.get(lastName.charAt(0)).entry) {
                System.out.println("No Entry Found.");
                return;
            } else {
                if(removeFind(lastName.substring(1), roots.get(lastName.charAt(0))))
                {
                    System.out.println("Which selection would you like to remove?");
                    current.showEntry();
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

    public void list()
    {
        for(int i = 0; i < 26; i++)
        {
            if(roots.containsKey((char)(i + 65)))
            {
                showAll(roots.get((char)(i + 65)));
            }
        }
    }

    public void add(String lastName, AddressEntry ad)
    {
        if (!roots.containsKey(lastName.charAt(0))) {
            roots.put(lastName.charAt(0), new Node());
        }

        this.ad = ad;
        create(lastName.substring(1),roots.get(lastName.charAt(0)));
    }

    private void create(String string, Node node) {

        final Node nextChild;

        if (node.children.containsKey(string.charAt(0))) {
            nextChild = node.children.get(string.charAt(0));
        }
        else {
            nextChild = new Node();
            node.children.put(string.charAt(0), nextChild);
        }

        if (string.length() == 1) {
            nextChild.entry = true;
            addressCount++;
            nextChild.add(ad);
            return;
        } else {
            create(string.substring(1),nextChild);
        }
    }

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

    private void searchFor(String string, Node node) {
        if (string.length()==0) {
            showAll(node);
        }
        else {

            if (node.children.containsKey(string.charAt(0))) {
                searchFor(string.substring(1), node.children.get(string.charAt(0)));
            } else {
                System.out.println("No entry found.");
            }
        }
    }

    public void showAll(Node node)
    {
        if(node.entry)
        {
            node.showEntry();
        }
        for(int i = 0; i < 26; i++)
        {

            letter = i + 97;

            if(node.children.containsKey((char)letter))
            {
                showAll(node.children.get((char)letter));
            }

        }
    }

    private boolean removeFind(String lastName, Node node)
    {
        if(lastName.length() == 0)
        {
            if(node.entry)
            {
                current = node;
                return true;
            }
            else
            {
                return false;
            }
        }
            if (node.children.containsKey(lastName.charAt(0)))
            {
                return removeFind(lastName.substring(1), node.children.get(lastName.charAt(0)));
            }
            else
            {
                return false;
            }
    }

    public static AddressBook getInstance()
    {
        if (single_instance == null)
            single_instance = new AddressBook();

        return single_instance;
    }


}
