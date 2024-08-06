import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
    private static ArrayList<String> list = new ArrayList<>();
    private static boolean needsToBeSaved = false;
    private static String currentFileName = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            String choice = SafeInput.getRegExString(scanner, "Choose an option: ", "[AaDdIiMmOoSsCcVvQq]").toUpperCase();

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
                case "M":
                    moveItem(scanner);
                    break;
                case "O":
                    loadList(scanner);
                    break;
                case "S":
                    saveList(scanner);
                    break;
                case "C":
                    clearList(scanner);
                    break;
                case "V":
                    viewList();
                    break;
                case "Q":
                    running = confirmExit(scanner);
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
        System.out.println("M - Move an item within the list");
        System.out.println("O - Open a list file from disk");
        System.out.println("S - Save the current list file to disk");
        System.out.println("C - Clear the current list");
        System.out.println("V - View the list");
        System.out.println("Q - Quit");
    }

    private static void addItem(Scanner scanner) {
        String item = SafeInput.getNonZeroLenString(scanner, "Enter the item to add: ");
        list.add(item);
        needsToBeSaved = true;
        System.out.println("Item added.");
    }

    private static void deleteItem(Scanner scanner) {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        int itemNumber = SafeInput.getRangedInt(scanner, "Enter the item number to delete: ", 1, list.size());
        list.remove(itemNumber - 1);
        needsToBeSaved = true;
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
        needsToBeSaved = true;
        System.out.println("Item inserted.");
    }

    private static void moveItem(Scanner scanner) {
        if (list.size() < 2) {
            System.out.println("Not enough items to move.");
            return;
        }

        int fromIndex = SafeInput.getRangedInt(scanner, "Enter the item number to move: ", 1, list.size());
        int toIndex = SafeInput.getRangedInt(scanner, "Enter the new position for the item: ", 1, list.size());

        if (fromIndex != toIndex) {
            String item = list.remove(fromIndex - 1);
            list.add(toIndex - 1, item);
            needsToBeSaved = true;
            System.out.println("Item moved.");
        } else {
            System.out.println("error item is there.");
        }
    }

    private static void loadList(Scanner scanner) {
        if (needsToBeSaved) {
            if (!SafeInput.getYNConfirm(scanner, "You have unsaved changes. make new list?")) {
                return;
            }
        }

        String fileName = SafeInput.getNonZeroLenString(scanner, "enter file name: ") + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader("src/" + fileName))) {
            list.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            currentFileName = fileName;
            needsToBeSaved = false;
            System.out.println("List loaded successfully from " + fileName);
        } catch (IOException e) {
            System.out.println("error with file: " + e.getMessage());
        }
    }

    private static void saveList(Scanner scanner) {
        if (list.isEmpty()) {
            System.out.println("error empty list");
            return;
        }

        if (currentFileName.isEmpty()) {
            currentFileName = SafeInput.getNonZeroLenString(scanner, "Enter the filename") + ".txt";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/" + currentFileName))) {
            for (String item : list) {
                writer.write(item);
                writer.newLine();
            }
            needsToBeSaved = false;
            System.out.println("List saved successfully to " + currentFileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file: " + e.getMessage());
        }
    }

    private static void clearList(Scanner scanner) {
        if (SafeInput.getYNConfirm(scanner, "Are you sure you want to clear the entire list? ")) {
            list.clear();
            needsToBeSaved = true;
            System.out.println("List cleared.");
        }
    }

    private static void viewList() {
        System.out.println("\nCurrent List:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }
    }

    private static boolean confirmExit(Scanner scanner) {
        if (needsToBeSaved) {
            if (SafeInput.getYNConfirm(scanner, "You have unsaved changes. Do you want to save before exiting?")) {
                saveList(scanner);
            }
        }
        return SafeInput.getYNConfirm(scanner, "Are you sure you want to quit? ");
    }
}
