package CalculationMethods;

import Models.Point;

public class RungeKuttaMethod extends ACalculationMethod {

    public RungeKuttaMethod(Point initialPoint, Double lastXCoordinate, Integer numberOfPoints) {
        super(initialPoint, lastXCoordinate, numberOfPoints);
    }

    @Override
    protected Point iterationCalculation(Point previousPoint, Double step) {
        Double _coefficient1 = getMainFunctionResult(previousPoint);
        Double _coefficient2 = getMainFunctionResult(new Point(previousPoint.getX() + step / 2, previousPoint.getY() + step / 2 * _coefficient1));
        Double _coefficient3 = getMainFunctionResult(new Point(previousPoint.getX() + step / 2, previousPoint.getY() + step / 2 * _coefficient2));
        Double _coefficient4 = getMainFunctionResult(new Point(previousPoint.getX() + step, previousPoint.getY() + step * _coefficient3));
        return new Point(previousPoint.getX() + step, previousPoint.getY() + step / 6 * (_coefficient1 + 2 * _coefficient2 + 2 * _coefficient3 + _coefficient4));
    }
}
