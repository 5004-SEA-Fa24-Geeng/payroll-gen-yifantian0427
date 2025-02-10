package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HourlyEmployee extends Employee {

    public HourlyEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    @Override
    public String getEmployeeType() {
        return "HOURLY";
    }

    @Override
    protected double calculateGrossPay(double hoursWorked) {
        if (hoursWorked <= 40) {
            return round(hoursWorked * getPayRate());
        } else {
            double regularPay = 40 * getPayRate();
            double overtimePay = (hoursWorked - 40) * getPayRate() * 1.5;
            return round(regularPay + overtimePay);
        }
    }

    private double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
