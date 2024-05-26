<<<<<<< HEAD
package eazysorder.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import eazysorder.App;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainScene {
    private App app;
    private Scene scene;

    public MainScene(App app) {
        this.app = app;
        mainScreen();
    }

    private void mainScreen(){
        Button tombolUser = createButton("User", "tomboluser", 830, 164, event -> opsiPengambilan());

        Button tombolAdmin = createButton("Admin", "tomboladmin", 830, 354, event -> {
            app.tampilkanAdminScene();
        });

        Pane pane = new Pane();
        pane.getChildren().addAll(tombolAdmin, tombolUser);
        VBox root = new VBox(pane);

        scene = new Scene(root, 1280, 700);
        applyStylesheet();
    }

    private void opsiPengambilan(){
        Button takein = createButton("Take In", "takein", 785, 285, event -> {
            Menu1 menu = new Menu1();
            Scene menuScene = menu.tampilkanMenu1();
            app.getPrimaryStage().setScene(menuScene);
        });

        Button takeaway = createButton("Take Away", "takeaway", 173, 285, event -> {
            // Handle Take Away action here
        });

        Pane pane = new Pane();
        pane.getChildren().addAll(takeaway, takein);
        VBox vBox = new VBox(pane);
        scene.setRoot(vBox);
    }

    private Button createButton(String text, String id, double x, double y, EventHandler<ActionEvent> eventHandler) {
        Button button = new Button(text);
        button.setId(id);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setMinWidth(290);
        button.setMinHeight(130);
        button.setOnAction(eventHandler);
        return button;
    }

    private void applyStylesheet() {
        String css = this.getClass().getResource("/css/Style.css").toExternalForm();
        scene.getStylesheets().add(css);
    }

    public Scene getScene() {
        return scene;
    }
}
=======
package eazysorder.view;

import eazysorder.App;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainScene {
    private App app;
    private Scene scene;

    public MainScene(App app) {
        this.app = app;
        MainScreen();
    }

    public void MainScreen(){
        Button tombolUser = new Button("User ");
        tombolUser.setId("tomboluser");
        tombolUser.setLayoutX(830);
        tombolUser.setLayoutY(164);
        tombolUser.setMinWidth(290);
        tombolUser.setMinHeight(130);
        tombolUser.setOnAction(event -> {
            opsiPengambilan();
        });

        Button tombolAdmin = new Button("Admin ");
        tombolAdmin.setId("tomboladmin");
        tombolAdmin.setLayoutX(830);
        tombolAdmin.setLayoutY(354);
        tombolAdmin.setMinWidth(290);
        tombolAdmin.setMinHeight(130);
        
        
        Pane pane = new Pane();
        pane.getChildren().addAll(tombolAdmin, tombolUser);
        VBox root = new VBox(pane);
        
        scene = new Scene(root, 1280, 700);

        String css = this.getClass().getResource("/css/Style.css").toExternalForm();
        scene.getStylesheets().add(css);
    }

    public void opsiPengambilan(){
        Button takein = new Button("Take In");
        takein.setId("takein");
        takein.setLayoutX(785);
        takein.setLayoutY(285);
        takein.setMinWidth(290);
        takein.setMinHeight(130);
        takein.setOnAction(event -> {
            Menu1 menu = new Menu1();
            app.getPrimaryStage().setScene(menu.tampilkanMenu1()); // Assuming you have a method in App class to get the primary stage
            app.getPrimaryStage().show(); // Show the new scene
        });

        Button takeaway = new Button("Take Away");
        takeaway.setId("takeaway");
        takeaway.setLayoutX(173);
        takeaway.setLayoutY(285);
        takeaway.setMinWidth(290);
        takeaway.setMinHeight(130);

        Pane pane = new Pane();
        pane.getChildren().addAll(takeaway, takein);
        VBox vBox = new VBox(pane);
        vBox.setEffect(null);
        scene.setRoot(vBox);


    }

    public Scene getScene(){
        return scene;
    }
}


>>>>>>> f48e2078cc0638222f2d24d94dbad635278daf87
