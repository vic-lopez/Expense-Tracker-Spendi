public class Expense {
    private float cost;
    private String category;
    private String description;

    public Expense () {
        cost = 0;
        category = "misc";
        description = "N/A";
    }

    // Base for all expenses
    public Expense (float cost, String category, String description) {
        this.cost = cost;
        this.category = category;
        this.description = description;
    }

    // Get Cost
    public float getCost() {
        return cost;
    }

    // Set Cost
    public void setCost(float cost) {
        this.cost = cost;
    }

    // Get Category
    public String getCategory() {
        return category;
    }

    // Set Category
    public void setCategory(String category) {
        this.category = category;
    }

    // Get Description
    public String getDescription() {
        return description;
    }

    // Set Description
    public void setDescription(String description) {
        this.description = description;
    }

    // Utility function for printing an expense
    public void printExpense() {
        System.out.println("Cost: " + cost);
        System.out.println("Category: " + category);
        System.out.println("Description: " + description);
    }

}

