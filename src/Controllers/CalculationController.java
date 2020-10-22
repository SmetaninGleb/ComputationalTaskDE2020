package Controllers;

import CalculationMethods.*;
import ErrorsCalculations.GTECalculator;
import ErrorsCalculations.MaximumsGTECalculator;
import Models.Grid;
import Models.Point;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class CalculationController {

    public ArrayList<XYChart.Series> getSeriesListOfMaximumErrors(Integer startNumberOfPoint, Integer lastNumberOfPoints,
                                                                  Point initialPoint, Double lastXCoordinate,
                                                                  Boolean euler, Boolean improvedEuler, Boolean rungeKutta, Boolean analytical,
                                                                  Boolean gte, Boolean lte) {
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<>();
        if (gte && euler) {
            XYChart.Series maxGteEulerSeries = getMaximumGTESeries(new MaximumsGTECalculator(new EulerMethod(initialPoint, lastXCoordinate, startNumberOfPoint),
                    new AnalyticalMethod(initialPoint, lastXCoordinate, startNumberOfPoint),
                    startNumberOfPoint, lastNumberOfPoints, initialPoint, lastXCoordinate));
            maxGteEulerSeries.setName("Euler Method");
            seriesArrayList.add(maxGteEulerSeries);
        }
        if (gte && improvedEuler) {
            XYChart.Series maxGteImprovedEulerSeries = getMaximumGTESeries(new MaximumsGTECalculator(new ImprovedEulerMethod(initialPoint, lastXCoordinate, startNumberOfPoint),
                    new AnalyticalMethod(initialPoint, lastXCoordinate, startNumberOfPoint),
                    startNumberOfPoint, lastNumberOfPoints, initialPoint, lastXCoordinate));
            maxGteImprovedEulerSeries.setName("Improved Euler Method");
            seriesArrayList.add(maxGteImprovedEulerSeries);
        }
        if (gte && rungeKutta) {
            XYChart.Series maxGteRungeKuttaSeries = getMaximumGTESeries(new MaximumsGTECalculator(new RungeKuttaMethod(initialPoint, lastXCoordinate, startNumberOfPoint),
                    new AnalyticalMethod(initialPoint, lastXCoordinate, startNumberOfPoint),
                    startNumberOfPoint, lastNumberOfPoints, initialPoint, lastXCoordinate));
            maxGteRungeKuttaSeries.setName("Runge-Kutta Method");
            seriesArrayList.add(maxGteRungeKuttaSeries);
        }
        return seriesArrayList;
    }

    private XYChart.Series getMaximumGTESeries(MaximumsGTECalculator calculator) {
        Grid gridMaxGTE = calculator.getResultGrid();
        XYChart.Series gteMaxSeries = gridToSeries(gridMaxGTE);
        return gteMaxSeries;
    }

    public ArrayList<XYChart.Series> getSeriesListOfErrors(Point initialPoint, Double lastXCoordinate, Integer numberOfPoints,
                                                           Boolean euler, Boolean improvedEuler, Boolean rungeKutta, Boolean analytical,
                                                           Boolean gte, Boolean lte) {
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<>();
        if (gte && euler) {
            XYChart.Series gteEulerSeries = getGTESeries(new GTECalculator(new AnalyticalMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid(),
                    new EulerMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid()));
            gteEulerSeries.setName("Euler Method Errors");
            seriesArrayList.add(gteEulerSeries);
        }
        if (gte && improvedEuler) {
            XYChart.Series gteImprovedEulerSeries = getGTESeries(new GTECalculator(new AnalyticalMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid(),
                    new ImprovedEulerMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid()));
            gteImprovedEulerSeries.setName("Improved Euler Method Errors");
            seriesArrayList.add(gteImprovedEulerSeries);
        }
        if (gte && rungeKutta) {
            XYChart.Series gteRungeKuttaMethod = getGTESeries(new GTECalculator(new AnalyticalMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid(),
                    new RungeKuttaMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid()));
            gteRungeKuttaMethod.setName("Runge-Kutta Method Errors");
            seriesArrayList.add(gteRungeKuttaMethod);
        }
        return seriesArrayList;
    }

    private XYChart.Series getGTESeries(GTECalculator gteCalculator) {
        Grid gridGTE = gteCalculator.getErrorGrid();
        XYChart.Series gteSeries = gridToSeries(gridGTE);
        return gteSeries;
    }

    public ArrayList<XYChart.Series> getSeriesListOfMethods(Point initialPoint, Double lastXCoordinate, Integer numberOfPoints,
                                                            Boolean euler, Boolean improvedEuler, Boolean rungeKutta, Boolean analytical) {
        System.out.println("----------------------------------");//TODO
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<>();
        if (euler) {
            XYChart.Series eulerSeries = getMethodSeries(new EulerMethod(initialPoint, lastXCoordinate, numberOfPoints));
            eulerSeries.setName("Euler Method");
            seriesArrayList.add(eulerSeries);
        }
        if (improvedEuler) {
            XYChart.Series improvedEulerSeries = getMethodSeries(new ImprovedEulerMethod(initialPoint, lastXCoordinate, numberOfPoints));
            improvedEulerSeries.setName("Improved Euler Method");
            seriesArrayList.add(improvedEulerSeries);
        }
        if (rungeKutta) {
            XYChart.Series rungeKuttaSeries = getMethodSeries(new RungeKuttaMethod(initialPoint, lastXCoordinate, numberOfPoints));
            rungeKuttaSeries.setName("Runge-Kutta Method");
            seriesArrayList.add(rungeKuttaSeries);
        }
        if (analytical) {
            XYChart.Series analyticalSeries = getMethodSeries(new AnalyticalMethod(initialPoint, lastXCoordinate, numberOfPoints));
            analyticalSeries.setName("Analytical Method");
            seriesArrayList.add(analyticalSeries);
        }
        System.out.println("----------------------------------");//TODO
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
            System.out.println(i + ". " + grid.getPointList().get(i).getX().toString() + " " + grid.getPointList().get(i).getY().toString());//TODO
        }
        System.out.println();//TODO
        return newSeries;
    }
}
