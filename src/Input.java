import java.util.Scanner;

public class Input {
    private static Scanner scanner;

    public static String getString(){
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static boolean yesNo(){
        scanner = new Scanner(System.in);
        System.out.println(ANSI_YELLOW+"[Y/n]"+ANSI_RESET);
        String userResponse = scanner.nextLine();
        if (userResponse.trim().toLowerCase().startsWith("y")){
            return true;
        }
        else if(userResponse.trim().toLowerCase().startsWith("n")){
            return false;
        }
        System.err.println("Enter a correct value: ");
        return yesNo();
    }

    public static int getInt(){
        try{
            return Integer.parseInt(getString());
        }catch (Exception ex){
            System.err.println("Enter a correct value: ");
            return getInt();
        }
    }

    public static int getInt(int min, int max){
        int userNumber = getInt();
        if (userNumber >= min && userNumber <= max){
            return userNumber;
        }else{
            System.err.printf("Enter a number between %s and %s: ", min, max);
            return getInt(min, max);
        }
    }
    public static double getDouble(){
        try{
            return Double.parseDouble(getString());
        }catch (Exception ex){
            System.err.println("Enter a correct value: ");
            return getDouble();
        }
    }

    public static double getDouble(double min, double max){
        double userNumber = getDouble();
        if (userNumber >= min && userNumber <= max){
            return userNumber;
        }else{
            System.err.printf("Enter a float number between %s and %s: ", min, max);
            return getDouble(min, max);
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\033[0;33m";

}