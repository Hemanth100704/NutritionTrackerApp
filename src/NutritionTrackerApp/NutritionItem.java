package NutritionTrackerApp;

/**
 * Represents a nutrition item with its name, calories, protein, carbohydrates, and fat content.
 */
class NutritionItem {
    private String name;
    private int calories;
    private int protein;
    private int carbohydrates;
    private int fat;

    /**
     * Constructs a NutritionItem object.
     *
     * @param name          The name of the nutrition item.
     * @param calories      The calorie content of the nutrition item.
     * @param protein       The protein content of the nutrition item.
     * @param carbohydrates The carbohydrate content of the nutrition item.
     * @param fat           The fat content of the nutrition item.
     */
    public NutritionItem(String name, int calories, int protein, int carbohydrates, int fat) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
    }

    // Getters and setters for NutritionItem
    /**
     * Retrieves the name of the nutrition item.
     *
     * @return The name of the nutrition item.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the amount of calories in the nutrition item.
     *
     * @return The amount of calories.
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Retrieves the amount of protein in grams in the nutrition item.
     *
     * @return The amount of protein in grams.
     */
    public int getProtein() {
        return protein;
    }

    /**
     * Sets the amount of protein in grams in the nutrition item.
     *
     * @param protein The amount of protein in grams to set.
     */
    public void setProtein(int protein) {
        this.protein = protein;
    }

    /**
     * Retrieves the amount of carbohydrates in grams in the nutrition item.
     *
     * @return The amount of carbohydrates in grams.
     */
    public int getCarbohydrates() {
        return carbohydrates;
    }

    /**
     * Sets the amount of carbohydrates in grams in the nutrition item.
     *
     * @param carbohydrates The amount of carbohydrates in grams to set.
     */
    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    /**
     * Retrieves the amount of fat in grams in the nutrition item.
     *
     * @return The amount of fat in grams.
     */
    public int getFat() {
        return fat;
    }

    /**
     * Sets the amount of fat in grams in the nutrition item.
     *
     * @param fat The amount of fat in grams to set.
     */
    public void setFat(int fat) {
        this.fat = fat;
    }
}
