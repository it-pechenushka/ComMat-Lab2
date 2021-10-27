package model;

public class EquationEntity {
    public final int equationNumber;
    private double upperLimit;
    private double lowerLimit;
    public final double accuracy;

    public EquationEntity(int equationNumber, double upperLimit, double lowerLimit, double accuracy) {
        this.equationNumber = equationNumber;
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.accuracy = accuracy;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(double upperLimit) {
        this.upperLimit = upperLimit;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }
}
