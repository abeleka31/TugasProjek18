package eazysorder.view;
import eazysorder.App;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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

        Button tombolUser = new Button("PESAN");
        tombolUser.setId("pesan");
        tombolUser.setPrefSize(306, 136);
        tombolUser.setLayoutX(767);
        tombolUser.setLayoutY(168);
        tombolUser.setOnAction(event -> {
            opsiPengambilan();
        });

        Button tombolAdmin = new Button("Admin");
        tombolAdmin.setId("admin");
        tombolAdmin.setPrefSize(306, 136);
        tombolAdmin.setLayoutX(767);
        tombolAdmin.setLayoutY(409);
        tombolAdmin.setOnAction(event -> {
            app.tampilkanAdminScene();
        });
        
        Pane pane = new Pane();
        pane.getChildren().addAll(tombolAdmin, tombolUser, imageView1);
        VBox root = new VBox(pane);

        scene = new Scene(root, 1280, 700);
        applyStylesheet();
    }

    

    void opsiPengambilan() {
        Image newImage1 = new Image(getClass().getResource("/pic/memepilfiks.png").toExternalForm());
        ImageView imageView2 = new ImageView(newImage1);
        imageView2.setFitWidth(623);
        imageView2.setFitHeight(506);
        imageView2.setLayoutX(328);
        imageView2.setLayoutY(-8);

        
        Button takein = new Button("DINE IN");
        takein.setId("takein");
        takein.setLayoutX(210);
        takein.setLayoutY(270);
        takein.setPrefSize(306, 136);
        takein.setOnAction(event -> {
            Menu1 menu = new Menu1(this, scene);
            Scene menuScene = menu.tampilkanMenu1();
            App.getPrimaryStage().setScene(menuScene);
        });

        Button takeaway = new Button("TAKE AWAY");
        takeaway.setId("takeaway");
        takeaway.setLayoutX(748);
        takeaway.setLayoutY(270);
        takeaway.setPrefSize(306, 136);
        takeaway.setOnAction(event -> {
            Menu1 menu = new Menu1(this, scene);
            Scene menuScene = menu.tampilkanMenu1();
            App.getPrimaryStage().setScene(menuScene);
        });
        
        Button kembali = new Button("<- Kembali");
        kembali.setId("balik");
        kembali.setLayoutX(40);
        kembali.setLayoutY(614);
        kembali.setPrefSize(124, 47);
        kembali.setOnAction(event -> {
            app.tampilkanSceneUtama();
        });

        Pane pane = new Pane();
        pane.getChildren().addAll(imageView2, takeaway, takein, kembali);
        VBox vBox = new VBox(pane);
        scene.setRoot(vBox);
    }

    private void applyStylesheet() {
        String css = this.getClass().getResource("/css/Style.css").toExternalForm();
        scene.getStylesheets().add(css);
    }

    public Scene getScene() {
        return scene;
    }
}
