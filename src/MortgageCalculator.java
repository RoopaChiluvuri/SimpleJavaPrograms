import static java.lang.Math.pow;

public class MortgageCalculator {
    private int principal;
    private float annualInterest;
    private byte years;

    public MortgageCalculator(int principal, float annualInterest, byte years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    public double calculateMortgage() {

        float monthlyInterest = annualInterest / Main.PERCENT / Main.MONTHS_IN_YEAR;
        short numberOfPayments = (short)(years * Main.MONTHS_IN_YEAR);

        double mortgage = (principal * ((monthlyInterest * pow(1+monthlyInterest,numberOfPayments))
                / (pow(1+monthlyInterest, numberOfPayments)-1)) );

        return mortgage;
    }

    public double calculateBalance(short numberOfPaymentsMade){

        float monthlyInterest = annualInterest / Main.PERCENT / Main.MONTHS_IN_YEAR;
        short numberOfPayments = (short)(years * Main.MONTHS_IN_YEAR);

        double balance = principal
                * (pow(1 + monthlyInterest, numberOfPayments) - pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;
    }
}
