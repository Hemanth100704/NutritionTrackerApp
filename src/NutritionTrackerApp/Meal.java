package NutritionTrackerApp;

import java.util.ArrayList;

/**
 * Represents a meal consisting of multiple nutrition items.
 */
class Meal {
    private ArrayList<NutritionItem> mealItems;
    private String mealName;

    /**
     * Constructs a Meal object with the given name.
     *
     * @param mealName The name of the meal.
     */
    public Meal(String mealName) {
        this.mealName = mealName;
        mealItems = new ArrayList<>();
    }

    public void addItem(NutritionItem item) {
        mealItems.add(item);
    }

    public void removeItem(NutritionItem item) {
        mealItems.remove(item);
    }

    public int calculateTotalCalories() {
        int totalCalories = 0;
        for (NutritionItem item : mealItems) {
            totalCalories += item.getCalories();
        }
        return totalCalories;
    }

    public int calculateTotalProtein() {
        int totalProtein = 0;
        for (NutritionItem item : mealItems) {
            totalProtein += item.getProtein();
        }
        return totalProtein;
    }

    public int calculateTotalCarbohydrates() {
        int totalCarbohydrates = 0;
        for (NutritionItem item : mealItems) {
            totalCarbohydrates += item.getCarbohydrates();
        }
        return totalCarbohydrates;
    }

    public int calculateTotalFat() {
        int totalFat = 0;
        for (NutritionItem item : mealItems) {
            totalFat += item.getFat();
        }
        return totalFat;
    }

    public void selectionSortByCalories() {
        int n = mealItems.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (mealItems.get(j).getCalories() < mealItems.get(minIndex).getCalories()) {
                    minIndex = j;
                }
            }
            NutritionItem temp = mealItems.get(minIndex);
            mealItems.set(minIndex, mealItems.get(i));
            mealItems.set(i, temp);
        }
    }

    public void insertionSortByProtein() {
        int n = mealItems.size();
        for (int i = 1; i < n; i++) {
            NutritionItem key = mealItems.get(i);
            int j = i - 1;
            while (j >= 0 && mealItems.get(j).getProtein() > key.getProtein()) {
                mealItems.set(j + 1, mealItems.get(j));
                j = j - 1;
            }
            mealItems.set(j + 1, key);
        }
    }

    // Getters and setters for Meal
    /**
     * Retrieves the name of the meal.
     *
     * @return The name of the meal.
     */
    public String getMealName() {
        return mealName;
    }

    /**
     * Retrieves the list of nutrition items in the meal.
     *
     * @return The list of nutrition items.
     */
    public ArrayList<NutritionItem> getMealItems() {
        return mealItems;
    }

    /**
     * Sets the list of nutrition items in the meal.
     *
     * @param mealItems The list of nutrition items to set.
     */
    public void setMealItems(ArrayList<NutritionItem> mealItems) {
        this.mealItems = mealItems;
    }
}
