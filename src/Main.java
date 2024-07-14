// Mortgage Calculator

import java.text.NumberFormat;
import java.util.Scanner;

import static java.lang.Math.pow;

public class Main {
    public static void main(String[] args) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;
        int principal = 0;
        float monthlyInterest = 0;
        int numberOfPayments = 0;

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print("Principal ($1K - $1M) : ");
            principal = scanner.nextInt();
            if ( (principal >= 1000) && (principal <= 1_000_000)) {
                break;
            }
            System.out.println("Enter a number between 1,000 and 1,000,000 ");
        }

        while(true){
            System.out.print("Annual Interest Rate: ");
            float annualInterest = scanner.nextFloat();
            if (annualInterest >= 0 && annualInterest <= 30) {
                monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
                break;
            }
            System.out.println("Enter a value greater than 0 and less then or equal to 30");
        }

        while(true){
            System.out.print("Period (Years): ");
            byte years = scanner.nextByte();
            if (years >= 0 && years <= 30) {
                numberOfPayments = years * MONTHS_IN_YEAR;
                break;
            }
            System.out.println("Enter a value between 1 and 30.");
        }


        double mortgage = (principal * ((monthlyInterest * pow(1+monthlyInterest,numberOfPayments))
                          / (pow(1+monthlyInterest, numberOfPayments)-1)) );

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + mortgageFormatted);

    }
}