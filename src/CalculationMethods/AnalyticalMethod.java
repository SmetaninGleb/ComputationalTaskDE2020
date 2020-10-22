package CalculationMethods;

import Models.Point;

public class AnalyticalMethod extends ACalculationMethod {

    public AnalyticalMethod(Point initialPoint, Double lastXCoordinate, Integer numberOfPoints) {
        super(initialPoint, lastXCoordinate, numberOfPoints);
    }

    /*
    y' = (y^2 - y) / x, y(1) = 0.5 => y = 1 / (Сx + 1) => C = (1 / y - 1) / x
     */
    @Override
    protected Double getMainFunctionResult(Point point) {
        Double coefficient = (1 / getInitialPoint().getY() - 1) / getInitialPoint().getX();
        return 1.0 / (coefficient * point.getX() + 1);
    }

    @Override
    protected Point iterationCalculation(Point previousPoint, Double step) {
        return new Point(previousPoint.getX() + step, getMainFunctionResult(new Point(previousPoint.getX() + step, previousPoint.getY())));
    }
}
