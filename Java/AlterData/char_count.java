package Others;

import java.util.Scanner;


public class CountChar {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your text: ");
        String str = input.nextLine();
        input.close();
        System.out.println("There are " + CountCharacters(str) + " characters.");
    }

    /**
     * @param str: String to count the characters
     * @return int: Number of characters in the passed string
     */

    private static int CountCharacters(String str) {

        int count = 0;

        if (str == "" || str == null) {
            return 0;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                count++;
            }
        }

        return count;
    }
}
