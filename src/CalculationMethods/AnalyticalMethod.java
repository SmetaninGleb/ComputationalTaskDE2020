package CalculationMethods;

import Models.Grid;
import Models.Point;

public class AnalyticalMethod extends ACalculationMethod {

    public AnalyticalMethod(Point initialPoint, Double lastXCoordinate, Integer numberOfPoints) {
        super(initialPoint, lastXCoordinate, numberOfPoints);
    }

    /*
    y' = (y^2 - y) / x, y(1) = 0.5 => y = 1 / (x + 1)
     */
    @Override
    protected Double getMainFunctionResult(Point point) {
        return 1 / (point.getX() + 1);
    }

    @Override
    protected Point iterationCalculation(Point previousPoint) {
        Double _step = (getLastXCoordinate() - getInitialPoint().getX()) / getNumberOfPoints();
        return new Point(previousPoint.getX() + _step, getMainFunctionResult(new Point(previousPoint.getX() + _step, previousPoint.getY())));
    }
}
