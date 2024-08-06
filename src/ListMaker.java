import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
    private static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            String choice = SafeInput.getRegExString(scanner, "Choose an option: ", "[AaDdIiPpQq]").toUpperCase();

            switch (choice) {
                case "A":
                    addItem(scanner);
                    break;
                case "D":
                    deleteItem(scanner);
                    break;
                case "I":
                    insertItem(scanner);
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    running = SafeInput.getYNConfirm(scanner, "Are you sure you want to quit? ");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private static void displayMenu() {
        System.out.println("\nCurrent List:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }
        System.out.println("\nMenu:");

        System.out.println("A - Add an item to the list");

        System.out.println("D - Delete an item from the list");

        System.out.println("I - Insert an item into the list");


        System.out.println("P - Print the list");

        System.out.println("Q - Quit");}

    private static void addItem(Scanner scanner) {
        String item = SafeInput.getNonZeroLenString(scanner, "Enter the item to add: ");
        list.add(item);
        System.out.println("Item added.");
    }

    private static void deleteItem(Scanner scanner) {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        int itemNumber = SafeInput.getRangedInt(scanner, "Enter the item number to delete: ", 1, list.size());
        list.remove(itemNumber - 1);
        System.out.println("Item deleted.");
    }


    private static void insertItem(Scanner scanner) {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Adding the first item.");
            addItem(scanner);
            return;
        }



        int position = SafeInput.getRangedInt(scanner, "Enter the position to insert the item: ", 1, list.size() + 1);
        String item = SafeInput.getNonZeroLenString(scanner, "Enter the item to insert: ");
        list.add(position - 1, item);
        System.out.println("Item inserted.");
    }



    private static void printList() {
        System.out.println("\nCurrent List:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }
    }
}
