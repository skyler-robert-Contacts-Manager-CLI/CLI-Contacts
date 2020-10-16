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


    public static void deleteContact(Path filePath, Contact contactToDelete) throws IOException{
        List<String> fileContents = Files.readAllLines(filePath);
        int fileContentStartSize = fileContents.size();
        String deleteContactInfo = contactToDelete.getName() + " | " + contactToDelete.getPhoneNumber();
        fileContents.removeIf(item -> item.equals(deleteContactInfo));
        if (fileContents.size() < fileContentStartSize){
            System.err.println("Contact has been deleted");
        } else {
            System.err.println("Unable to find contact to delete");
        }
        Files.write(filePath, fileContents);
}

public void searchContact(ArrayList contactList){

}

public void showAllContacts(ArrayList contactList){

}

}
