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

        if (hoursWorked == 0 && getEmployeeType().equals("HOURLY")) {
            return new PayStub(this, 0.0, 0.0, getYTDEarnings(), getYTDTaxesPaid());
        }

        double grossPay = calculateGrossPay(hoursWorked);
        BigDecimal netPay = BigDecimal.valueOf(grossPay)
                .subtract(BigDecimal.valueOf(pretaxDeductions))
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal taxAmount = netPay.multiply(BigDecimal.valueOf(0.2265))
                .setScale(2, RoundingMode.HALF_UP);
        netPay = netPay.subtract(taxAmount);
        double taxablePay = grossPay - pretaxDeductions;


        // Update YTD earnings and taxes
        ytdEarnings += netPay.doubleValue();
        ytdTaxesPaid += taxAmount.doubleValue();

        return new PayStub(this, netPay.doubleValue(), taxAmount.doubleValue(), ytdEarnings, ytdTaxesPaid);
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
