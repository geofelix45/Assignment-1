import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private static List<AddressEntry> AddressEntryList = new ArrayList<AddressEntry>();

    public static void list()
    {
        for(int i = 0; i < AddressEntryList.size(); i++) {

            System.out.println((AddressEntryList.get(i)).toString());
        }
    }

    public static void add(AddressEntry ae)
    {
        AddressEntryList.add(ae);
    }
}
