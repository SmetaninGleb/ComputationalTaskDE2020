package ErrorsCalculations;

import CalculationMethods.ACalculationMethod;
import CalculationMethods.AnalyticalMethod;
import Models.Grid;
import Models.Point;

public class MaximumsErrorCalculator {
    private ACalculationMethod calcMethod;
    private AnalyticalMethod analyticalMethod;
    private Grid resultGTEGrid;
    private Grid resultLTEGrid;
    private Point initialPoint;
    private Double lastXCoordinate;
    private Integer startNumberOfPoint, lastNumberOfPoint;
    private Boolean wasGTEGridCalculated = false;
    private Boolean wasLTEGridCalculated = false;

    public MaximumsErrorCalculator(ACalculationMethod calcMethod, AnalyticalMethod analyticalMethod, Integer startNumberOfPoint,
                                   Integer lastNumberOfPoint, Point initialPoint, Double lastXCoordinate) {
        this.calcMethod = calcMethod;
        this.startNumberOfPoint = startNumberOfPoint;
        this.lastNumberOfPoint = lastNumberOfPoint;
        this.initialPoint = initialPoint;
        this.lastXCoordinate = lastXCoordinate;
        this.analyticalMethod = analyticalMethod;
        resultGTEGrid = new Grid();
        resultLTEGrid = new Grid();
    }

    public Grid getLTEResultGrid() {
        if (!wasLTEGridCalculated) {
            calculateLTEGrid();
            wasLTEGridCalculated = true;
        }
        return resultLTEGrid;
    }

    private void calculateLTEGrid() {
        for (int numOfPtn = startNumberOfPoint; numOfPtn <= lastNumberOfPoint; numOfPtn++) {
            updateMethods(numOfPtn);
            Grid curErrorGrid = new LTECalculator(calcMethod, analyticalMethod.getResultGrid()).getErrorGrid();
            addMaxError(numOfPtn, curErrorGrid, resultLTEGrid);
        }
    }

    public Grid getGTEResultGrid() {
        if (!wasGTEGridCalculated) {
            calculateGTEGrid();
            wasGTEGridCalculated = true;
        }
        return resultGTEGrid;
    }

    private void calculateGTEGrid() {
        for (int numOfPtn = startNumberOfPoint; numOfPtn <= lastNumberOfPoint; numOfPtn++) {
            updateMethods(numOfPtn);
            Grid curErrorGrid = new GTECalculator(analyticalMethod.getResultGrid(), calcMethod.getResultGrid()).getErrorGrid();
            addMaxError(numOfPtn, curErrorGrid, resultGTEGrid);
        }
    }

    private void updateMethods(Integer numberOfPoints) {
        calcMethod.updateInitialValues(initialPoint, lastXCoordinate, numberOfPoints);
        analyticalMethod.updateInitialValues(initialPoint, lastXCoordinate, numberOfPoints);
    }

    private void addMaxError(Integer numOfPtn, Grid errorGrid, Grid resultGrid) {
        Double maxError = 0.0;
        for (int gridPtnNum = 0; gridPtnNum < errorGrid.getPointList().size(); gridPtnNum++) {
            if (errorGrid.getPointList().get(gridPtnNum).getY() > maxError) {
                maxError = errorGrid.getPointList().get(gridPtnNum).getY();
            }
        }
        resultGrid.addPoint(new Point(Double.valueOf(numOfPtn), maxError));
    }
}
