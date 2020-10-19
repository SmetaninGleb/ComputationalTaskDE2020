package Controllers;

import CalculationMethods.*;
import ErrorsCalculations.GTECalculator;
import Models.Grid;
import Models.Point;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class CalculationController {

    public ArrayList<XYChart.Series> getSeriesListOfErrors (Point initialPoint, Double lastXCoordinate, Integer numberOfPoints) {
        XYChart.Series gteEulerSeries = getGTESeries(new GTECalculator(new AnalyticalMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid(),
                new EulerMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid()));
        gteEulerSeries.setName("Euler Method Errors");
        XYChart.Series gteImprovedEulerSeries = getGTESeries(new GTECalculator(new AnalyticalMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid(),
                new ImprovedEulerMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid()));
        gteImprovedEulerSeries.setName("Improved Euler Method Errors");
        XYChart.Series gteRungeKuttaMethod = getGTESeries(new GTECalculator(new AnalyticalMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid(),
                new RungeKuttaMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid()));
        gteRungeKuttaMethod.setName("Runge-Kutta Method Errors");
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<>();
        seriesArrayList.add(gteEulerSeries);
        seriesArrayList.add(gteImprovedEulerSeries);
        seriesArrayList.add(gteRungeKuttaMethod);
        return seriesArrayList;
    }

    public XYChart.Series getGTESeries (GTECalculator gteCalculator) {
        Grid gridGTE = gteCalculator.getErrorGrid();
        XYChart.Series gteSeries = gridToSeries(gridGTE);
        return gteSeries;
    }

    public ArrayList<XYChart.Series> getSeriesListOfMethods (Point initialPoint, Double lastXCoordinate, Integer numberOfPoints) {
        XYChart.Series eulerSeries = getMethodSeries(new EulerMethod(initialPoint, lastXCoordinate, numberOfPoints));
        eulerSeries.setName("Euler Method");
        XYChart.Series improvedEulerSeries = getMethodSeries(new ImprovedEulerMethod(initialPoint, lastXCoordinate, numberOfPoints));
        improvedEulerSeries.setName("Improved Euler Method");
        XYChart.Series rungeKuttaSeries  = getMethodSeries(new RungeKuttaMethod(initialPoint, lastXCoordinate, numberOfPoints));
        rungeKuttaSeries.setName("Runge-Kutta Method");
        XYChart.Series analyticalSeries = getMethodSeries(new AnalyticalMethod(initialPoint, lastXCoordinate, numberOfPoints));
        analyticalSeries.setName("Analytical Method");
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<>();
        seriesArrayList.add(eulerSeries);
        seriesArrayList.add(improvedEulerSeries);
        seriesArrayList.add(rungeKuttaSeries);
        seriesArrayList.add(analyticalSeries);
        return seriesArrayList;
    }

    private XYChart.Series getMethodSeries(ACalculationMethod method) {
        Grid methodGrid = method.getResultGrid();
        XYChart.Series methodSeries = gridToSeries(methodGrid);
        return methodSeries;
    }

    private XYChart.Series gridToSeries(Grid grid) {
        XYChart.Series newSeries = new XYChart.Series();
        for (int i = 0; i < grid.getPointList().size(); i++) {
            newSeries.getData().add(new XYChart.Data(grid.getPointList().get(i).getX(), grid.getPointList().get(i).getY()));
        }
        return newSeries;
    }
}
