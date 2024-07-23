// Mortgage Calculator

import java.text.NumberFormat;

import static java.lang.Math.pow;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {
        int principal = (int) Console.readNumber("Principal: ", 1000, 1_00_000);
        float annualInterest = (float) Console.readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte) Console.readNumber("Period (Years): ", 1, 30);

        double mortgage = calculateMortgage(principal, annualInterest, years);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("\nMORTGAGE");
        System.out.println("----------");
        System.out.println("Monthly Payments: " + mortgageFormatted);

        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("-----------------");
        for (short month = 1; month <= years * MONTHS_IN_YEAR; month++){
           double balance = calculateBalance(principal, annualInterest, years, month);
           System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double calculateMortgage(
            int principal,
            float annualInterest,
            byte years) {

        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        double mortgage = (principal * ((monthlyInterest * pow(1+monthlyInterest,numberOfPayments))
                / (pow(1+monthlyInterest, numberOfPayments)-1)) );

        return mortgage;
    }

    public static double calculateBalance(
            int principal,
            float annualInterest,
            byte years,
            short numberOfPaymentsMade){

        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        double balance = principal
                * (pow(1 + monthlyInterest, numberOfPayments) - pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;
    }
}