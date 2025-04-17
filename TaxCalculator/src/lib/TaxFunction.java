package lib;

public class TaxFunction {

    private static final double TAX_RATE = 0.05;
    private static final int MAX_WORKING_MONTHS = 12;
    private static final int MAX_CHILDREN = 3;

    private static final int BASE_NON_TAXABLE = 54_000_000;
    private static final int MARRIAGE_ALLOWANCE = 4_500_000;
    private static final int CHILD_ALLOWANCE = 1_500_000;  // sesuai kode lama

    public static int calculateTax(int monthlySalary,
                                   int otherMonthlyIncome,
                                   int numberOfMonthWorking,
                                   int deductible,
                                   boolean isMarried,
                                   int numberOfChildren) {

        if (numberOfMonthWorking > MAX_WORKING_MONTHS) {
            System.err.println("More than " + MAX_WORKING_MONTHS + " months working per year");
        }

        if (numberOfChildren > MAX_CHILDREN) {
            numberOfChildren = MAX_CHILDREN;
        }

        int raw = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking - deductible;
        int nonTaxable = BASE_NON_TAXABLE
                       + (isMarried ? MARRIAGE_ALLOWANCE + (numberOfChildren * CHILD_ALLOWANCE) : 0);

        int tax = (int) Math.round(TAX_RATE * (raw - nonTaxable));
        return tax < 0 ? 0 : tax;
    }
}
