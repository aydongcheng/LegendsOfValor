import java.util.*;

// A class that provides utility functions.
public class Utils {
    static Scanner scan = new Scanner(System.in);

    // Foolproof integer input
    public static int safeIntInput(String message, int min, int max) {
        int int_tmp;

        while (true) {
            System.out.println(message);
            String str_tmp = scan.next();
            try {
                int_tmp = Integer.parseInt(str_tmp);
                if ((int_tmp >= min) && (int_tmp <= max)) {
                    break;
                }
                System.out.println("Not within range [" + min + ", " + max + "]!");
              } catch(NumberFormatException e) {
                System.out.println("Not an integer!");
            } 
        }
        return int_tmp;
    }

    // Foolproof double input
    public static double safeDoubleInput(String message, double min, double max) {
        double double_tmp;

        while (true) {
            System.out.println(message);
            String str_tmp = scan.next();
            try {
                double_tmp = Double.parseDouble(str_tmp);
                if ((double_tmp >= min) && (double_tmp <= max)) {
                    break;
                }
                System.out.println("Not within range [" + min + ", " + max + "]!");
              } catch(NumberFormatException e) {
                System.out.println("Not a number!");
            } 
        }
        return double_tmp;
    }

    // Foolproof char input
    public static char safeCharInput(String message, char[] allowedChars) {
        char char_tmp;

        while (true) {
            System.out.println(message);
            String str_tmp = scan.next();
            if (str_tmp.length() != 1) {
                System.out.println("Please input one character!");
                continue;
            }
            char_tmp = str_tmp.charAt(0);
            for (int idx = 0; idx < allowedChars.length; idx++) {
                if (char_tmp == allowedChars[idx]) {
                    return char_tmp;
                }
            }
            System.out.println("Illegal input!");
        }
    }    

    // Wait for x seconds with an animation
    public static void beautifulWait(double seconds, int width) {
        System.out.print("\n");
        for (int idx = 0; idx < width; idx++) {
            System.out.print("*");
            try {
                Thread.sleep((int)(1000 * seconds / width));
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.print("\n");
        System.out.print("\n");
    }
}
