/**
 * AddressEntry object holds data such as first name, last name, street, city, state, zip, phone, and email.
 * Uses getters and setters to modify or send information from the object
 *
 * @author Geovany Felix
 * @since February 2021
 */
public class AddressEntry {

    /**
     * class variables
     */
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String state;
    private int zip;
    private String phone;
    private String email;

    /**
     * 0 arg instance of AddressEntry
     */
    public AddressEntry()
    {
        firstName = " ";
        lastName = " ";
        street = " ";
        city = " ";
        state = " ";
        zip = 00000;
        phone = " ";
        email = " ";
    }

    /**
     * multi arg instance of AddressEntry
     *
     * @param firstName first name of entry
     * @param lastName last name of entry
     * @param street street of entry
     * @param city city of entry
     * @param state state of entry
     * @param zip zip of entry
     * @param phone phone of entry
     * @param email email of entry
     */
    public AddressEntry(String firstName, String lastName, String street, String city, String state, int zip, String phone, String email)
    {
        /**
         * sets the entry data from the constructors
         */
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;

    }

    /**
     * formats the data from AddressEntry in a neat manner
     *
     * @return a neat String that displays the data from AddressEntry
     */
    public String toString()
    {
        return (firstName + " " + lastName + "\n" + street + ", " + city + ", " + state + " " + zip + "\n" + phone + " " + email + "\n");
    }

    /**
     *
     * @return first name of entry
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     *
     * @param firstName used to set entry first name
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     *
     * @return last name of entry
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     *
     * @param lastName used to set last name of entry
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     *
     * @return street of entry
     */
    public String getStreet()
    {
        return street;
    }

    /**
     *
     * @param street used to set street of entry
     */
    public void setStreet(String street)
    {
        this.street = street;
    }

    /**
     *
     * @return city of entry
     */
    public String getCity()
    {
        return city;
    }

    /**
     *
     * @param city used to set city of entry
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     *
     * @return state of entry
     */
    public String getState()
    {
        return state;
    }

    /**
     *
     * @param state used to set state of entry
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     *
     * @return zip of entry
     */
    public int zip()
    {
        return zip;
    }

    /**
     *
     * @param zip used to set zip of entry
     */
    public void setZip(int zip)
    {
        this.zip = zip;
    }

    /**
     *
     * @return phone of entry
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     *
     * @param phone used to set phone of entry
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     *
     * @return email of entry
     */
    public String getEmail()
    {
        return email;
    }

    /**
     *
     * @param email used to set email of entry
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
}
