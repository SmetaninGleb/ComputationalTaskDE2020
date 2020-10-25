package CalculationMethods;

import Models.Point;

public class ImprovedEulerMethod extends ACalculationMethod {

    public ImprovedEulerMethod(Point initialPoint, Double lastXCoordinate, Integer numberOfPoints) {
        super(initialPoint, lastXCoordinate, numberOfPoints);
    }

    @Override
    public Point iterationCalculation(Point previousPoint) {
        Double step = (getLastXCoordinate() - getInitialPoint().getX()) / (getNumberOfPoints() - 1);
        Double _firstCoefficient = getMainFunctionResult(previousPoint);
        Double _secondCoefficient = getMainFunctionResult(new Point(previousPoint.getX() + step, previousPoint.getY() + step * _firstCoefficient));
        return new Point(previousPoint.getX() + step, previousPoint.getY() + step / 2 * (_firstCoefficient + _secondCoefficient));
    }
}
