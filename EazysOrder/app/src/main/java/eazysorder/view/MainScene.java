package eazysorder.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import eazysorder.App;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainScene {
    private App app;
    private Scene scene;

    public MainScene(App app) {
        this.app = app;
        mainScreen();
    }

    private void mainScreen() {

        Image newImage = new Image(getClass().getResource("/pic/patrik.png").toExternalForm());
        ImageView imageView1 = new ImageView(newImage);
        imageView1.setFitWidth(831);
        imageView1.setFitHeight(623);
        imageView1.setLayoutX(-58);
        imageView1.setLayoutY(76);

        Button tombolUser = createButton("Pesan", "tomboluser", 772, 100, event -> opsiPengambilan());

        Button tombolAdmin = createButton("Admin", "tomboladmin", 772, 410, event -> {
            app.tampilkanAdminScene();
        });

        Pane pane = new Pane();
        pane.getChildren().addAll(tombolAdmin, tombolUser, imageView1);
        VBox root = new VBox(pane);

        scene = new Scene(root, 1280, 700);
        applyStylesheet();
    }

    private void opsiPengambilan() {
        Image newImage1 = new Image(getClass().getResource("/pic/memepilfiks.png").toExternalForm());
        ImageView imageView2 = new ImageView(newImage1);
        imageView2.setFitWidth(623);
        imageView2.setFitHeight(506);
        imageView2.setLayoutX(328);
        imageView2.setLayoutY(-8);

        Button takein = createButton("Take In", "takein", 150, 295, event -> {
            Menu1 menu = new Menu1();
            Scene menuScene = menu.tampilkanMenu1();
            app.getPrimaryStage().setScene(menuScene);
        });

        Button takeaway = createButton("Take Away", "takeaway", 692, 295, event -> {
            Menu1 menu = new Menu1();
            Scene menuScene = menu.tampilkanMenu1();
            app.getPrimaryStage().setScene(menuScene);
        });

        Pane pane = new Pane();
        pane.getChildren().addAll(imageView2, takeaway, takein);
        VBox vBox = new VBox(pane);
        scene.setRoot(vBox);
    }

    private Button createButton(String text, String id, double x, double y, EventHandler<ActionEvent> eventHandler) {
        Button button = new Button(text);
        button.setId(id);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setMinWidth(446);
        button.setMinHeight(205);
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
