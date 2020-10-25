package CalculationMethods;

import Models.Point;

public class EulerMethod extends ACalculationMethod {

    public EulerMethod(Point initialPoint, Double lastXCoordinate, Integer numberOfPoints) {
        super(initialPoint, lastXCoordinate, numberOfPoints);
    }

    @Override
    public Point iterationCalculation(Point previousPoint) {
        Double step = (getLastXCoordinate() - getInitialPoint().getX()) / (getNumberOfPoints() - 1);
        return new Point(previousPoint.getX() + step, previousPoint.getY() + step * getMainFunctionResult(previousPoint));
    }
}
