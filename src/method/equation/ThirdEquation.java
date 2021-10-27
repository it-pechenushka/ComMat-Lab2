package method.equation;

public class ThirdEquation implements Equation{
    @Override
    public double getEquationResult(double x) {
        return Math.sin(x);
    }
}
