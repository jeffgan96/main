package seedu.address.ui;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import seedu.address.model.employee.Employee;

public class EmployeeDetailPanel extends UiPart<Region> {

    public static final String FXML = "EmployeeDetailPanel.fxml";
    private static final int PAGE_EMPLOYEE_SUMMARY = 0;
    private static final int PAGE_EMPLOYEE_GITHUB = 1;

    @javafx.fxml.FXML
    private Pagination pagination;


    public EmployeeDetailPanel(ObservableValue<Employee> selectedEmployee) {
        super(FXML);

        Employee employee = selectedEmployee.getValue();

        selectedEmployee.addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                loadDefaultPage();
                return;
            }
            loadSummaryPage(newValue);
        });

        pagination.setPageFactory((Integer pageNumber) -> {
            switch (pageNumber) {
            case PAGE_EMPLOYEE_SUMMARY:
                return loadSummaryPage(employee);
            case PAGE_EMPLOYEE_GITHUB:
                return loadGithubPage(employee);
            default:
                return loadDefaultPage();
            }
        });

    }

    private VBox loadSummaryPage(Employee employee) {
        VBox vbox = new VBox();
        Label name = new Label();
        name.setText(employee.getName().fullName);
        vbox.getChildren().add(name);
        return vbox;
    }

    private WebView loadGithubPage(Employee employee) {
        WebView newBrowser = new WebView();
        WebEngine webEngine = newBrowser.getEngine();
        webEngine.load("https://github.com/jeffgan96");
        return newBrowser;
    }

    private WebView loadDefaultPage() {
        WebView newBrowser = new WebView();
        WebEngine webEngine = newBrowser.getEngine();
        webEngine.load("https://github.com/jeffgan96");
        return newBrowser;
    }





}
