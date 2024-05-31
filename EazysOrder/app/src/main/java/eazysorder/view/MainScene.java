package eazysorder.view;

import eazysorder.App;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Label;


public class MainScene {
    private App app;
    private Scene scene;

    public MainScene(App app) {
        this.app = app;
        mainScreen();
    }

    private void mainScreen() {
        Label Text1 = new Label("Eazys Order For UMKM");
        Text1.setId("maintext1");
        Text1.setTextAlignment(TextAlignment.CENTER);
        Text1.setAlignment(Pos.CENTER);
        Text1.setLayoutX(230);
        Text1.setLayoutY(52);
        Text1.setPrefSize(819, 51);

        Label Text2 = new Label("Aplikasi untuk mu dan untuk kita semua");
        Text2.setId("maintext2");
        Text2.setTextAlignment(TextAlignment.CENTER);
        Text2.setAlignment(Pos.CENTER);
        Text2.setLayoutX(381);
        Text2.setLayoutY(186);
        Text2.setPrefSize(519, 39);
        
        // Button for user
        Button tombolUser = new Button("User");
        tombolUser.setId("pesan");
        tombolUser.setPrefSize(552, 280);
        tombolUser.setLayoutX(70);
        tombolUser.setLayoutY(350);
        tombolUser.setOnAction(event -> {
            opsiPengambilan();
        });

        // Button for admin
        Button tombolAdmin = new Button("Admin");
        tombolAdmin.setId("admin");
        tombolAdmin.setPrefSize(552, 280);
        tombolAdmin.setLayoutX(657);
        tombolAdmin.setLayoutY(350);
        tombolAdmin.setOnAction(event -> {
            app.tampilkanAdminScene();
        });

        // Pane and VBox layout
        Pane pane = new Pane();
        pane.setId("bg");
        pane.getChildren().addAll(Text1, Text2, tombolAdmin, tombolUser);

        // Create scene
        scene = new Scene(pane, 1280, 700);
        applyStylesheet();
    }

    public void opsiPengambilan() {
        // Load the image
        Image newImage1 = new Image(getClass().getResource("/pic/memepilfiks.png").toExternalForm());
        ImageView imageView2 = new ImageView(newImage1);
        imageView2.setFitWidth(623);
        imageView2.setFitHeight(506);
        imageView2.setLayoutX(328);
        imageView2.setLayoutY(-8);

        // Dine-in button
        Button takein = new Button("PESAN");
        takein.setId("takein");
        takein.setLayoutX(464);
        takein.setLayoutY(279);
        takein.setPrefSize(351, 141);
        takein.setOnAction(event -> {
            Menu1 menu = new Menu1(this, scene);
            Scene menuScene = menu.tampilkanMenu1(null);
            App.getPrimaryStage().setScene(menuScene);
        });

        // Back button
        Button kembali = new Button("");
        kembali.setId("balik");
        kembali.setLayoutX(37);
        kembali.setLayoutY(597);
        kembali.setPrefSize(65, 65);
        kembali.setOnAction(event -> {
            app.tampilkanSceneUtama();
        });

        Label Text3 = new Label("'Kamu tidak bisa membeli kebahagiaan, \n" + //
                        "Tetapi kamu bisa membeli donat\n" + //
                        "dan itu pada dasarnya adalah hal yang sama'");
        Text3.setId("maintext3");
        Text3.setTextAlignment(TextAlignment.CENTER);
        Text3.setAlignment(Pos.CENTER);
        Text3.setLayoutX(307);
        Text3.setLayoutY(457);
        Text3.setPrefSize(593, 105);

        // Pane and VBox layout for opsiPengambilan
        Pane pane = new Pane();
        pane.setId("bg");
        pane.getChildren().addAll(imageView2, Text3, takein, kembali);
        
        scene.setRoot(pane);
    }

    private void applyStylesheet() {
        String css = this.getClass().getResource("/css/Style.css").toExternalForm();
        String scc = this.getClass().getResource("/css/AdminStyle.css").toExternalForm();
        scene.getStylesheets().addAll(css, scc);
    }

    public Scene getScene() {
        return scene;
    }
}
