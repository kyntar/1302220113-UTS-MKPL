package lib;

public class TaxFunction {

    private static final double TAX_RATE = 0.05;
    private static final int MAX_WORKING_MONTHS = 12;
    private static final int MAX_CHILDREN = 3;

    private static final int BASE_NON_TAXABLE = 54_000_000;
    private static final int MARRIAGE_ALLOWANCE = 4_500_000;
    private static final int CHILD_ALLOWANCE = 1_500_000;

    public static int calculateTax(int monthlySalary,
                                   int otherMonthlyIncome,
                                   int numberOfMonthWorking,
                                   int deductible,
                                   boolean isMarried,
                                   int numberOfChildren) {

        int validMonths   = Math.min(numberOfMonthWorking, MAX_WORKING_MONTHS);
        int validChildren = Math.min(numberOfChildren, MAX_CHILDREN);

        int raw = (monthlySalary + otherMonthlyIncome) * validMonths - deductible;
        int nonTaxable = BASE_NON_TAXABLE
                       + (isMarried ? MARRIAGE_ALLOWANCE + (validChildren * CHILD_ALLOWANCE) : 0);

        int tax = (int) Math.round(TAX_RATE * (raw - nonTaxable));
        return tax < 0 ? 0 : tax;
    }
}
