package ErrorsCalculations;

import Models.Grid;
import Models.Point;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class GTECalculator implements IErrorCalculator {
    private Grid exactSolution;
    private Grid solutionByMethod;
    private Grid errorGrid;
    private Boolean wasErrorCalculated = false;

    public GTECalculator(Grid exactSolution, Grid solutionByMethod) {
        this.exactSolution = exactSolution;
        this.solutionByMethod = solutionByMethod;
        errorGrid = new Grid();
    }

    @Override
    public Grid getErrorGrid() {
        if (!wasErrorCalculated) {
            calculateError();
            wasErrorCalculated = true;
        }
        return errorGrid;
    }

    private void calculateError() {
        ArrayList<Point> _exactList = exactSolution.getPointList();
        ArrayList<Point> _methodList = solutionByMethod.getPointList();
        for (int _exactPointNumber = 0; _exactPointNumber < _exactList.size(); _exactPointNumber++) {
            for (int _methodPointNumber = 0; _methodPointNumber < _methodList.size(); _methodPointNumber++) {
                if (_exactList.get(_exactPointNumber).getX().equals(_methodList.get(_methodPointNumber).getX())) {
                    errorGrid.addPoint(new Point(_exactList.get(_exactPointNumber).getX(),
                            abs(_exactList.get(_exactPointNumber).getY() - _methodList.get(_methodPointNumber).getY())));
                }
            }
        }
    }
}
