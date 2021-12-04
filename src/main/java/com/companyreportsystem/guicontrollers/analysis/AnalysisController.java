package com.companyreportsystem.guicontrollers.analysis;

import com.companyreportsystem.helpers.errorhandling.alertmanager.AlertManager;
import com.companyreportsystem.helpers.configurators.choiceboxconfigurators.EmployeeChoiceBoxConfigurator;
import com.companyreportsystem.helpers.initializators.LinePlotInitialization;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AnalysisController implements Initializable {

    @FXML
    private ChoiceBox<String> employeeChoiceBox;

    @FXML
    private LineChart<Number, Number> reviewsPlot;

    private final EmployeeChoiceBoxConfigurator employeeChoiceBoxConfigurator;
    private final AlertManager alertManager;

    public AnalysisController() {
        employeeChoiceBoxConfigurator = new EmployeeChoiceBoxConfigurator();
        alertManager = new AlertManager();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initEmployeeChoiceBox();
    }

    private void initReviewsScatterPlot(String firstname, String lastname, String position, String department) {
        LinePlotInitialization linePlotInitialization = new LinePlotInitialization(firstname, lastname, position, department);
        linePlotInitialization.init(reviewsPlot);
    }

    private void initEmployeeChoiceBox() {
        String query = "SELECT * FROM `database`.reviews";
        employeeChoiceBoxConfigurator.initValues(query, employeeChoiceBox);
    }

    @FXML
    private void showAnalysis() {
        reviewsPlot.getData().clear();
        try {
            String[] employeeInfo = employeeChoiceBox.getValue().split("/");
            initReviewsScatterPlot(employeeInfo[0].split(" ")[0], employeeInfo[0].split(" ")[1], employeeInfo[1], employeeInfo[2]);
        } catch (Exception e) {
            alertManager.throwError("You have to select an employee");
        }
    }
}
