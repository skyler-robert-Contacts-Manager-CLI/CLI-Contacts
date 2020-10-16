import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CLI {
    public static void main(String[] args) {
        ArrayList<Contact> contactList = new ArrayList<>();
        String directoryName = "src";
        String fileName = "contacts.txt";

        try{
            Path contactsFilePath = File_IO.createDirectoryAndFile(directoryName, fileName);
            do{
                System.out.println();
                printMenu();
                if(!getMenuInput(contactsFilePath)) break;
            }while (true);
        } catch (IOException e){
            System.err.println("File cannot be created");
            e.printStackTrace();
        }
        System.out.println("Closing Contacts");
    }

    private static final String[] menuItems = {
            "View contacts",
            "Add a new contact",
            "Search a contact by name",
            "Delete an existing contact",
            "Exit",
    };

    public static void printMenu(){
        System.out.println(("What would you like to do?"));
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ". " + menuItems[i]);
        }

    }

    public static boolean getMenuInput(Path filepath) throws IOException{
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");
        // validate user input
        switch (Input.getInt(1,5)){
            case 1:
                File_IO.printContacts(filepath);
                break;
            case 2:
                System.out.println("Enter Name: ");
                String contactName = Input.getString();
                System.out.println("Enter Phone Number: ");
                int contactNumber = Input.getInt();
                File_IO.addContact(filepath,new Contact(contactName,contactNumber));
                break;
            case 3:
                System.out.println("Enter Contact Name: ");
                String searchName = Input.getString();
                ArrayList<String> searchResults = File_IO.searchContact(filepath,searchName);
                if(!searchResults.isEmpty()){
                    for(String item: searchResults){
                        System.out.println(item);
                    }
                }
                break;
            case 4:
                System.out.println("Enter Name: ");
                String deleteName = Input.getString();
                File_IO.deleteContact(filepath,deleteName);
                break;
            case 5:
                return false;
            default:
                break;
        }
        return true;
    }
}
