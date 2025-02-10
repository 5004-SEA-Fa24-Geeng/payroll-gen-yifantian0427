package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalaryEmployee extends Employee {

    public SalaryEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    @Override
    public String getEmployeeType() {
        return "SALARY";
    }

    @Override
    protected double calculateGrossPay(double hoursWorked) {
        return round(getPayRate() / 24); // Bi-monthly salary (24 pay periods per year)
    }

    private double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
