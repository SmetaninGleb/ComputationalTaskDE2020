package ErrorsCalculations;

import CalculationMethods.ACalculationMethod;
import CalculationMethods.AnalyticalMethod;
import Models.Grid;
import Models.Point;

public class MaximumsGTECalculator {
    private ACalculationMethod calcMethod;
    private AnalyticalMethod analyticalMethod;
    private Grid resultGrid;
    private Point initialPoint;
    private Double lastXCoordinate;
    private Integer startNumberOfPoint, lastNumberOfPoint;
    private Boolean wasGridCalculated = false;

    public MaximumsGTECalculator(ACalculationMethod calcMethod, AnalyticalMethod analyticalMethod, Integer startNumberOfPoint,
                                 Integer lastNumberOfPoint, Point initialPoint, Double lastXCoordinate) {
        this.calcMethod = calcMethod;
        this.startNumberOfPoint = startNumberOfPoint;
        this.lastNumberOfPoint = lastNumberOfPoint;
        this.initialPoint = initialPoint;
        this.lastXCoordinate = lastXCoordinate;
        this.analyticalMethod = analyticalMethod;
        resultGrid = new Grid();
    }

    public Grid getResultGrid() {
        if (!wasGridCalculated) {
            calculateGrid();
            wasGridCalculated = true;
        }
        return resultGrid;
    }

    private void calculateGrid() {
        for (Integer numOfPtn = startNumberOfPoint; numOfPtn <= lastNumberOfPoint; numOfPtn++) {
            calcMethod.updateInitialValues(initialPoint, lastXCoordinate, numOfPtn);
            analyticalMethod.updateInitialValues(initialPoint, lastXCoordinate, numOfPtn);
            Grid curErrorGrid = new GTECalculator(analyticalMethod.getResultGrid(), calcMethod.getResultGrid()).getErrorGrid();
            Double maxError = 0.0;
            for (int gridPtnNum = 0; gridPtnNum < curErrorGrid.getPointList().size(); gridPtnNum++) {
                if (curErrorGrid.getPointList().get(gridPtnNum).getY() > maxError) {
                    maxError = curErrorGrid.getPointList().get(gridPtnNum).getY();
                }
            }
            resultGrid.addPoint(new Point(Double.valueOf(numOfPtn), maxError));
        }
    }
}
