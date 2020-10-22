package Controllers;

import Models.Point;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class MainInterfaceController {
    private Boolean wasMethodsChartCalibrated = false;
    private Boolean wasErrorsChartCalibrated = false;
    private Boolean wasMaximumErrorsChartCalibrated = false;
    private CalculationController calculationController;
    @FXML
    private TextField x0TextField;
    @FXML
    private TextField y0TextField;
    @FXML
    private TextField XTextField;
    @FXML
    private TextField NTab1TextField;
    @FXML
    private TextField n0TextField;
    @FXML
    private TextField NTab2TextField;
    @FXML
    private LineChart<Double, Double> methodsChart;
    @FXML
    private LineChart<Double, Double> errorsChart;
    @FXML
    private LineChart<Double, Double> maximumErrorsChart;
    @FXML
    private CheckBox analyticalMethodChB;
    @FXML
    private CheckBox eulerMethodChB;
    @FXML
    private CheckBox improvedEulerMethodChB;
    @FXML
    private CheckBox rkMethodChB;
    @FXML
    private CheckBox gteChB;
    @FXML
    private CheckBox lteChB;

    public String getX0Text() {
        return x0TextField.getText();
    }

    public String getY0Text() {
        return y0TextField.getText();
    }

    public String getXText() {
        return XTextField.getText();
    }

    public String getNTab1Text() {
        return NTab1TextField.getText();
    }

    public String getN0Text() {
        return n0TextField.getText();
    }

    public String getNTab2Text() {
        return NTab2TextField.getText();
    }

    public void rebuildTab1Charts() {
        Point _initialPoint = new Point(Double.valueOf(getX0Text()), Double.valueOf(getY0Text()));
        Double _lastXCoordinate = Double.valueOf(getXText());
        Integer _numberOfPoints = Integer.valueOf(getNTab1Text());
        rebuildMethodChart(calculationController.getSeriesListOfMethods(
                _initialPoint, _lastXCoordinate, _numberOfPoints,
                eulerMethodChB.isSelected(), improvedEulerMethodChB.isSelected(), rkMethodChB.isSelected(), analyticalMethodChB.isSelected()));
        rebuildErrorsChart(calculationController.getSeriesListOfErrors(
                _initialPoint, _lastXCoordinate, _numberOfPoints,
                eulerMethodChB.isSelected(), improvedEulerMethodChB.isSelected(), rkMethodChB.isSelected(), analyticalMethodChB.isSelected(),
                gteChB.isSelected(), lteChB.isSelected()));
    }

    public void rebuildTab2Charts() {
        Integer _startNumberOfPoint = Integer.valueOf(getN0Text());
        Integer _lastNumberOfPoint = Integer.valueOf(getNTab2Text());
        Point _initialPoint = new Point(Double.valueOf(getX0Text()), Double.valueOf(getY0Text()));
        Double _lastXCoordinate = Double.valueOf(getXText());
        rebuildMaximumErrorsChart(calculationController.getSeriesListOfMaximumErrors(
                _startNumberOfPoint, _lastNumberOfPoint, _initialPoint, _lastXCoordinate,
                eulerMethodChB.isSelected(), improvedEulerMethodChB.isSelected(), rkMethodChB.isSelected(), analyticalMethodChB.isSelected(),
                gteChB.isSelected(), lteChB.isSelected()));
    }

    public void rebuildMethodChart(ArrayList<XYChart.Series> series) {
        if (!wasMethodsChartCalibrated) {
            calibrateChart(methodsChart);
            wasMethodsChartCalibrated = true;
        }
        methodsChart.getData().clear();
        for (int i = 0; i < series.size(); i++) {
            methodsChart.getData().add(series.get(i));
        }
    }

    public void rebuildErrorsChart(ArrayList<XYChart.Series> series) {
        if (!wasErrorsChartCalibrated) {
            calibrateChart(errorsChart);
            wasErrorsChartCalibrated = true;
        }
        errorsChart.getData().clear();
        for (int i = 0; i < series.size(); i++) {
            errorsChart.getData().add(series.get(i));
        }
    }

    public void rebuildMaximumErrorsChart(ArrayList<XYChart.Series> series) {
        if (!wasMaximumErrorsChartCalibrated) {
            calibrateChart(maximumErrorsChart);
            wasMaximumErrorsChartCalibrated = true;
        }
        maximumErrorsChart.getData().clear();
        for (int i = 0; i < series.size(); i++) {
            maximumErrorsChart.getData().add(series.get(i));
        }
    }

    private void calibrateChart(LineChart<Double, Double> chart) {
        chart.setCreateSymbols(false);
    }

    public void setCalculationController(CalculationController calculationController) {
        this.calculationController = calculationController;
    }

    @FXML
    private void clickerTab1Btn() {
        rebuildTab1Charts();
    }

    @FXML
    private void clickerTab2Btn() {
        rebuildTab2Charts();
    }
}
