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


