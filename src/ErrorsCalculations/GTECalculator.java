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
        for (int _pointNumber = 0; _pointNumber < _exactList.size(); _pointNumber++) {
            if (_exactList.get(_pointNumber).getX().equals(_methodList.get(_pointNumber).getX())) {
                errorGrid.addPoint(new Point(_exactList.get(_pointNumber).getX(),
                        abs(_exactList.get(_pointNumber).getY() - _methodList.get(_pointNumber).getY())));
            }
        }
    }
}
