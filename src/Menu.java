import java.util.Scanner;

public class Menu {

    private AddressBook ab = AddressBook.getInstance();

    public void select()
    {
        boolean quit = false;
        Scanner inputFromUser = new Scanner(System.in);
        String input;

        while(!quit)
        {
            format();
            input = inputFromUser.nextLine();

            if(input.charAt(0) < 123 && input.charAt(0) > 96)
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

    public void read()
    {
        Scanner inputFromUser = new Scanner(System.in);
        System.out.println("Please enter filename.");
        String filename = inputFromUser.nextLine();
        ab.readFromFile(filename);
    }

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

    public void removal()
    {
        Scanner inputFromUser = new Scanner(System.in);

        System.out.println("Enter last name for removal: ");
        ab.remove(inputFromUser.nextLine());
    }

    public void find()
    {
        Scanner inputFromUser = new Scanner(System.in);

        System.out.println("Enter some of all of the individual's last name: ");
        ab.find(inputFromUser.nextLine());
    }

    public void listing()
    {
        ab.list();
    }
}
