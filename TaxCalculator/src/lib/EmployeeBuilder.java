package lib;

import java.util.LinkedList;
import java.util.List;

public class EmployeeBuilder {
    // Field wajib
    String employeeId;
    String firstName;
    String lastName;
    String idNumber;
    String address;
    int yearJoined;
    int monthJoined;
    int dayJoined;
    boolean isForeigner;
    boolean gender;
    
    // Field opsional dengan default
    int monthlySalary = 0;
    int otherMonthlyIncome = 0;
    int annualDeductible = 0;
    String spouseName = "";
    String spouseIdNumber = "";
    List<Child> children = new LinkedList<>();
    
    public EmployeeBuilder(String employeeId,
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
    }
    
    public EmployeeBuilder monthlySalary(int grade) {
        // Anda bisa memanggil kembali logika setMonthlySalary yang refactored
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
        return this;
    }
    
    public EmployeeBuilder otherMonthlyIncome(int income) {
        this.otherMonthlyIncome = income;
        return this;
    }
    
    public EmployeeBuilder annualDeductible(int deductible) {
        this.annualDeductible = deductible;
        return this;
    }
    
    public EmployeeBuilder spouse(String spouseName, String spouseIdNumber) {
        this.spouseName      = spouseName;
        this.spouseIdNumber  = spouseIdNumber;
        return this;
    }
    
    public EmployeeBuilder addChild(String name, String idNum) {
        this.children.add(new Child(name, idNum));
        return this;
    }
    
    public Employee build() {
        return new Employee(this);
    }
}
