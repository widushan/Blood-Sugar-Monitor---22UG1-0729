import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;


public class Tester {
    private final ArrayList<BloodSugar> records = new ArrayList<>();
    
    public static void main(String[] args) {
        Tester bloodSugarApp = new Tester();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            bloodSugarApp.displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> bloodSugarApp.create();
                case 2 -> bloodSugarApp.index();
                case 3 -> {
                    System.out.print("Enter user id: ");
                    String id = scanner.nextLine();
                    bloodSugarApp.view(id);
                }
                case 4 -> bloodSugarApp.delete();
                case 5 -> bloodSugarApp.exit();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("");
        System.out.println("---------Blood Sugar Monitor-----------------");
        System.out.println("1. Create a record");
        System.out.println("2. Show blood sugar data for all users");
        System.out.println("3. Show blood sugar data for a selected user");
        System.out.println("4. Delete all records");
        System.out.println("5. Exit application");
        System.out.println("Enter your choice: ");
    }

    public void index() {
        for (BloodSugar record : records) {
            record.display();
        }
        if (records.isEmpty()){
            System.out.println("Record List is empty");
            System.out.println("Please enter Details & Try Again!");
         }
        
        System.out.println("");
    }

    public void view(String id) {
        boolean recordFound = false; // Flag to track if the record is found
        for (BloodSugar record : records) {
            if (record.getId().equals(id)) { // Check if the current record's ID matches the specified ID
                recordFound = true; // Mark that the record is found
                record.display(); // Display the record details
                break; // Exit the method once the record is found
            }
        }

        // If the loop completes without finding the record, print a message
        if (!recordFound) {
            System.out.println("Record not found for ID: " + id);
            System.out.println(""); // Print an empty line for spacing
        }
    }









    public void create() {
        
        // Check if the limit has been reached
        if (records.size() >= 5) {
            System.out.println("Cannot create more records. Limit reached.");
            return;
        }
        
        // Input user id
        System.out.print("Enter user id: ");
        String id = scanner.nextLine(); // Read user id as String

        // Validate user id
        if (!id.matches("[a-zA-Z0-9]+")) {
            System.out.println("Invalid user id. User id must contain only alphanumeric characters.");
            return; // Exit method if user id is invalid
        }

        // Consume the newline character after reading the user id
        

        // Input name
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty. Record creation failed.");
            return; // Exit method if name is empty
        }
        if (!name.matches("[a-zA-Z ]+")) {
        System.out.println("Invalid name. Name must contain only alphabetic characters.");
        return; // Exit method if name is invalid
        }

        // Input year of birth
        System.out.print("Enter year of birth: ");
        String yobInput = scanner.nextLine();
        int yob;
        try {
            yob = Integer.parseInt(yobInput);
        } catch (NumberFormatException e) {
            System.out.println("Year of birth must be a number. Record creation failed.");
            return; // Exit method if yob is not a number
        }

        // Input blood sugar level
        System.out.print("Enter blood sugar level: ");
        String sugarLevelInput = scanner.nextLine();
        int sugarLevel;
        try {
            sugarLevel = Integer.parseInt(sugarLevelInput);
        } catch (NumberFormatException e) {
            System.out.println("Blood sugar level must be a number. Record creation failed.");
            return; // Exit method if sugarLevel is not a number
        }

        // Create and add the new record if all inputs are valid
        BloodSugar newRecord = new BloodSugar(id, name, yob, sugarLevel);
        records.add(newRecord);

        System.out.println("Record created successfully!");
        System.out.println("");
}


    public void delete() {
        records.clear();
        System.out.println("All records deleted!");
        System.out.println("");
    }

    public void exit() {
        System.out.println("Exiting application. Goodbye!");
        System.out.println("");
        System.exit(0);
    }

    
}





class BloodSugar {
    private final String id;
    private final String name;
    private final int yob;
    private final int sugarLevel;

    public BloodSugar(String id, String name, int yob, int sugarLevel) {
        this.id = id;
        this.name = name;
        this.yob = yob;
        this.sugarLevel = sugarLevel;
    }

    public String getId() {
        return id;
    }
    
    public int calculateAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - yob;
    }


    // Setters and getters

    public void display() {
        System.out.println("--------------------------");
        System.out.println("---------Blood Sugar Monitor-----------------");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Year of Birth: " + yob);
        System.out.println("Age: " + calculateAge());
        System.out.println("Sugar Level: " + sugarLevel);
        System.out.println("Sugar Level Calculation: " + calculate());
        System.out.println("--------------------------");
    }

    public String calculate() {
        String result = "Blood sugar level is ";

        if (sugarLevel <= 99) {
            result += "normal.";
        } else if (sugarLevel <= 130) {
            result += "slightly elevated.";
        } else if (sugarLevel <= 180) {
            result += "elevated.";
        } else {
            result += "high.";
        }

        return result;
    }
}


