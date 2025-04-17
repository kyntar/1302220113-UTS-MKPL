package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    // Field–field sama seperti sebelum refactor, tapi akses diatur via Builder
    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private int yearJoined;
    private int monthJoined;
    private int dayJoined;

    private boolean isForeigner;
    private boolean gender;          // true = Laki‑laki, false = Perempuan

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<Child> children;

    // Konstruktor package‑private: hanya bisa diakses oleh EmployeeBuilder
    Employee(EmployeeBuilder b) {
        this.employeeId         = b.employeeId;
        this.firstName          = b.firstName;
        this.lastName           = b.lastName;
        this.idNumber           = b.idNumber;
        this.address            = b.address;
        this.yearJoined         = b.yearJoined;
        this.monthJoined        = b.monthJoined;
        this.dayJoined          = b.dayJoined;
        this.isForeigner        = b.isForeigner;
        this.gender             = b.gender;
        this.monthlySalary      = b.monthlySalary;
        this.otherMonthlyIncome = b.otherMonthlyIncome;
        this.annualDeductible   = b.annualDeductible;
        this.spouseName         = b.spouseName;
        this.spouseIdNumber     = b.spouseIdNumber;
        this.children           = new LinkedList<>(b.children);
    }

    /** Lama bekerja di tahun ini (dipisah untuk clarity) */
    private int calculateMonthsWorkedThisYear() {
        LocalDate now = LocalDate.now();
        if (now.getYear() == yearJoined) {
            return Math.max(1, now.getMonthValue() - monthJoined);
        }
        return 12;
    }

    /** Hitung pajak dengan signature TaxFunction.calculateTax(..., boolean isMarried, int childCount) */
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

    // (Opsional) Getter untuk semua field jika diperlukan di luar kelas
    public String getEmployeeId()       { return employeeId; }
    public String getFirstName()        { return firstName; }
    public String getLastName()         { return lastName; }
    public String getIdNumber()         { return idNumber; }
    public String getAddress()          { return address; }
    public boolean isForeigner()        { return isForeigner; }
    public boolean isMale()             { return gender; }
    public List<Child> getChildren()    { return new LinkedList<>(children); }
    public String getSpouseName()       { return spouseName; }
    public String getSpouseIdNumber()   { return spouseIdNumber; }
}
