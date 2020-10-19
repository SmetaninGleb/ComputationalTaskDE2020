package CalculationMethods;

import Models.Grid;
import Models.Point;

public class ImprovedEulerMethod extends ACalculationMethod {

    public ImprovedEulerMethod(Point initialPoint, Double lastXCoordinate, Integer numberOfPoints) {
        super(initialPoint, lastXCoordinate, numberOfPoints);
    }

    @Override
    protected Point iterationCalculation(Point previousPoint) {
        Double _step = (getLastXCoordinate() - getInitialPoint().getX()) / getNumberOfPoints();
        Double _firstCoefficient = getMainFunctionResult(previousPoint);
        Double _secondCoefficient = getMainFunctionResult(new Point(previousPoint.getX() + _step, previousPoint.getY() + _step * _firstCoefficient));
        return new Point(previousPoint.getX() + _step, previousPoint.getY() + _step / 2 * (_firstCoefficient + _secondCoefficient));
    }
}
