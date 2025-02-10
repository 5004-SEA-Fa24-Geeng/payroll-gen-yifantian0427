package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PayStub implements IPayStub {
    private IEmployee employee;
    private double netPay;
    private double taxesPaid;
    private double ytdEarnings;
    private double ytdTaxesPaid;

    public PayStub(IEmployee employee, double netPay, double taxesPaid, double ytdEarnings, double ytdTaxesPaid) {
        this.employee = employee;
        this.netPay = round(netPay);
        this.taxesPaid = round(taxesPaid);
        this.ytdEarnings = round(ytdEarnings);
        this.ytdTaxesPaid = round(ytdTaxesPaid);
    }

    @Override
    public double getPay() {
        return netPay;
    }

    @Override
    public double getTaxesPaid() {
        return taxesPaid;
    }

    @Override
    public String toCSV() {
        return String.format("%s,%.2f,%.2f,%.2f,%.2f",
                employee.getName(), netPay, taxesPaid, ytdEarnings, ytdTaxesPaid);
    }

    private double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
