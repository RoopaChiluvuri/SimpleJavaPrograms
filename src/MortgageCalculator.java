import static java.lang.Math.pow;

public class MortgageCalculator {
    private final static byte MONTHS_IN_YEAR = 12;
    private final static byte PERCENT = 100;

    private int principal;
    private float annualInterest;
    private byte years;


    public MortgageCalculator(int principal, float annualInterest, byte years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    public double calculateMortgage() {
        float monthlyInterest = getMonthlyInterest();
        short numberOfPayments = (short) getNumberOfPayments();

        double mortgage = (principal * ((monthlyInterest * pow(1 + monthlyInterest, numberOfPayments))
                / (pow(1 + monthlyInterest, numberOfPayments) - 1)));

        return mortgage;
    }

    public double calculateBalance(short numberOfPaymentsMade) {

        float monthlyInterest = getMonthlyInterest();
        short numberOfPayments = (short) getNumberOfPayments();

        double balance = principal
                * (pow(1 + monthlyInterest, numberOfPayments) - pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;
    }

    public double[] getRemainingBalances(){
        var balances = new double[getNumberOfPayments()];
        for (short month = 1; month <= balances.length; month++)
            balances[month-1] = calculateBalance(month);
        return balances;
    }

    private int getNumberOfPayments() {
        return years * MONTHS_IN_YEAR;
    }

    private float getMonthlyInterest() {
        return annualInterest / PERCENT / MONTHS_IN_YEAR;
    }
}
