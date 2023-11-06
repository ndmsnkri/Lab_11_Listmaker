import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
    private static ArrayList<String> itemList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice;

        do {
            displayMenu();
            choice = SafeInput.getRegExString(scanner, "Enter your choice: [AaDdPpQq]", "[AaDdPpQq]").toLowerCase().charAt(0);

            switch (choice) {
                case 'a':
                    addItem(scanner);
                    break;
                case 'd':
                    deleteItem(scanner);
                    break;
                case 'p':
                    printList();
                    break;
                case 'q':
                    if (getConfirmation(scanner, "Are you sure you want to quit? (y/n): ")) {
                        System.out.println("Goodbye!");
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit");
    }

    private static void addItem(Scanner scanner) {
        String item = SafeInput.getNonEmptyString(scanner, "Enter an item to add: ");
        itemList.add(item);
        System.out.println("Item added to the list.");
    }

    private static void deleteItem(Scanner scanner) {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        printNumberedItems();
        int itemNumber = SafeInput.getRangedInt(scanner, "Enter the item number to delete: ", 1, itemList.size());

        String removedItem = itemList.remove(itemNumber - 1);
        System.out.println("Item '" + removedItem + "' has been deleted.");
    }

    private static void printList() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("Current List:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i + 1) + ". " + itemList.get(i));
        }
    }

    private static void printNumberedItems() {
        System.out.println("Numbered Items:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i + 1) + ". " + itemList.get(i));
        }
    }

    private static boolean getConfirmation(Scanner scanner, String prompt) {
        return SafeInput.getYNConfirm(scanner, prompt);
    }
}
