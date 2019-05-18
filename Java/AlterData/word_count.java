package Others;

import java.util.Scanner;


public class CountWords {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your text: ");
        String str = input.nextLine();

        System.out.println("Your text has " + wordCount(str) + " word(s)");
        input.close();
    }

    private static int wordCount(String s) {
        if (s == null || s.isEmpty())
            return 0;
        return s.trim().split("[\\s]+").length;
    }

}
