package CalculationMethods;

import Models.Grid;
import Models.Point;

public abstract class ACalculationMethod implements ICalculationMethod {
    private Point initialPoint;
    private Double lastXCoordinate;
    private Integer numberOfPoints;
    private Boolean wasGridCalculated = false;
    protected Grid calculatedGrid;

    public ACalculationMethod(Point initialPoint, Double lastXCoordinate, Integer numberOfPoints) {
        this.initialPoint = initialPoint;
        this.lastXCoordinate = lastXCoordinate;
        this.numberOfPoints = numberOfPoints;
        this.calculatedGrid = new Grid();
        calculatedGrid.addPoint(this.initialPoint);
    }

    public Point getInitialPoint() {
        return initialPoint;
    }

    public Double getLastXCoordinate() {
        return lastXCoordinate;
    }

    public Integer getNumberOfPoints() {
        return numberOfPoints;
    }

    /*
    y' = (y^2 - y) / x
     */
    protected Double getMainFunctionResult(Point point) {
        return (point.getY() * point.getY() - point.getY()) / point.getX();
    }

    protected void calculateGrid() {
        for (int pointNumber = 1; pointNumber < getNumberOfPoints(); pointNumber++) {
            calculatedGrid.addPoint(iterationCalculation(calculatedGrid.getPointList().get(pointNumber - 1)));
        }
    }

    protected abstract Point iterationCalculation(Point previousPoint);

    @Override
    public Grid getResultGrid() {
        if (!wasGridCalculated) {
            calculateGrid();
            wasGridCalculated = true;
        }
        return calculatedGrid;
    }

    public void updateInitialValues(Point initialPoint, Double lastXCoordinate, Integer numberOfPoints) {
        wasGridCalculated = false;
        this.initialPoint = initialPoint;
        this.lastXCoordinate = lastXCoordinate;
        this.numberOfPoints = numberOfPoints;
    }
}
