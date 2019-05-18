package Others;

import java.util.Scanner;


public class Armstrong {
    static Scanner scan;

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        int n = inputInt("please enter the number");
        boolean isArmstrong = checkIfANumberIsAmstrongOrNot(n);
        if (isArmstrong) {
            System.out.println("the number is armstrong");
        } else {
            System.out.println("the number is not armstrong");
        }
    }

    
    public static boolean checkIfANumberIsAmstrongOrNot(int number) {
        int remainder, sum = 0, temp = 0;
        temp = number;
        while (number > 0) {
            remainder = number % 10;
            sum = sum + (remainder * remainder * remainder);
            number = number / 10;
        }
        if (sum == temp) {
            return true;
        } else {
            return false;
        }
    }

    private static int inputInt(String string) {
        System.out.print(string);
        return Integer.parseInt(scan.nextLine());
    }
}
