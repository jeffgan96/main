package seedu.address.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

/**
 * The side tab panel which consists of the tabs as well as the content inside.
 */
public class SideTabPanel extends UiPart<Region> {
    public static final String FXML = "SideTabPanel.fxml";


    @FXML
    private TabPane tabPane;

    @FXML
    private Tab employeeTab;

    @FXML
    private Tab projectTab;

    /**
     * Constructor for side panel. This class should be used for handling of the tabpane and tabs
     * and not the actual contents inside the tabpane
     * @param projectsNode root node of the project viewlist
     * @param employeesNode root node of the employee viewlist
     */
    public SideTabPanel(Node projectsNode, Node employeesNode) {
        super(FXML);
        employeeTab.setContent(employeesNode);
        projectTab.setContent(projectsNode);
        tabPane.getTabs().addAll(employeeTab, projectTab);

    }

    public void setContentToEmployeeTab(Node value) {
        employeeTab.setContent(value);
    }

    public void setContentToProjectTab(Node value) {
        projectTab.setContent(value);
    }

    public List<Tab> getTabs() {
        return tabPane.getTabs();
    }

}
