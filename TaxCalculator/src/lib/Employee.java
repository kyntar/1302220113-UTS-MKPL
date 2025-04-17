package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;
    
    private int yearJoined;
    private int monthJoined;
    private int dayJoined;
    
    private boolean isForeigner;
    private boolean gender; // true = Laki-laki, false = Perempuan
    
    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;
    
    private String spouseName;
    private String spouseIdNumber;

	private List<Child> children;
	
	public Employee(String employeeId,
                    String firstName,
                    String lastName,
                    String idNumber,
                    String address,
                    int yearJoined,
                    int monthJoined,
                    int dayJoined,
                    boolean isForeigner,
                    boolean gender) {
        this.employeeId   = employeeId;
        this.firstName    = firstName;
        this.lastName     = lastName;
        this.idNumber     = idNumber;
        this.address      = address;
        this.yearJoined   = yearJoined;
        this.monthJoined  = monthJoined;
        this.dayJoined    = dayJoined;
        this.isForeigner  = isForeigner;
        this.gender       = gender;
        
        this.children = new LinkedList<>();
    }
	private int calculateMonthsWorkedThisYear() {
        LocalDate now = LocalDate.now();
        if (now.getYear() == yearJoined) {
            return Math.max(1, now.getMonthValue() - monthJoined);
        }
        return 12;
    }
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	 public void setMonthlySalary(int grade) {
        int base;
        switch (grade) {
            case 1 -> base = 3_000_000;
            case 2 -> base = 5_000_000;
            case 3 -> base = 7_000_000;
            default -> throw new IllegalArgumentException("Invalid grade: " + grade);
        }
        if (isForeigner) {
            base = (int)(base * 1.5);
        }
        this.monthlySalary = base;
    }
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber  = spouseIdNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		children.add(new Child(childName, childIdNumber));
	}
	
	public int getAnnualIncomeTax() {
        int monthsWorked = calculateMonthsWorkedThisYear();
        boolean isMarried = (spouseIdNumber != null && !spouseIdNumber.isEmpty());
        int childCount   = children.size();

        return TaxFunction.calculateTax(
            monthlySalary,
            otherMonthlyIncome,
            monthsWorked,
            annualDeductible,
            isMarried,
            childCount
        );
    }
}
