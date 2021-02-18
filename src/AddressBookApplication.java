import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class AddressBookApplication {

    static AddressBook ab = new AddressBook();

    public static void main(String args[])throws FileNotFoundException {

        //AddressBook ab = new AddressBook();
        init("C:\\Users\\geova\\Desktop\\New folder\\test.txt");
        //initAddressBookExercise(ab);
        /*Menu menu = new Menu();
        menu.prompt_FirstName();
        menu.prompt_LastName();
        menu.prompt_Street();
        menu.prompt_City();
        menu.prompt_State();
        menu.prompt_Zip();
        menu.prompt_Telephone();
        menu.prompt_Email(); */

    }

    /*static void initAddressBookExercise(AddressBook ab)
    {
        AddressEntry x1 = new AddressEntry();
        AddressEntry x2 = new AddressEntry();
        ab.add(x1);
        ab.add(x2);
        ab.list();
    } */

    static void init(String filename)
    {
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
        ab.add(x);
        }

        ab.list();
    }
}
