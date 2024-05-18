package NutritionTrackerApp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A nutrition tracking application that allows users to manage nutrition items, create meals, and perform various operations.
 */
public class NutritionTrackerApp {
    private static final String FILENAME = "nutrition_data.txt";
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<NutritionItem> nutritionItems = new ArrayList<>();

    /**
     * The main method that starts the nutrition tracking application.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        ArrayList<Meal> meals = new ArrayList<>();

        loadNutritionData();

        while (true) {
            // Main menu
            System.out.println("\nWelcome to Nutrition Tracker!");
            System.out.println("1. Add Nutrition Item");
            System.out.println("2. Create Meal");
            System.out.println("3. View All Meals");
            System.out.println("4. View All Nutrition Items");
            System.out.println("5. Search Nutrition Item");
            System.out.println("6. Partial Show by Calories Range");
            System.out.println("7. Save Data to File");
            System.out.println("8. Sort Meals by Calories");
            System.out.println("9. Sort Meals by Protein");
            System.out.println("10. Delete Nutrition Item");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addNutritionItem();
                    break;
                case 2:
                    createMeal(meals);
                    break;
                case 3:
                    viewAllMeals(meals);
                    break;
                case 4:
                    viewAllNutritionItems(); // Call the new method
                    break;
                case 5:
                    searchNutritionItem();
                    break;
                case 6:
                    partialShowByCaloriesRange(meals);
                    break;
                case 7:
                    saveNutritionData();
                    break;
                case 8:
                    sortMealsByCalories(meals);
                    break;
                case 9:
                    sortMealsByProtein(meals);
                    break;
                case 10:
                    deleteNutritionItem();
                    break;
                case 11:
                    System.out.println("Exiting Nutrition Tracker. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Methods for adding, creating, viewing, searching, and deleting nutrition items and meals
    /**
     * Adds a new nutrition item to the list of nutrition items.
     */
    private static void addNutritionItem() {
        // Add Nutrition Item
        System.out.println("\nAdding a Nutrition Item:");
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter calories: ");
        int calories = scanner.nextInt();
        System.out.print("Enter protein (grams): ");
        int protein = scanner.nextInt();
        System.out.print("Enter carbohydrates (grams): ");
        int carbohydrates = scanner.nextInt();
        System.out.print("Enter fat (grams): ");
        int fat = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        NutritionItem newItem = new NutritionItem(itemName, calories, protein, carbohydrates, fat);
        // Add item to the list
        nutritionItems.add(newItem);
        System.out.println("Item added successfully!");
    }

    /**
     * Creates a new meal with user-specified nutrition items.
     *
     * @param meals The list of meals to add the new meal to.
     */
    private static void createMeal(ArrayList<Meal> meals) {
        // Create Meal
        System.out.println("\nCreating a Meal:");
        System.out.print("Enter meal name: ");
        String mealName = scanner.nextLine();
        Meal meal = new Meal(mealName);
        boolean addingItems = true;
        while (addingItems) {
            System.out.print("Enter item name (type 'done' to finish adding items): ");
            String itemNameInput = scanner.nextLine();
            if (itemNameInput.equalsIgnoreCase("done")) {
                addingItems = false;
            } else {
                // Retrieve item from database or list based on itemNameInput
                NutritionItem item = retrieveItemByName(itemNameInput);
                // Add item to the meal
                meal.addItem(item);
            }
        }
        meals.add(meal);
    }

    /**
     * Displays all meals along with their respective nutrition items.
     *
     * @param meals The list of meals to display.
     */
    private static void viewAllMeals(ArrayList<Meal> meals) {
        // View All Meals
        System.out.println("\nAll Meals:");
        for (int i = 0; i < meals.size(); i++) {
            Meal currentMeal = meals.get(i);
            System.out.println("Meal " + (i + 1) + ": " + currentMeal.getMealName());
            ArrayList<NutritionItem> mealItems = currentMeal.getMealItems();
            System.out.println("Items:");
            for (NutritionItem item : mealItems) {
                System.out.println("- " + item.getName() + ": Calories - " + item.getCalories() +
                        ", Protein - " + item.getProtein() + "g, Carbohydrates - " +
                        item.getCarbohydrates() + "g, Fat - " + item.getFat() + "g");
            }
        }
    }

