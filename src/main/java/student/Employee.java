package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Employee implements IEmployee {
    private String name;
    private String id;
    private double payRate;
    private double ytdEarnings;
    private double ytdTaxesPaid;
    private double pretaxDeductions;

    public Employee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public double getPayRate() {
        return payRate;
    }

    @Override
    public double getYTDEarnings() {
        return ytdEarnings;
    }

    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid;
    }

    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions;
    }

    // Abstract method to be implemented by subclasses (HourlyEmployee and SalaryEmployee)
    protected abstract double calculateGrossPay(double hoursWorked);

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null; // Skip payroll processing if negative hours
        }

        double grossPay = calculateGrossPay(hoursWorked);
        double taxablePay = grossPay - pretaxDeductions;
        double taxAmount = taxablePay * 0.2265; // 22.65% total tax rate
        double netPay = grossPay - pretaxDeductions - taxAmount;

        // Update YTD earnings and taxes
        ytdEarnings = round(ytdEarnings + grossPay);
        ytdTaxesPaid = round(ytdTaxesPaid + taxAmount);

        return new PayStub(this, netPay, taxAmount, ytdEarnings, ytdTaxesPaid);
    }

    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f",
                getEmployeeType(), name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }

    private double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
