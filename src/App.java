import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private final Scanner scnr = new Scanner(System.in);
    private final ArrayList<Expense> expenseList = new ArrayList<>();

    public void start() {
        // Try - Catch to catch a file not found exception from FileReader in case this is the first time the program has run
        try {
            // Here we read a file called catalog and parse through it to get our data
            FileReader fileReader = new FileReader("catalog");
            Scanner fileScanner = new Scanner(fileReader);
            // The catalog file is in the form "expenseCount\n expenseCost expenseCategory expenseDescription
            int expenseCount = fileScanner.nextInt();
            for (int i = 0; i < expenseCount; i++) {
                Expense expense = new Expense(
                    fileScanner.nextFloat(),
                    fileScanner.next(),
                    fileScanner.nextLine().substring(1)
                );
                expenseList.add(expense);
            }
            fileScanner.close();
            fileReader.close();
        }
        catch (Exception e) {
            System.out.println("Could not find existing catalog data, initializing SPENDI with no data...");
        }

        // Welcome message to user
        System.out.println("Welcome to SPENDI the expense tracker.");

        mainMenu();
    }

    public void mainMenu() {
        // Main menu is called whenever the use finishes running an action,
        // it provides choices to the user and calls the appropriate function after parsing input
        System.out.println("Choose any of these options: \n" +
                "1.Add Expense 2.Delete Expense 3.Show Summary 4.View All 5.Exit");

        int input = getUserInt();
        if(input == 1) {
            addExpense();
        }
        else if(input == 2) {
            deleteExpense();
        }
        else if(input == 3) {
            showSummary();
        }
        else if(input == 4) {
            viewAll();
        }
        else if(input == 5) {
            exit();
        }
        else {
            mainMenu();
        }
    }

    public void addExpense() {
        // Creates an expense object
        Expense expense = new Expense();

        // Set Cost
        System.out.println("Enter cost:");
        float cost = 0;
        while (cost <= 0) {
            cost = getUserFloat();
            if (cost <= 0) {
                System.out.println("Cost cannot be negative or 0. Enter cost:");
            }
        }
        expense.setCost(cost);

        // Set Category
        String category = getCategoryInput();
        expense.setCategory(category);

        // Set Description
        System.out.println("Enter a description:");
        String description = scnr.nextLine();
        expense.setDescription(description);

        // Add to Catalog
        expenseList.add(expense);

        // Confirmation to User
        System.out.println("Expense added.");

        mainMenu();
    }

    public String getCategoryInput() {
        // Utility function to print out categories, returns selected category
        System.out.println("Select a category: \n" +
                "1.Personal 2.Home 3.Business 4.School");

        int categoryInput = getUserInt();
        if(categoryInput == 1) {
            return "Personal";
        }
        else if (categoryInput == 2) {
            return "Home";
        }
        else if (categoryInput == 3) {
            return "Business";
        }
        else if (categoryInput == 4) {
            return "School";
        }
        else {
            return getCategoryInput();
        }
    }

    public void deleteExpense() {
        // Iterate through expense list and print out each expense using printExpense
        for (int i = 0; i  < expenseList.size(); i++) {
            System.out.println("Expense #" + i + ":");
            expenseList.get(i).printExpense();
        }

        // Removal Selection
        System.out.println("Choose an expense to delete: ");
        while (true) {
            int input = getUserInt();

            if (input >= 0 && input < expenseList.size()) {
                expenseList.remove(input);
                break;
            }
            System.out.println("Input invalid. Choose an expense to delete: ");
        }

        // Confirmation to User
        System.out.println("Expense deleted.");

        mainMenu();
    }

    public void showSummary() {
        float personalExpensesSum = 0;
        float homeExpensesSum = 0;
        float businessExpensesSum = 0;
        float schoolExpensesSum = 0;

        // Iterate through expenseList and calculate total sums by category
        for (Expense expense : expenseList) { // this is for (i=0; i < expenseList.size(); i++)
            if (expense.getCategory().equals("Personal")) {
                personalExpensesSum += expense.getCost();
            }
            if (expense.getCategory().equals("Home")) {
                homeExpensesSum += expense.getCost();
            }
            if (expense.getCategory().equals("Business")) {
                businessExpensesSum += expense.getCost();
            }
            if (expense.getCategory().equals("School")) {
                schoolExpensesSum += expense.getCost();
            }

        }

        // Print total sums by category in a table using 2D-array
        String[] tableColumn = {"Category", "Sum Total"};
        String[][] tableRow = {
                {"Personal:", Float.toString(personalExpensesSum)},
                {"Home:", Float.toString(homeExpensesSum)},
                {"Business:", Float.toString(businessExpensesSum)},
                {"School:", Float.toString(schoolExpensesSum)}
        };

        System.out.printf("%-10s %s\n", tableColumn[0], tableColumn[1]);
        for (String[] row : tableRow) {
            System.out.printf("%-10s %s\n", row[0], row[1]);
        }

        System.out.println("Enter any key to finish viewing: ");
        scnr.nextLine();

        mainMenu();
    }

    public void viewAll() {
        // Iterate through expense list and print out each expense using printExpense
        for (int i = 0; i < expenseList.size(); i++) {
            System.out.println("Expense #" + i + ":");
            expenseList.get(i).printExpense();
        }

        System.out.println("Enter any key to finish viewing: ");
        scnr.nextLine();

        mainMenu();
    }

    public void exit() {
        // Try - Catch to catch an IOException and inform the user if their data can not be saved
        try {
            FileWriter fileWriter = new FileWriter("catalog");
            fileWriter.write(expenseList.size() + "\n");
            for (Expense expense: expenseList) {
                fileWriter.write(
                    String.format(
                        "%f %s %s\n",
                        expense.getCost(),
                        expense.getCategory(),
                        expense.getDescription()
                    )
                );
            }
            fileWriter.flush();
            fileWriter.close();

            // Goodbye message to user
            System.out.println("Thank you for choosing SPENDI! Goodbye.");
        }
        catch (Exception e) {
            System.out.println("Sorry, we couldn't save your data, please try again.");
            mainMenu();
        }
    }

    public int getUserInt() {
        // Utility function for getting an integer from the user
        try {
            int num = scnr.nextInt();
            scnr.nextLine();
            return num;
        }
        catch (Exception e) {
            scnr.nextLine();
            System.out.println("Input was not a number, please try again: ");
            return getUserInt();
        }
    }

    public float getUserFloat() {
        // Utility function for getting a float from the user
        try {
            float num = scnr.nextFloat();
            scnr.nextLine();
            return num;
        }
        catch (Exception e) {
            scnr.nextLine();
            System.out.println("Input was not a number, please try again: ");
            return getUserFloat();
        }
    }

}