    /**
     * Displays all nutrition items in the list.
     */
    private static void viewAllNutritionItems() {
        // View All Nutrition Items
        System.out.println("\nAll Nutrition Items:");
        for (NutritionItem item : nutritionItems) {
            System.out.println("- " + item.getName() + ": Calories - " + item.getCalories() +
                    ", Protein - " + item.getProtein() + "g, Carbohydrates - " +
                    item.getCarbohydrates() + "g, Fat - " + item.getFat() + "g");
        }
    }

    /**
     * Searches for a nutrition item by its name.
     */
    private static void searchNutritionItem() {
        // Search Nutrition Item
        System.out.print("\nEnter item name to search: ");
        String itemName = scanner.nextLine();
        NutritionItem item = retrieveItemByName(itemName);
        if (item != null) {
            System.out.println("Item found: " + item.getName() + ": Calories - " + item.getCalories() +
                    ", Protein - " + item.getProtein() + "g, Carbohydrates - " +
                    item.getCarbohydrates() + "g, Fat - " + item.getFat() + "g");
        } else {
            System.out.println("Item with name '" + itemName + "' not found.");
        }
    }

    /**
     * Displays meals with total calories falling within a specified range.
     *
     * @param meals The list of meals to search within.
     */
    private static void partialShowByCaloriesRange(ArrayList<Meal> meals) {
        // Partial Show by Calories Range
        System.out.print("\nEnter minimum calories: ");
        int minCalories = scanner.nextInt();
        System.out.print("Enter maximum calories: ");
        int maxCalories = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("\nMeals with total calories in range [" + minCalories + ", " + maxCalories + "]:");
        for (Meal meal : meals) {
            int totalCalories = meal.calculateTotalCalories();
            if (totalCalories >= minCalories && totalCalories <= maxCalories) {
                System.out.println("- " + meal.getMealName() + ": Total Calories - " + totalCalories);
            }
        }
    }

    /**
     * Saves the list of nutrition items to a file.
     */

    private static void saveNutritionData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILENAME))) {
            for (NutritionItem item : nutritionItems) {
                writer.println(item.getName() + "," + item.getCalories() + "," +
                        item.getProtein() + "," + item.getCarbohydrates() + "," +
                        item.getFat());
            }
            System.out.println("Nutrition data saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving nutrition data to file: " + e.getMessage());
        }
    }

    // Methods for loading and saving nutrition data
    /**
     * Loads nutrition data from a file and populates the list of nutrition items.
     */
    private static void loadNutritionData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String name = parts[0];
                    int calories = Integer.parseInt(parts[1]);
                    int protein = Integer.parseInt(parts[2]);
                    int carbohydrates = Integer.parseInt(parts[3]);
                    int fat = Integer.parseInt(parts[4]);
                    NutritionItem item = new NutritionItem(name, calories, protein, carbohydrates, fat);
                    nutritionItems.add(item);
                }
            }
            System.out.println("Nutrition data loaded from file.");
        } catch (IOException e) {
            System.err.println("Error loading nutrition data from file: " + e.getMessage());
        }
    }

    // Methods for sorting meals by calories or protein
    /**
     * Sorts the list of meals by total calories.
     *
     * @param meals The list of meals to be sorted.
     */
    private static void sortMealsByCalories(ArrayList<Meal> meals) {
        for (Meal meal : meals) {
            meal.selectionSortByCalories();
        }
        System.out.println("Meals sorted by calories.");
    }

    /**
     * Sorts the list of meals by total protein.
     *
     * @param meals The list of meals to be sorted.
     */
    private static void sortMealsByProtein(ArrayList<Meal> meals) {
        for (Meal meal : meals) {
            meal.insertionSortByProtein();
        }
        System.out.println("Meals sorted by protein.");
    }

    /**
     * Deletes a nutrition item from the list of nutrition items.
     */
    private static void deleteNutritionItem() {
        // Delete Nutrition Item
        System.out.print("\nEnter item name to delete: ");
        String itemName = scanner.nextLine();
        NutritionItem item = retrieveItemByName(itemName);
        if (item != null) {
            nutritionItems.remove(item);
            System.out.println("Item '" + itemName + "' deleted successfully.");
        } else {
            System.out.println("Item with name '" + itemName + "' not found.");
        }
    }

    /**
     * Retrieves a NutritionItem by its name from the list of nutrition items.
     *
     * @param itemName The name of the nutrition item to retrieve.
     * @return The NutritionItem with the specified name, or null if not found.
     */
    public static NutritionItem retrieveItemByName(String itemName) {
        for (NutritionItem item : nutritionItems) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null; // Item not found
    }
}