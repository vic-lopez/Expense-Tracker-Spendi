import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private final Scanner scnr = new Scanner(System.in);
    private final ArrayList<Expense> expenseList = new ArrayList<>();

    public void start() {
        System.out.println("Welcome to SPENDI the expense tracker.");
        mainMenu();
    }

    public void mainMenu() {

        System.out.println("Choose any of these options: \n" +
                "1.Add Expense 2.Delete Expense 3.Show Summary 4.View All 5.Exit");

        int input = scnr.nextInt();
        scnr.nextLine();
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
        Expense expense = new Expense();

        // SET COST
        System.out.println("Enter cost:");
        float cost = 0;
        while (cost <= 0) {
            cost = scnr.nextFloat();
            if (cost <= 0) {
                System.out.println("Cost cannot be negative or 0. Enter cost:");
            }
        }
        expense.setCost(cost);

        // SET CATEGORY
        String category = getCategoryInput();
        expense.setCategory(category);

        // SET DESCRIPTION
        System.out.println("Enter a description:");
        String description = scnr.nextLine();
        expense.setDescription(description);

        // ADD TO CATALOG
        expenseList.add(expense);

        System.out.println("Expense added.");

        mainMenu();
    }

    public String getCategoryInput() {
        System.out.println("Select a category: \n" +
                "1.Personal 2.Home 3.Business 4.School");

        int categoryInput = scnr.nextInt();
        scnr.nextLine();
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
        for (int i = 0; i  < expenseList.size(); i++) {
            System.out.println("Expense #" + i + ":");
            expenseList.get(i).printExpense();
        }

        System.out.println("Choose an expense to delete: ");
        while (true) {
            int input = scnr.nextInt();

            if (input >= 0 && input < expenseList.size()) {
                expenseList.remove(input);
                break;
            }
            System.out.println("Input invalid. Choose an expense to delete: ");
        }

        System.out.println("Expense deleted.");

        mainMenu();
    }

    public void showSummary() {
        float personalExpensesSum = 0;
        float homeExpensesSum = 0;
        float businessExpensesSum = 0;
        float schoolExpensesSum = 0;

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
        for (int i = 0; i < expenseList.size(); i++) {
            System.out.println("Expense #" + i + ":");
            expenseList.get(i).printExpense();
        }

        System.out.println("Enter any key to finish viewing: ");
        scnr.nextLine();
        mainMenu();
    }

    public void exit() {
        System.out.println("Thank you for choosing SPENDI! Goodbye.");
    }

}