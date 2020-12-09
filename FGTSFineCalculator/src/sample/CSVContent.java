package sample;

public class CSVContent {

    private String month;
    private double monthValue;
    private double monthValueWithFine;
    private double totalAmount;

    public CSVContent(String month, double monthValue, double monthValueWithFine, double totalAmount) {
        this.month = month;
        this.monthValue = monthValue;
        this.monthValueWithFine = monthValueWithFine;
        this.totalAmount = totalAmount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(double monthValue) {
        this.monthValue = monthValue;
    }

    public double getMonthValueWithFine() {
        return monthValueWithFine;
    }

    public void setMonthValueWithFine(double monthValueWithFine) {
        this.monthValueWithFine = monthValueWithFine;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
