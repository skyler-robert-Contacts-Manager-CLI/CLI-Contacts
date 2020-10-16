import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;


public class CLI {
    public static void main(String[] args) {
        ArrayList<Contact> contactList = new ArrayList<>();
//        contactList.add(new Contact("Robert", 1231231234));
//        contactList.add(new Contact("Skyler", 1231231234));

        String directoryName = "src";
        String fileName = "contacts.txt";

        try{
            Path contactsFilePath = File_IO.createDirectoryAndFile(directoryName, fileName);


            File_IO.printContacts(contactsFilePath);
            //Our addMethod allows new contact to be added, but denies if contact already exists
            //File_IO.addContact(contactsFilePath, new Contact("Douglas", 1231231234));

            //Our deleteMethod allows existing contact to be deleted, but denies if contact does not exist
            //File_IO.deleteContact(contactsFilePath, new Contact("Douglas", 1231231234));

            File_IO.printContacts(contactsFilePath);

        } catch (IOException e){
            System.err.println("File cannot be created");
            e.printStackTrace();
        }

    }
}
