import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class File_IO {

    public static Path createDirectoryAndFile(String directoryName, String fileName) throws IOException {
        Path directoryPath = Paths.get(directoryName);
        Path filePath = Paths.get(directoryName, fileName);
        if(!Files.exists(filePath)){
            Files.createFile(filePath);
        }
        return filePath;
    }

    public static void printContacts(Path filePath) throws IOException {
        System.out.println();
        List<String> fileContents = Files.readAllLines(filePath);
        if (fileContents.size() > 0){
            for (int i = 0; i < fileContents.size(); i++) {
                System.out.printf("%d: %s\n", i + 1, fileContents.get(i));
            }
        } else {
            System.out.println("You got no friends!");
        }
    }
    public static void addContact(Path filePath, Contact newContact) throws IOException {
        List<String> fileContents = Files.readAllLines(filePath);
        String newContactInfo = newContact.getName() + " | " + newContact.getPhoneNumber();

        for (String item: fileContents) {
            if (item.equals(newContactInfo)) {
                System.err.println("This contact already exists");
                return;
            }
        }

        fileContents.add(newContactInfo);
        Files.write(filePath, fileContents);
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

public void showAllContacts(ArrayList contactList){

}

}
