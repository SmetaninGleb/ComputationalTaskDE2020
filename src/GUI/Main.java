package GUI;

import Controllers.CalculationController;
import Controllers.MainInterfaceController;
import Models.Point;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL urlFXML = getClass().getResource("MainWindow.fxml");
        fxmlLoader.setLocation(urlFXML);
        Parent root = fxmlLoader.load();
        MainInterfaceController mainInterfaceController = fxmlLoader.getController();
        CalculationController calculationController = new CalculationController();
        mainInterfaceController.setCalculationController(calculationController);
        mainInterfaceController.rebuildTab1Charts();
        mainInterfaceController.rebuildTab2Charts();
        primaryStage.setTitle("Computational Task");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
