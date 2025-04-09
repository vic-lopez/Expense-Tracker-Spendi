public class Expense {
    private float cost;
    private String category;
    private String description;

    public Expense () {
        cost = 0;
        category = "misc";
        description = "N/A";
    }

    public Expense (float cost, String category, String description) {
        this.cost = cost;
        this.category = category;
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void printExpense() {
        System.out.println("Cost: " + cost);
        System.out.println("Category: " + category);
        System.out.println("Description: " + description);
    }

}

