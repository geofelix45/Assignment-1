import java.util.Scanner;

/**
 * Provides options to the program gui and interacts with the user to manipulate the AddressBook
 *
 * @author Geovany Felix
 * @since February 2021
 */
public class Menu {

    /** singleton instance of AddressBook */
    private AddressBook ab = AddressBook.getInstance();

    /**
     * select() acts as the selection screen for the user. This method enters an infinite loop that is only escaped
     * if the user selects the "quit" option from the menu. A switch statement is used to identify the user's input
     */
    public void select()
    {
        boolean quit = false;
        Scanner inputFromUser = new Scanner(System.in);
        String input;

        while(!quit)
        {
            format(); /** format() displays the user options */
            input = inputFromUser.nextLine();

            if(input.charAt(0) < 123 && input.charAt(0) > 96) /** uses ascii value to validate user input */
            {
                switch (input)
                {
                    case "a":
                        read();
                        break;
                    case "b":
                        addition();
                        break;
                    case "c":
                        removal();
                        break;
                    case "d":
                        find();
                        break;
                    case "e":
                        listing();
                        break;
                    case "f":
                        System.out.println("Bye Bye!");
                        quit = true;
                        break;
                }
            }
            else {
                System.out.println("\nInvalid Entry.\n");
            }
        }
    }

    /**
     * prints out the menu options to the user
     */
    private void format()
    {
        System.out.println("********************");
        System.out.println("Please enter in your menu selection.");
        System.out.println("a) Loading From File");
        System.out.println("b) Addition");
        System.out.println("c) Removal");
        System.out.println("d) Find");
        System.out.println("e) Listing\n");
        System.out.println("f) Quit");
        System.out.println("********************");
    }

    /**
     * prompts the user for a file name to provide to the addressbook
     */
    public void read()
    {
        Scanner inputFromUser = new Scanner(System.in);
        System.out.println("Please enter filename.");
        String filename = inputFromUser.nextLine();
        ab.readFromFile(filename);
    }

    /**
     * asks the user for input to update the first name, last name, street, city, state, zip, phone, and
     * email of an address entry before adding it to the address book
     */
    public void addition()
    {
        AddressEntry x = new AddressEntry();
        Scanner inputFromUser = new Scanner(System.in);

        System.out.println("First Name: ");
        x.setFirstName(inputFromUser.nextLine());
        System.out.println("Last Name: ");
        x.setLastName(inputFromUser.nextLine());
        System.out.println("Street: ");
        x.setStreet(inputFromUser.nextLine());
        System.out.println("City: ");
        x.setCity(inputFromUser.nextLine());
        System.out.println("State: ");
        x.setState(inputFromUser.nextLine());
        System.out.println("Zip: ");
        x.setZip(Integer.valueOf(inputFromUser.nextLine()));
        System.out.println("Phone: ");
        x.setPhone(inputFromUser.nextLine());
        System.out.println("Email: ");
        x.setEmail(inputFromUser.nextLine());

        ab.add(x.getLastName(), x);

    }

    /**
     * prompts the user for the last name of the entry they want to remove before sending the String to
     * AddressBook
     */
    public void removal()
    {
        Scanner inputFromUser = new Scanner(System.in);

        System.out.println("Enter last name for removal: ");
        ab.remove(inputFromUser.nextLine());
    }

    /**
     * prompts the user for a String to provide to AddressBook to find a path for entries
     */
    public void find()
    {
        Scanner inputFromUser = new Scanner(System.in);

        System.out.println("Enter some of all of the individual's last name: ");
        ab.find(inputFromUser.nextLine());
    }

    /**
     * calls the list method from AddressBook
     */
    public void listing()
    {
        ab.list();
    }
}
