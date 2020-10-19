package Controllers;

import Models.Point;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class MainInterfaceController {
    private Boolean wasMethodsChartCalibrated = false;
    private Boolean wasErrorsChartCalibrated = false;
    private CalculationController calculationController;
    @FXML
    private Button tab1CalculateBtn;
    @FXML
    private TextField x0TextField;
    @FXML
    private TextField y0TextField;
    @FXML
    private TextField XTextField;
    @FXML
    private TextField NTab1TextField;
    @FXML
    private LineChart<Double, Double> methodsChart;
    @FXML
    private LineChart<Double, Double> errorsChart;

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

    public void rebuildTab1Charts() {
        rebuildMethodChart(calculationController.getSeriesListOfMethods(
                new Point(Double.valueOf(x0TextField.getText()), Double.valueOf(y0TextField.getText())),
                Double.valueOf(XTextField.getText()), Integer.valueOf(NTab1TextField.getText())));
        rebuildErrorTab1Chart(calculationController.getSeriesListOfErrors(
                new Point(Double.valueOf(x0TextField.getText()), Double.valueOf(y0TextField.getText())),
                Double.valueOf(XTextField.getText()), Integer.valueOf(NTab1TextField.getText())));
    }

    public void rebuildErrorTab1Chart(ArrayList<XYChart.Series> series) {
        if (!wasErrorsChartCalibrated) {
            calibrateChart(errorsChart);
            wasErrorsChartCalibrated = true;
        }
        errorsChart.getData().clear();
        for (int i = 0; i < series.size(); i++) {
            errorsChart.getData().add(series.get(i));
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
}
