import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class File_IO {

    public static Path createDirectoryAndFile(String directoryName, String fileName) throws IOException {
        Path filePath = Paths.get(directoryName, fileName);
        if(!Files.exists(filePath)){
            Files.createFile(filePath);
        }
        return filePath;
    }

    public static void printContacts(Path filePath) throws IOException {
        System.out.println();
        List<String> fileContents = Files.readAllLines(filePath);
        System.out.println("-----------------------------------");
        System.out.printf("%-17s | %-13s%n", "Name", "Number");
        System.out.println("-----------------------------------");
        if (fileContents.size() > 0){
            for (String fileContent : fileContents) {
                System.out.printf("%s |\n", fileContent);
            }
        } else {
            System.out.println("You got no friends!");
        }
    }
    public static void addContact(Path filePath, Contact newContact) throws IOException {
        List<String> fileContents = Files.readAllLines(filePath);
        List<String> tempContents = Files.readAllLines(filePath);
        if((newContact.getPhoneNumber().length() == 10)){
            newContact.setPhoneNumber(insertChar(newContact.getPhoneNumber(),3));
            newContact.setPhoneNumber(insertChar(newContact.getPhoneNumber(),7));
        } else if (newContact.getPhoneNumber().length() == 7){
            newContact.setPhoneNumber(insertChar(newContact.getPhoneNumber(),3));
        }

        String newContactInfo = String.format("%-17s | %-13s", newContact.getName() ,newContact.getPhoneNumber());
        for (String item: fileContents) {
            if (item.equals(newContactInfo)) {
                System.err.println("This contact already exists with a matching name & number");
                return;
            }
            if (item.startsWith(newContact.getName())){
                System.err.println("A contact with this name already exists.");
                System.out.println("Would you like to overwrite existing contact?");
                System.out.println(item);
                if (Input.yesNo()){
                    tempContents.remove(item);
                }
            }
        }
        tempContents.add(newContactInfo);
        System.out.println("Contact successfully created!");
        Files.write(filePath, tempContents);
    }

    private static String insertChar(String str, int position) {
        return str.substring(0, position) + "-" + str.substring(position);
    }



    public static void deleteContact(Path filePath, String contactName) throws IOException{
        List<String> fileContents = Files.readAllLines(filePath);
        int fileContentStartSize = fileContents.size();
        // search for contact
        ArrayList<String> searchResults = File_IO.searchContact(filePath,contactName);
        if(!searchResults.isEmpty()){
            for (int i = 0; i <  searchResults.size(); i++) {
                System.out.println((i+1) +". "+searchResults.get(i));
            }
            //  ask user which contact they want delete
            System.out.println("Select Contact to delete");
            int userInput = Input.getInt(0,searchResults.size()) -1;
            // get string from results
            String deleteContactInfo = searchResults.get(userInput);
            // if the contacts file has the Contact delete the contact
            fileContents.removeIf(item -> item.equals(deleteContactInfo));
            if (fileContents.size() < fileContentStartSize){
                System.err.println("Contact has been deleted");
            }
            Files.write(filePath, fileContents);;
        }

    }

    public static ArrayList searchContact(Path filePath, String contactName) throws IOException{
        List<String> fileContents = Files.readAllLines(filePath);
        ArrayList<String> searchResults = new ArrayList<>();
        for( String item : fileContents){
            if(item.toLowerCase().contains(contactName.toLowerCase())){
                searchResults.add(item);
            }
        }
        if(searchResults.isEmpty()){
            System.err.println("Contact not Found.");
        }
        return searchResults;

    }
}
