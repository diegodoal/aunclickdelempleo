package utils.cv;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Victor on 12/02/2015.
 */
public class PersonalInformation {

    private String name;
    private String surname;
    private String birthDate;
    private String nationality;
    private String address;
    private String email;
    private int phoneNumber;


    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMMM-yyyy");

    public PersonalInformation(String name, String surname, Date birthDate, String address, String email, int phoneNumber){
        this.name = name;
        this.surname = surname;
        this.birthDate = dateFormatter.format(birthDate);
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public PersonalInformation(String name, String surname, Date birthDate, String nationality, String address, String email, int phoneNumber){
        this.name = name;
        this.surname = surname;
        this.birthDate = dateFormatter.format(birthDate);
        this.nationality = nationality;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getNationality() {
        return nationality;
    }
}
