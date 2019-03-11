package guitests.guihandles;

import javafx.scene.control.TabPane;

public class SideTabPanelHandle extends NodeHandle<TabPane> {

    public static final String SIDE_TAB_ID = "#tabPane";

    public static final String PROJECT_TAB_ID = "projectTab";

    public SideTabPanelHandle(TabPane tabpane) {
        super(tabpane);
    }


}
