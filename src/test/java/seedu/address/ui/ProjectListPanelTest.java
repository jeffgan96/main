package seedu.address.ui;


import static java.time.Duration.ofMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.address.testutil.TypicalProjects.getTypicalProjects;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysProject;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardEquals;

import java.util.Collections;

import org.junit.Test;

import guitests.guihandles.ProjectCardHandle;
import guitests.guihandles.ProjectListPanelHandle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.project.Client;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

public class ProjectListPanelTest extends GuiUnitTest {
    private static final ObservableList<Project> TYPICAL_PROJECTS =
            FXCollections.observableList(getTypicalProjects());

    private static final long CARD_CREATION_AND_DELETION_TIMEOUT = 2500;

    private final SimpleObjectProperty<Project> selectedProject = new SimpleObjectProperty<>();
    private ProjectListPanelHandle projectListPanelHandle;

    @Test
    public void display() {
        initUi(TYPICAL_PROJECTS);

        for (int i = 0; i < TYPICAL_PROJECTS.size(); i++) {

            projectListPanelHandle.navigateToCard(TYPICAL_PROJECTS.get(i));
            Project expectedProject = TYPICAL_PROJECTS.get(i);
            ProjectCardHandle actualCard = projectListPanelHandle.getProjectCardHandle(i);

            assertCardDisplaysProject(expectedProject, actualCard);
            assertEquals(Integer.toString(i + 1) + ". ", actualCard.getId());
        }
    }

    @Test
    public void selection_modelSelectedProjectChanged_selectionChanges() {
        initUi(TYPICAL_PROJECTS);
        Project secondProject = TYPICAL_PROJECTS.get(INDEX_SECOND_PROJECT.getZeroBased());
        guiRobot.interact(() -> selectedProject.set(secondProject));
        guiRobot.pauseForHuman();

        ProjectCardHandle expectedProject =
                projectListPanelHandle.getProjectCardHandle(INDEX_SECOND_PROJECT.getZeroBased());
        ProjectCardHandle selectedProject = projectListPanelHandle.getHandleToSelectedCard();
        assertCardEquals(expectedProject, selectedProject);
    }

    /**
     * Verifies that creating and deleting large number of projects in {@code ProjectsListPanel} requires lesser than
     * {@code CARD_CREATION_AND_DELETION_TIMEOUT} milliseconds to execute.
     */
    @Test
    public void performanceTest() {
        ObservableList<Project> backingList = createBackingList(10000);

        assertTimeoutPreemptively(ofMillis(CARD_CREATION_AND_DELETION_TIMEOUT), () -> {
            initUi(backingList);
            guiRobot.interact(backingList::clear);
        }, "Creation and deletion of project cards exceeded time limit");
    }

    /**
     * Returns a list of projects containing {@code projectCount} projects that is used to populate the
     * {@code ProjectListPanel}.
     */
    private ObservableList<Project> createBackingList(int projectCount) {
        ObservableList<Project> backingList = FXCollections.observableArrayList();
        for (int i = 0; i < projectCount; i++) {
            ProjectName projectName = new ProjectName(i + "a");
            Deadline deadline = new Deadline("12/12/2012");
            Client client = new Client("a");
            Project project = new Project(projectName, client, deadline);
            backingList.add(project);
        }
        return backingList;
    }

    /**
     * Initializes {@code projectListPanelHandle} with a {@code ProjectListPanel} backed by {@code backingList}.
     * Also shows the {@code Stage} that displays only {@code ProjectListPanel}.
     */
    private void initUi(ObservableList<Project> backingList) {
        ProjectListPanel projectListPanel =
                new ProjectListPanel(backingList, selectedProject, selectedProject::set);
        uiPartRule.setUiPart(projectListPanel);

        projectListPanelHandle = new ProjectListPanelHandle(getChildNode(projectListPanel.getRoot(),
                ProjectListPanelHandle.PROJECT_LIST_VIEW_ID));
    }
}
