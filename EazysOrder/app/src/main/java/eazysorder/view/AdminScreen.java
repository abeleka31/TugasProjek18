package eazysorder.view;

import eazysorder.App;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class AdminScreen {
    private App app;
    private Scene scene;

    public AdminScreen(App app){
        this.app = app;
        createAdminScene();
    }

    private void createAdminScene() {
        Pane mainPane = createAdminPane();

        scene = new Scene(mainPane, 1280, 700);
        String css = this.getClass().getResource("/css/Style.css").toExternalForm();
        scene.getStylesheets().add(css);
    }

    private Pane createAdminPane(){
        Label foodName = new Label("Food Name: ");
        
        Pane pane = new Pane(foodName);
        return pane;
    }

    public Scene getScene() {
        return scene;
    }
}
