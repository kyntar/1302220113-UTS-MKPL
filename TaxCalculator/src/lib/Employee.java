package lib;

import java.time.LocalDate;
import java.time.Month;
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
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			monthlySalary = 3000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		}else if (grade == 2) {
			monthlySalary = 5000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		}else if (grade == 3) {
			monthlySalary = 7000000;
			if (isForeigner) {
				monthlySalary = (int) (3000000 * 1.5);
			}
		}
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
        // Hitung berapa bulan bekerja di tahun ini
        LocalDate now = LocalDate.now();
        int monthWorkingInYear = (now.getYear() == yearJoined)
            ? now.getMonthValue() - monthJoined
            : 12;
        
        // Boolean untuk status menikah
        boolean isMarried = (spouseIdNumber != null && !spouseIdNumber.isEmpty());
        // Jumlah anak
        int childCount  = children.size();
        
        return TaxFunction.calculateTax(
            monthlySalary,
            otherMonthlyIncome,
            monthWorkingInYear,
            annualDeductible,
            isMarried,
            childCount
        );
    }
}
