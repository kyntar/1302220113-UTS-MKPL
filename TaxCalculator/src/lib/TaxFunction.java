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
        validateMonths(numberOfMonthWorking);
        int validChildren = capChildren(numberOfChildren);

        int grossIncome      = calculateGrossIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
        int nonTaxableIncome = calculateNonTaxable(isMarried, validChildren);
        int taxableIncome    = grossIncome - deductible - nonTaxableIncome;

        return calculateFinalTax(taxableIncome);
    }

    private static void validateMonths(int months) {
        if (months < 0 || months > MAX_WORKING_MONTHS) {
            throw new IllegalArgumentException("Months worked must be 0–" + MAX_WORKING_MONTHS);
        }
    }

    private static int capChildren(int count) {
        return Math.max(0, Math.min(count, MAX_CHILDREN));
    }

    private static int calculateGrossIncome(int salary, int otherIncome, int months) {
        return (salary + otherIncome) * months;
    }

    private static int calculateNonTaxable(boolean married, int children) {
        int nti = BASE_NON_TAXABLE;
        if (married) {
            nti += MARRIAGE_ALLOWANCE + (children * CHILD_ALLOWANCE);
        }
        return nti;
    }

    private static int calculateFinalTax(int taxableIncome) {
        int rawTax = (int) Math.round(TAX_RATE * taxableIncome);
        return Math.max(rawTax, 0);
    }
}
