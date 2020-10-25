package Controllers;

import CalculationMethods.*;
import ErrorsCalculations.GTECalculator;
import ErrorsCalculations.LTECalculator;
import ErrorsCalculations.MaximumsErrorCalculator;
import Models.Grid;
import Models.Point;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class CalculationController {

    public ArrayList<XYChart.Series> getSeriesListOfMaximumErrors(Integer startNumberOfPoint, Integer lastNumberOfPoints,
                                                                  Point initialPoint, Double lastXCoordinate,
                                                                  Boolean euler, Boolean improvedEuler, Boolean rungeKutta,
                                                                  Boolean gte, Boolean lte) {
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<>();
        if (euler && (gte || lte)) {
            EulerMethod eulerMethod = new EulerMethod(initialPoint, lastXCoordinate, startNumberOfPoint);
            AnalyticalMethod analyticalMethod = new AnalyticalMethod(initialPoint, lastXCoordinate, startNumberOfPoint);
            MaximumsErrorCalculator calc = new MaximumsErrorCalculator(eulerMethod, analyticalMethod,
                    startNumberOfPoint, lastNumberOfPoints, initialPoint, lastXCoordinate);
            if (gte) {
                XYChart.Series maxGteEulerSeries = getMaximumGTESeries(calc);
                maxGteEulerSeries.setName("Euler Method (GTE)");
                seriesArrayList.add(maxGteEulerSeries);
            }
            if (lte) {
                XYChart.Series maxLteEulerSeries = getMaximumLTESeries(calc);
                maxLteEulerSeries.setName("Euler Method (LTE)");
                seriesArrayList.add(maxLteEulerSeries);
            }
        }
        if (improvedEuler && (gte || lte)) {
            ImprovedEulerMethod improvedEulerMethod = new ImprovedEulerMethod(initialPoint, lastXCoordinate, startNumberOfPoint);
            AnalyticalMethod analyticalMethod = new AnalyticalMethod(initialPoint, lastXCoordinate, startNumberOfPoint);
            MaximumsErrorCalculator calc = new MaximumsErrorCalculator(improvedEulerMethod, analyticalMethod,
                    startNumberOfPoint, lastNumberOfPoints, initialPoint, lastXCoordinate);
            if (gte) {
                XYChart.Series maxGteImprovedEulerSeries = getMaximumGTESeries(calc);
                maxGteImprovedEulerSeries.setName("Improved Euler Method (GTE)");
                seriesArrayList.add(maxGteImprovedEulerSeries);
            }
            if (lte) {
                XYChart.Series maxLteImprovedEulerSeries = getMaximumLTESeries(calc);
                maxLteImprovedEulerSeries.setName("Improved Euler Method (LTE)");
                seriesArrayList.add(maxLteImprovedEulerSeries);
            }
        }
        if (rungeKutta && (gte || lte)) {
            RungeKuttaMethod rungeKuttaMethod = new RungeKuttaMethod(initialPoint, lastXCoordinate, startNumberOfPoint);
            AnalyticalMethod analyticalMethod = new AnalyticalMethod(initialPoint, lastXCoordinate, startNumberOfPoint);
            MaximumsErrorCalculator calc = new MaximumsErrorCalculator(rungeKuttaMethod, analyticalMethod,
                    startNumberOfPoint, lastNumberOfPoints, initialPoint, lastXCoordinate);
            if (gte) {
                XYChart.Series maxGteRungeKuttaSeries = getMaximumGTESeries(calc);
                maxGteRungeKuttaSeries.setName("Runge-Kutta Method (GTE)");
                seriesArrayList.add(maxGteRungeKuttaSeries);
            }
            if (lte) {
                XYChart.Series maxLteRungeKuttaSeries = getMaximumLTESeries(calc);
                maxLteRungeKuttaSeries.setName("Runge-Kutta Method (LTE)");
                seriesArrayList.add(maxLteRungeKuttaSeries);
            }
        }
        return seriesArrayList;
    }

    private XYChart.Series getMaximumLTESeries(MaximumsErrorCalculator calculator) {
        Grid gridMaxLte = calculator.getLTEResultGrid();
        return gridToSeries(gridMaxLte);
    }

    private XYChart.Series getMaximumGTESeries(MaximumsErrorCalculator calculator) {
        Grid gridMaxGTE = calculator.getGTEResultGrid();
        return gridToSeries(gridMaxGTE);
    }

    public ArrayList<XYChart.Series> getSeriesListOfErrors(Point initialPoint, Double lastXCoordinate, Integer numberOfPoints,
                                                           Boolean euler, Boolean improvedEuler, Boolean rungeKutta, Boolean analytical,
                                                           Boolean gte, Boolean lte) {
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<>();
        if (gte && euler) {
            Grid _exactSolution = new AnalyticalMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid();
            Grid _eMethod = new EulerMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid();
            GTECalculator calc = new GTECalculator(_exactSolution, _eMethod);
            XYChart.Series gteEulerSeries = getGTESeries(calc);
            gteEulerSeries.setName("Euler Method GTE");
            seriesArrayList.add(gteEulerSeries);
        }
        if (gte && improvedEuler) {
            Grid _exactSolution = new AnalyticalMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid();
            Grid _ieMethod = new ImprovedEulerMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid();
            GTECalculator calc = new GTECalculator(_exactSolution, _ieMethod);
            XYChart.Series gteImprovedEulerSeries = getGTESeries(calc);
            gteImprovedEulerSeries.setName("Improved Euler Method GTE");
            seriesArrayList.add(gteImprovedEulerSeries);
        }
        if (gte && rungeKutta) {
            Grid _exactSolution = new AnalyticalMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid();
            Grid _rkMethod = new RungeKuttaMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid();
            GTECalculator calc = new GTECalculator(_exactSolution, _rkMethod);
            XYChart.Series gteRungeKuttaMethod = getGTESeries(calc);
            gteRungeKuttaMethod.setName("Runge-Kutta Method GTE");
            seriesArrayList.add(gteRungeKuttaMethod);
        }
        if (lte && euler) {
            Grid _exactSolution = new AnalyticalMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid();
            EulerMethod _eMethod = new EulerMethod(initialPoint, lastXCoordinate, numberOfPoints);
            LTECalculator calc = new LTECalculator(_eMethod, _exactSolution);
            XYChart.Series lteEulerMethod = getLTESeries(calc);
            lteEulerMethod.setName("Euler Method LTE");
            seriesArrayList.add(lteEulerMethod);
        }
        if (lte && improvedEuler) {
            Grid _exactSolution = new AnalyticalMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid();
            ImprovedEulerMethod _ieMethod = new ImprovedEulerMethod(initialPoint, lastXCoordinate, numberOfPoints);
            LTECalculator calc = new LTECalculator(_ieMethod, _exactSolution);
            XYChart.Series lteImprovedEulerMethod = getLTESeries(calc);
            lteImprovedEulerMethod.setName("Improved Euler Method LTE");
            seriesArrayList.add(lteImprovedEulerMethod);
        }
        if (lte && rungeKutta) {
            Grid _exactSolution = new AnalyticalMethod(initialPoint, lastXCoordinate, numberOfPoints).getResultGrid();
            RungeKuttaMethod _rkMethod = new RungeKuttaMethod(initialPoint, lastXCoordinate, numberOfPoints);
            LTECalculator calc = new LTECalculator(_rkMethod, _exactSolution);
            XYChart.Series lteRungeKuttaMethod = getLTESeries(calc);
            lteRungeKuttaMethod.setName("Runge-Kutta Method LTE");
            seriesArrayList.add(lteRungeKuttaMethod);
        }
        return seriesArrayList;
    }

    private XYChart.Series getLTESeries(LTECalculator lteCalculator) {
        Grid gridLTE = lteCalculator.getErrorGrid();
        return gridToSeries(gridLTE);
    }

    private XYChart.Series getGTESeries(GTECalculator gteCalculator) {
        Grid gridGTE = gteCalculator.getErrorGrid();
        return gridToSeries(gridGTE);
    }

    public ArrayList<XYChart.Series> getSeriesListOfMethods(Point initialPoint, Double lastXCoordinate, Integer numberOfPoints,
                                                            Boolean euler, Boolean improvedEuler, Boolean rungeKutta, Boolean analytical) {
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
        return seriesArrayList;
    }

    private XYChart.Series getMethodSeries(ACalculationMethod method) {
        Grid methodGrid = method.getResultGrid();
        return gridToSeries(methodGrid);
    }

    private XYChart.Series gridToSeries(Grid grid) {
        XYChart.Series newSeries = new XYChart.Series();
        for (int i = 0; i < grid.getPointList().size(); i++) {
            newSeries.getData().add(new XYChart.Data(grid.getPointList().get(i).getX(), grid.getPointList().get(i).getY()));
        }
        return newSeries;
    }
}
