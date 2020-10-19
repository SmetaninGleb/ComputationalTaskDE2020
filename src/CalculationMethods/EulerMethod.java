package CalculationMethods;

import Models.Grid;
import Models.Point;

import java.util.function.BiFunction;

public class EulerMethod extends ACalculationMethod {

    public EulerMethod(Point initialPoint, Double lastXCoordinate, Integer numberOfPoints) {
        super(initialPoint, lastXCoordinate, numberOfPoints);
    }

    @Override
    protected Point iterationCalculation(Point previousPoint) {
        Double _step = (getLastXCoordinate() - getInitialPoint().getX()) / getNumberOfPoints();
        return new Point(previousPoint.getX() + _step, previousPoint.getY() + getMainFunctionResult(previousPoint));
    }
}
