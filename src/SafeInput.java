import java.util.Scanner;

public class SafeInput {


    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String input = "";
        do {
            System.out.print(prompt);
            input = pipe.nextLine();
        } while (input.length() == 0);
        return input;
    }


    public static int getInt(Scanner pipe, String prompt) {
        int input = 0;
        boolean valid = false;
        do {
            System.out.print(prompt);
            if (pipe.hasNextInt()) {
                input = pipe.nextInt();
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
            }
            pipe.nextLine();
        } while (!valid);
        return input;
    }
    public static double getDouble(Scanner pipe, String prompt) {
        double input = 0.0;
        boolean valid = false;
        do {
            System.out.print(prompt);
            if (pipe.hasNextDouble()) {
                input = pipe.nextDouble();
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a double.");
            }
            pipe.nextLine();
        } while (!valid);
        return input;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int input = 0;
        boolean valid = false;
        do {
            System.out.print(prompt);
            if (pipe.hasNextInt()) {
                input = pipe.nextInt();
                if (input >= low && input <= high) {
                    valid = true;
                } else {
                    System.out.println("Input out of range. Enter a number between " + low + " and " + high + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
            }
            pipe.nextLine();
        } while (!valid);
        return input;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double input = 0.0;
        boolean valid = false;
        do {
            System.out.print(prompt);
            if (pipe.hasNextDouble()) {
                input = pipe.nextDouble();
                if (input >= low && input <= high) {
                    valid = true;
                } else {
                    System.out.println("Input out of range. Enter a number between " + low + " and " + high + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a double.");
            }
            pipe.nextLine();
        } while (!valid);
        return input;
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String input = "";
        boolean valid = false;
        boolean result = false;
        do {
            System.out.print(prompt + " [Y/N]: ");
            input = pipe.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                valid = true;
                result = true;
            } else if (input.equals("n") || input.equals("no")) {
                valid = true;
                result = false;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        } while (!valid);
        return result;
    }
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String input = "";
        boolean valid = false;
        do {
            System.out.print(prompt);
            input = pipe.nextLine();
            if (input.matches(regEx)) {
                valid = true;
            } else {
                System.out.println("Input does not match the pattern. Please try again.");
            }
        } while (!valid);
        return input;
    }
}
